const mongoose = require('mongoose');

let userSchema = new mongoose.Schema({
    id: Number,
    nick: String,
    email: String,
}, {versionKey: false});

module.exports = mongoose.model('User', userSchema, 'users');