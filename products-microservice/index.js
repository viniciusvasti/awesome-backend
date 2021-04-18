const express = require("express");
const logger = require("morgan-body");

const app = express();
const productsRouter = require("./routes/products");

const port = 8013;

// Setting middlewares
app.use(express.json());
logger(app, { prettify: false, includeNewLine: true });

// Setting routes
app.use("/api/v1/product-management/products", productsRouter);

app.listen(port, () => {
  console.log(`Awesome Backend - Products Microservice is running on ${port}`);
});
