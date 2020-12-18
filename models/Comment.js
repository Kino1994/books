const mongoose = require('mongoose');

let commentSchema = new mongoose.Schema({
    id: Number,
    message: String,
    score: Number,
    userId: Number,
    bookId: Number
}, {versionKey: false});

module.exports = mongoose.model('Comment', commentSchema, 'comments')