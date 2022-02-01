import mongoose from 'mongoose';
var AnuncioSchema = new mongoose.Schema({
    title: String,
    author: String,
    content: String
});
mongoose.model('Anuncio', AnuncioSchema);

export default mongoose.model('Anuncio');