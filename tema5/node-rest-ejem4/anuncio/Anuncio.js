var mongoose = require('mongoose');  
var AnuncioSchema = new mongoose.Schema({  
  title: String,
  author: String,
  content: String
});
mongoose.model('Anuncio', AnuncioSchema);

module.exports = mongoose.model('Anuncio');