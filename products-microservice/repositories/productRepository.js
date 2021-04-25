const Product = require("../models/Product");

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

    insertMany: async (products) => {
      const newProducts = products.map(
        (product) =>
          new Product({
            sku: product.sku,
            name: product.name,
            price: product.price,
          })
      );

      return await Product.insertMany(newProducts);
    },

    count: async () => {
      return await Product.countDocuments();
    },
  };
};

exports["@singleton"] = true;
