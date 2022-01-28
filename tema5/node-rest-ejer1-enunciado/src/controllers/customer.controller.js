import Customer from "../models/customer.model.js";

// Create and Save a new Customer
export const create = (req, res) => {
    // Validate request
    if (!req.body) {
        res.status(400).send({
            message: "Content can not be empty!"
        });
    }
    // Save Customer in the database

    Customer.create(req.body)
        .then(customer => {
            res.status(201).send(customer);
        })
        .catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while creating the Customer."
            });
        });

};

// Retrieve all Customers from the database.
export const findAll = (req, res) => {

    Customer.findAll()
        .then(customers => res.send(customers))
        .catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while retrieving customers."
            });
        });

};

// Find a single Customer with a customerId
export const findOne = async(req, res) => {


    const customer = await Customer.findByPk(req.params.customerId)
        .catch(err => res.status(500).send(err));

    if (customer == null) {
        res.status(404).send({
            message: `Not found Customer with id ${req.params.customerId}.`
        });
    } else {
        res.send(customer);
    }

};

// Update a Customer identified by the customerId in the request
export const update = async(req, res) => {
    // Validate Request
    if (!req.body) {
        res.status(400).send({
            message: "Content can not be empty!"
        });
    }

    const customer = await Customer.update(req.body, { where: { id: req.params.customerId } })
        .catch(err => {
            res.status(500).send({
                message: "Error updating Customer with id " + req.params.customerId
            });
        })

    if (customer == null) {
        res.status(404).send({
            message: `Not found Customer with id ${req.params.customerId}.`
        });
    } else {
        res.send(customer);
    }

};

// Delete a Customer with the specified customerId in the request
export const destroy = async(req, res) => {

    Customer.destroy({
            where: {
                id: req.params.customerId
            }
        }).then(customersDeleted => {
            if (customersDeleted == 0) {
                res.status(404).send({
                    message: `Not found Customer with id ${req.params.customerId}.`
                });
            } else {
                res.status(204).send({ message: `Customer was deleted successfully!` });
            }
        })
        .catch(err => {
            res.status(500).send({
                message: "Could not delete Customer with id " + req.params.customerId
            });
        });

};