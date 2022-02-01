import db from "../db/db.js";
import pkg from 'sequelize';
const { Model, DataTypes } = pkg;

export default class Customer extends Model {}

Customer.init({
    email: DataTypes.STRING,
    name: DataTypes.STRING,
    active: DataTypes.BOOLEAN
}, { sequelize: db, modelName: 'customers' })