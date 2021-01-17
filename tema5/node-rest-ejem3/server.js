var express = require('express');
var app = express();

var AnuncioController = require('./anuncio/AnuncioController');
app.use('/anuncios', AnuncioController);

module.exports = app;