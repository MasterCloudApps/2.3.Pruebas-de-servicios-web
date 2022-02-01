import app from './src/app';
import mongoose from 'mongoose';

var port = process.env.PORT || 3000;
// Connect
mongoose.connect('mongodb://localhost:27017');

app.listen(port, function() {
    console.log('Express server listening on port ' + port);
});