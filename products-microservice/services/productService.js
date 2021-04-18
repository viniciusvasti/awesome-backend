exports = module.exports = function (productRepository) {
  return {
    getAll: function getAll() {
      return productRepository.findAll();
    },

    getBySku: function getBySku(sku) {
      return productRepository.findBySku(sku);
    },

    create: function create(product) {
      if (!productIsValid(product)) {
        throw new Error("Product is not valid");
      }
      return productRepository.insert(product);
    },
  };
};

function productIsValid(product) {
  return product.sku && product.name && product.price;
}

exports["@singleton"];
exports["@require"] = ["productRepository"];
