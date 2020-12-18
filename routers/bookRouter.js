const express = require('express');
const Book = require('../models/Book.js');
const User = require('../models/User.js');
const Comment = require('../models/Comment.js');
const comments = require("mongodb");

let bookRouter = express.Router();

async function findAllBooks(){
    return await Book.find().exec();
}

async function findAllComments() {
    return await Comment.find().exec();
}

async function findAllUsers(){
    return await User.find().exec();
}

async function nextBookId(){
    const book = await Book.find().sort({id:-1}).limit(1);
    if (book[0]){
        return book[0].id + 1;
    }
    return 1;
}

async function nextCommentId(){
    const comment = await Comment.find().sort({id:-1}).limit(1);
    if (comment[0]){
        return comment[0].id +1;
    }
    return 1;
}

async function nextUserId(){
    const user = await User.find().sort({id:-1}).limit(1);
    if (user[0]){
        return user[0].id +1;
    }
    return 1;
}

bookRouter.route('/books')
    .get( async (req, res) => {
        res.status(200).jsonp(await findAllBooks());
    })
    .post( async(req, res) =>{
         let book = new Book({
             id: await nextBookId(),
             tittle: req.body.tittle,
             author: req.body.author,
             summary: req.body.summary,
             publisher: req.body.publisher,
             year: req.body.publisher
         })
         book = await book.save();
         res.status(201).jsonp(book);
    });


bookRouter.route('/books/:id')
    .get( async (req, res) => {
        let book = Book.findOne({id: req.params.nick}).exec();
        if (!book){
            res.status(404).send('Book not found');
        } else {
            let comments = await Comment.find({id: req.params.id}).exec();
            if (comments != null){
                book.comments = comments;
                let response = {
                    book: book,
                    comments: book.comments
                }
                return res.status(200).jsonp(response);
            } else {
                res.status(200).jsonp(book);
            }
        }
    });


bookRouter.route('/comments')
    .get( async (req, res)=>{
        res.status(200).jsonp(await findAllComments());
    });

bookRouter.route("/books/:id/comments")
    .post(async (req, res) => {
        let book = await Book.find({id: req.params.id}).exec();
        let user = await User.find({nick: req.body.nick}).exec();
        if(!book){
            res.status(404).send('Book not found');
        }
        if(req.body.score > 5 || req.body.score < 1) {
            res.status(404).send('Invalid score');
        }
        else {
            let comment = new Comment({
                id: await nextCommentId(),
                message: req.body.message,
                score: req.body.score,
                bookId: req.params.id,
                userId: user.id
            })
            comment = await comment.save();
            res.status(201).jsonp(comment);
        }
    })

bookRouter.route("/books/comments/:id")
    .delete(async (req, res) => {
        let comment = await Comment.findOne({id: req.params.id}).exec();
        if (comment != null){
            await comment.delete();
            res.status(200).send("Comment deleted");
        } else {
            res.status(404).send("Comment not found");
        }
    });

bookRouter.route('/users')
    .get(async (req, res) => {
        res.status(200).json(await findAllUsers());
    })
    .post(async(req, res) => {
        let user = await User.findOne({nick: req.body.nick}).exec();
        if (!user){
            user = new User({
                id: await nextUserId(),
                nick: req.body.nick,
                email: req.body.email
            });
            await user.save();
            res.status(201).json(user);
        } else {
            res.status(500).send('User already exists');
        }
    });

bookRouter.route('/users/:nick')
    .get(async (req, res) =>{
        let user = await User.findOne({nick: req.params.nick}).exec();
        if (!user){
            res.status(404).send('User not found');
        } else {
            res.status(200).json(user.toObject());
        }
    });

bookRouter.route('/users/:id')
    .patch(async (req, res) =>{
        let user = await User.findOneAndUpdate({id: req.params.id},  {email: req.body.email}, {
            new: true
        });
        if (!user){
            res.status(404).send('User not found')
        } else {
            res.status(200).json(user);
        }

    })

    .delete(async (req, res) => {
        let user = await User.findOne({id: req.params.id}).exec();
        if (!user){
            res.status(404).send('User not found');
        } else {
            let comment = Comment.findOne({userId: user.id}).exec();
            if (!comment)
            User.deleteOne({id: req.params.id}, function (error) {
                if(error){
                    res.status(500).send();
                }
                res.status(200).send('User deleted');
            })
            else {
                res.status(404).send('Users with comments can not be deleted');
            }
        }
    });

bookRouter.route('/users/:id/comments')
    .get(async (req, res) =>{
        let user = await User.findOne({id: req.params.id}).exec();
        if (user != null){
            let comment = await Comment.find({userId: user.id}).exec();
            if (comment != null){
                res.status(200).jsonp(comment);
            } else {
                res.status(404).send("Comments not found");
            }
        } else {
            res.status(404).send("User not found");
        }
    });

module.exports = bookRouter;