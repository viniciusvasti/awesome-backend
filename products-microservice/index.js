const express = require("express");
const IoC = require("electrolyte");
const logger = require("morgan-body");

IoC.use(IoC.dir("controllers"));
IoC.use(IoC.dir("services"));
IoC.use(IoC.dir("repositories"));

const productController = IoC.create("productController");

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

const initProductsCollection = () => {
  const productService = IoC.create("productService");
  productService.count().then((count) => {
    if (count === 0) {
      console.log("Creating some products...");
      productService
        .createMany([
          {
            sku: "0001",
            name: "IPhone 12",
            price: "10000",
          },
          {
            sku: "0002",
            name: "Samsung S20",
            price: "8000",
          },
          {
            sku: "0003",
            name: "Moto Raz4r",
            price: "2000",
          },
        ])
        .then(() => console.log("Created some products."));
    }
  });
};

initProductsCollection();

module.exports = app;
