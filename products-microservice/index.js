const express = require("express");
const app = express();
const productsRouter = require("./routes/products");

const port = 8013;

app.use(express.json());
app.use("/api/v1/product-management/products", productsRouter);

app.listen(port, () => {
  console.log(`Awesome Backend - Products Microservice is running on ${port}`);
});
