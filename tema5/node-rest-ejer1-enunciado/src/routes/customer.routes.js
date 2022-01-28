import { create, findAll, findOne, update, destroy } from "../controllers/customer.controller.js";

export default (app) => {

    // Create a new Customer
    app.post("/api/customers", create);

    // Retrieve all Customers
    app.get("/api/customers", findAll);

    // Retrieve a single Customer with customerId
    app.get("/api/customers/:customerId", findOne);

    // Update a Customer with customerId
    app.put("/api/customers/:customerId", update);

    // Delete a Customer with customerId
    app.delete("/api/customers/:customerId", destroy);

};