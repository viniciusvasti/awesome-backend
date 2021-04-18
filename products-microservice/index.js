const express = require("express");
const IoC = require("electrolyte");
const logger = require("morgan-body");

IoC.use(IoC.dir('controllers'));
IoC.use(IoC.dir('services'));
IoC.use(IoC.dir('repositories'));

const productController = IoC.create('productController');

const app = express();

const port = 8013;

// Setting middlewares
app.use(express.json());
logger(app, { prettify: false, includeNewLine: true });

// Setting routes
app.use("/api/v1/product-management/products", productController);

app.listen(port, () => {
  console.log(`Awesome Backend - Products Microservice is running on ${port}`);
});
