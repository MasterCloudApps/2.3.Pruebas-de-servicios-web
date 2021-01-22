const app = require('./src/app');
const { db } = require('./src/db/db')

// set port, listen for requests
const PORT = process.env.PORT || 3000;
app.listen(PORT, async () => {
  // RUN MIGRATIONS
  await db.sync({ force: true }).catch(err=>{
    console.log(err)
  })
  console.log(`Server is running on port ${PORT}.`);
});
