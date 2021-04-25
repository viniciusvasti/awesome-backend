const Product = require("../models/Product");

exports = module.exports = function () {
  return {
    findAll: async () => {
      return await Product.find().populate("category", "name");
    },

    findBySku: async (sku) => {
      return await Product.findOne({ sku }).populate("category", "name");
    },

    insert: async (product) => {
      const newProduct = new Product({
        sku: product.sku,
        name: product.name,
        price: product.price,
        category: product.category,
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
            category: product.category,
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
