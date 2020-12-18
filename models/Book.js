const mongoose = require('mongoose')

let bookSchema = new mongoose.Schema({
    id: Number,
    tittle: String,
    author: String,
    summary: String,
    publisher: String,
    year: Number
}, {versionKey: false});

module.exports = mongoose.model('Book', bookSchema, 'books')