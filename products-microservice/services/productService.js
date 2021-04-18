const productRepository = require("../repositories/productRepository");

function getAll() {
  return productRepository.findAll();
}

function getBySku(sku) {
  return productRepository.findBySku(sku);
}

function create(product) {
  if (!productIsValid(product)) {
    throw new Error("Product is not valid");
  }
  return productRepository.insert(product);
}

function productIsValid(product) {
  return product.sku && product.name && product.price;
}

module.exports = { getAll, getBySku, create };
