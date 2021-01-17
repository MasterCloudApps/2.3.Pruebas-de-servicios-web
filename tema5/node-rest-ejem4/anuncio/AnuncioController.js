var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');

router.use(bodyParser.urlencoded({ extended: true }));
router.use(bodyParser.json());
var Anuncio = require('./Anuncio');

// CREATES A NEW AD
router.post('/', (req, res) => {
        Anuncio.create({
            title: req.body.title,
            author: req.body.author,
            content: req.body.content
        }, (err, ad) => {
            if (err) return res.status(500).send("There was a problem adding the information to the database.");
            res.status(201).send(ad);
        })
});

// RETURNS ALL THE AD IN THE DATABASE
router.get('/', function (req, res) {
        Anuncio.find({}, (err, anuncios) => {
            if (err) return res.status(500).send("There was a problem finding the Anuncios.");
            res.status(200).send(anuncios);
        });
});

// GETS A SINGLE AD FROM THE DATABASE
router.get('/:id', (req, res) => {
        Anuncio.findById(req.params.id, (err, ad) => {
            if (err) return res.status(500).send("There was a problem finding the Anuncio.");
            if (!ad) return res.status(404).send("No Anuncio found.");
            res.status(200).send(ad);
        });
});

// DELETES AN AD FROM THE DATABASE
router.delete('/:id', (req, res) => {
        Anuncio.findByIdAndRemove(req.params.id, (err, ad) => {
            if (err) return res.status(500).send("There was a problem deleting the Anuncio.");
            res.status(200).send("Anuncio: "+ ad.title +" was deleted.");
        });
});

// UPDATES A SINGLE AD IN THE DATABASE
router.put('/:id', (req, res) => {
        Anuncio.findByIdAndUpdate(req.params.id, req.body, {new: true}, (err, ad) => {
            if (err) return res.status(500).send("There was a problem updating the Anuncio.");
            res.status(200).send(ad);
        });
});


module.exports = router;