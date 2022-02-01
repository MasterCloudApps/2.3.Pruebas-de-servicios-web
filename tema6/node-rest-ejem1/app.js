import express from 'express';

const app = express();

//Convert json bodies to JavaScript object
app.use(express.json());

app.get('/ads', (req, res) => {

    var sampleAds = [
        { id: 0, message: "Vendo moto", author: "Pepe" },
        { id: 1, message: "Compro TV", author: "Juan" },
        { id: 2, message: "Cambio manta", author: "Julián" }
    ];

    res.json(sampleAds);
});

export default app;