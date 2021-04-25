const ProductCategory = require("../models/ProductCategory");

exports = module.exports = function () {
  return {
    findAll: async () => {
      return await ProductCategory.find();
    },

    findByName: async (name) => {
      return await ProductCategory.findOne({ name });
    },

    insertMany: async (productCategories) => {
      const newProductCategories = productCategories.map(
        (productCategory) =>
          new ProductCategory({
            name: productCategory.name,
          })
      );

      return await ProductCategory.insertMany(newProductCategories);
    },

    count: async () => {
      return await ProductCategory.countDocuments();
    },
  };
};

exports["@singleton"] = true;
