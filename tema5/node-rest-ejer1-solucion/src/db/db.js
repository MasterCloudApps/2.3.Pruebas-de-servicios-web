const dbConfig = require("../config/db.config.js");

const { Sequelize } = require('sequelize');

const db = new Sequelize('test', dbConfig.USER, dbConfig.PASSWORD, {
  dialect: 'mysql',
  port: process.env.MYSQL_PORT || dbConfig.MYSQL_PORT,
  logging: false
})

module.exports = { db };
