const express = require("express");

exports = module.exports = function (productService) {
  const router = express.Router();

  router.get("/", async (req, res) => {
    res.json(await productService.getAll());
  });

  router.get("/:sku", async (req, res) => {
    const { sku } = req.params;
    const foundEntity = await productService.getBySku(sku);
    if (!foundEntity) {
      res.status(404).json({ message: `Product not found for SKU ${sku}` });
      return;
    }
    res.json(foundEntity);
  });

  router.post("/", async (req, res) => {
    const newProduct = req.body;
    try {
      const createdProduct = await productService.create(newProduct);
      res.status(201).json(createdProduct);
    } catch (error) {
      res.status(400).json({ message: "Product is not valid" });
    }
  });

  return router;
};

exports["@singleton"];
exports["@require"] = ["productService"];
