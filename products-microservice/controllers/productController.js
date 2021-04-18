const express = require("express");

exports = module.exports = function (productService) {
  const router = express.Router();

  router.get("/", (req, res) => {
    res.json(productService.getAll());
  });

  // By appending the RegEx "\d+" to ":sku" path param, it is defining that ":sku" needs to be one or more digits
  router.get("/:sku(\\d+)", (req, res) => {
    const { sku } = req.params;
    const foundEntity = productService.getBySku(sku);
    if (!foundEntity) {
      res.status(404).json({ message: `Product not found for SKU ${sku}` });
      return;
    }
    res.json(foundEntity);
  });

  router.post("/", (req, res) => {
    const newProduct = req.body;
    try {
      const createdProduct = productService.create(newProduct);
      res.status(201).json(createdProduct);
    } catch (error) {
      res.status(400).json({ message: "Product is not valid" });
    }
  });

  return router;
};

exports["@singleton"];
exports["@require"] = ["productService"];
