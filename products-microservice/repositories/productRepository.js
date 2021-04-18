let mockedProducts = require("../mocks/products");

exports = module.exports = function () {
  return {
    findAll: function findAll() {
      return mockedProducts;
    },

    findBySku: function findBySku(sku) {
      return mockedProducts.find((product) => product.sku === sku);
    },

    insert: function insert(product) {
      mockedProducts = [...mockedProducts, product];
      return product;
    },
  };
};

exports["@singleton"] = true;
