import dbConfig from "../config/db.config.js";

import { Sequelize } from 'sequelize';

const db = new Sequelize('test', dbConfig.USER, dbConfig.PASSWORD, {
    dialect: 'mysql',
    port: process.env.MYSQL_PORT || dbConfig.MYSQL_PORT,
    logging: false
})

export default db;