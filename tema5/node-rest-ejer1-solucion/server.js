import app from './src/app.js';
import db from './src/db/db.js';

// set port, listen for requests
const PORT = process.env.PORT || 3000;
app.listen(PORT, async() => {
    // RUN MIGRATIONS
    await db.sync({ force: true }).catch(err => {
        console.log(err)
    })
    console.log(`Server is running on port ${PORT}`);
});