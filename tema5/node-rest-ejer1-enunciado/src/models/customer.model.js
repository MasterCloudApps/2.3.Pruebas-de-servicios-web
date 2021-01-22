const { db } = require("../db/db");
const { Model, DataTypes } = require('sequelize');

class Customer extends Model {}

Customer.init({
    email: DataTypes.STRING,
    name: DataTypes.STRING,
    active: DataTypes.BOOLEAN
},{ sequelize: db, modelName: 'customers'})

module.exports = Customer;
