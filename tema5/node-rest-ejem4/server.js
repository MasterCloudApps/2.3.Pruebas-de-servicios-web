var app = require('./app');
var port = process.env.PORT || 3000;
const mongoose = require('mongoose');

// Connect
mongoose.connect('mongodb://localhost:27017');

app.listen(port, function() {
  console.log('Express server listening on port ' + port);
});