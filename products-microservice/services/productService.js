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
        throw new Error(`Product is not valid: sku ${product.sku}`);
      }
      return await productRepository.insert(product);
    },

    createMany: async (products) => {
      products.forEach(product => {        
        if (!productIsValid(product)) {
          throw new Error(`Product is not valid: sku ${product.sku}`);
        }
      });
      return await productRepository.insertMany(products);
    },
  };
};

function productIsValid(product) {
  return product.sku && product.name && product.price;
}

exports["@singleton"];
exports["@require"] = ["productRepository"];
