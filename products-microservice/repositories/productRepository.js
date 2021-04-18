let mockedProducts = require("../mocks/products");

function findAll() {
  return mockedProducts;
}

function findBySku(sku) {
  return mockedProducts.find((product) => product.sku === sku);
}

function insert(product) {
  mockedProducts = [...mockedProducts, product];
  return product;
}

module.exports = { findAll, findBySku, insert };
