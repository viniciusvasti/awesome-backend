exports = module.exports = function (productRepository) {
  return {
    getAll: async () => {
      return await productRepository.findAll();
    },

    getBySku: async (sku) => {
      return await productRepository.findBySku(sku);
    },

    create: async (product) => {
      if (!productIsValid(product)) {
        throw new Error("Product is not valid");
      }
      return await productRepository.insert(product);
    },
  };
};

function productIsValid(product) {
  return product.sku && product.name && product.price;
}

exports["@singleton"];
exports["@require"] = ["productRepository"];
