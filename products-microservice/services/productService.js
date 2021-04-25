exports = module.exports = function (repository, productCategoryRepository) {
  return {
    getAll: async () => {
      return await repository.findAll();
    },

    getBySku: async (sku) => {
      return await repository.findBySku(sku);
    },

    create: async (product) => {
      if (!productIsValid(product)) {
        throw new Error(`Product is not valid - sku: ${product.sku}`);
      }
      const category = await findCategory(product.category);
      if (!category) {
        throw new Error(`Category not found - category: ${product.category}`);
      }
      return await repository.insert({ ...product, category: category._id });
    },

    createMany: async (products) => {
      for (const product of products) {
        if (!productIsValid(product)) {
          throw new Error(`Product is not valid - sku: ${product.sku}`);
        }

        const category = await findCategory(product.category);
        if (!category) {
          throw new Error(`Category not found - category: ${product.category}`);
        }

        product.category = category._id;
      }
      
      console.log("products", products);
      return await repository.insertMany(products);
    },

    count: async () => repository.count(),
  };

  async function findCategory(category) {
    if (category.name) {
      return await productCategoryRepository.findByName(category.name);
    }

    return await productCategoryRepository.findById(category._id);
  }
};

function productIsValid(product) {
  return (
    product.sku &&
    product.name &&
    product.price &&
    categoryIsValid(product.category)
  );
}

function categoryIsValid(category) {
  return category && (category.name || category._id);
}

exports["@singleton"];
exports["@require"] = ["productRepository", "productCategoryRepository"];
