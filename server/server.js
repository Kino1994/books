const express = require('express');
const mongoose = require('mongoose');

const bookRouter = require('../routers/bookRouter');

const User = require('../models/User.js');
const Book = require('../models/Book.js');
const Comment = require('../models/Comment.js');


const server = express();
server.use(express.json());
server.use(bookRouter);

const url = "mongodb://localhost:27017/books";

async function main(){

    await mongoose.connect(url, {
        useUnifiedTopology: true,
        useNewUrlParser: true,
        useFindAndModify: false
    }, function (err) {
        if (err) {
            console.log("Error: connecting to Database!" + err);
        }
        server.listen(8000, () => {
            loadData();
        });
    });
}

function loadData(){
    mongoose.connection.db.dropCollection('books', function(err, result) {});
    mongoose.connection.db.dropCollection('users', function(err, result) {});
    mongoose.connection.db.dropCollection('comments', function(err, result) {});


    Book.create({
        "id": 1,
        "title": "Atlas Shrugged",
        "author": "Ayn Rand",
        "summary": "Objectivism philosophy",
        "publisher": "Deusto",
        "year": 2019,
        comments: []
    });

    Book.create({
        "id": 2,
        "title": "Harry Potter y la camara secretad",
        "author": "J.K Rowling",
        "summary": "The magic boy",
        "publisher": "Salamandra",
        "year": 1999,
        comments: []
    });

    User.create({
        "id": 1,
        nick: "joaquin",
        email: "joaquindevicente@hotmail.es"
    });

    User.create({
        "id": 2,
        nick: "ja.devicentel",
        email: "ja.devicente@alumnos.urjc.es",
    });
    User.create({
        "id": 3,
        nick: "kino_1994",
        email: "kino110494@gmail.com",
    });


    Comment.create({
        "id": 1,
        message: "Comentario 1",
        score: 4,
        userId: 1,
        bookId: 1
    });

    Comment.create({
        "id": 2,
        message: "Comentario 2",
        score: 2,
        userId: 1,
        bookId: 2
    });

    Comment.create({
        "id": 3,
        message: "Comentario 3",
        score: 3,
        userId: 2,
        bookId: 1
    });

    Comment.create({
        "id": 4,
        message: "Comentario 4",
        score: 5,
        userId: 2,
        bookId: 2
    });
}

main();