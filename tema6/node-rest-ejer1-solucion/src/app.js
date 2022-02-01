import express from "express";
import bodyParser from "body-parser";
import routes from "./routes/customer.routes.js"

const app = express();

// parse requests of content-type - application/json
app.use(bodyParser.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

routes(app);

export default app;