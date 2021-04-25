exports = module.exports = function (repository) {
  return {
    getAll: async () => {
      return await repository.findAll();
    },

    getByName: async (name) => {
      return await repository.findByName(name);
    },

    createMany: async (productCategories) => {
      productCategories.forEach((productCategory) => {
        if (!productCategoryIsValid(productCategory)) {
          throw new Error(`Product category is not valid: name ${productCategory.name}`);
        }
      });
      return await repository.insertMany(productCategories);
    },

    count: async () => repository.count(),
  };
};

function productCategoryIsValid(productCategory) {
  return !!productCategory.name;
}

exports["@singleton"];
exports["@require"] = ["productCategoryRepository"];
