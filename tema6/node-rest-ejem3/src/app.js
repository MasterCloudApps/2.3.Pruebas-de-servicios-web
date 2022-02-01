import express from 'express';
import AnuncioController from './controller/AnuncioController';

var app = express();

app.use('/anuncios', AnuncioController);

export default app;