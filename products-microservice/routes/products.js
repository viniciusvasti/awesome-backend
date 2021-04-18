const express = require("express");

const router = express.Router();
let mockedProducts = require("../mocks/products");

router.get("/", (req, res) => {
  res.json(mockedProducts);
});

// By appending the RegEx "\d+" to ":sku" path param, it is defining that ":sku" needs to be one or more digits
router.get("/:sku(\\d+)", (req, res) => {
  const { sku } = req.params;
  const foundEntity = mockedProducts.find(({ sku }) => sku === sku);
  if (!foundEntity) {
    res.status(404).json({ message: `Product not found for SKU ${sku}` });
  } else {
    res.json(foundEntity);
  }
});

router.post("/", (req, res) => {
  const newProduct = req.body;
  if (!productIsValid(newProduct)) {
    res.status(400).json({ message: "Product is not valid" });
  } else {
    mockedProducts = [...mockedProducts, newProduct];
    res.json(mockedProducts);
  }
});

function productIsValid(product) {
  return product.sku && product.name && product.price;
}

module.exports = router;
