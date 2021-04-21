const Product = require("../models/Product");
let mockedProducts = require("../mocks/products");

exports = module.exports = function () {
  return {
    findAll: async () => {
      return await Product.find();
    },

    findBySku: async (sku) => {
      return await Product.findOne({ sku });
    },

    insert: async (product) => {
      const newProduct = new Product({
        sku: product.sku,
        name: product.name,
        price: product.price,
      });

      return await newProduct.save();
    },
  };
};

exports["@singleton"] = true;
