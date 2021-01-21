var express = require('express');
var app = express();

var AnuncioController = require('./controller/AnuncioController');
app.use('/anuncios', AnuncioController);

module.exports = app;