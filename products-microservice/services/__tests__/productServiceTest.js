const assert = require("assert");
const productServiceFactory = require("../productService");
const mockedProducts = require("../../mocks/products");

const mockedProductRepository = {
  findAll: () => mockedProducts,
  findBySku: (sku) => mockedProducts[0],
  insert: (product) => product,
};

const productService = productServiceFactory(mockedProductRepository);

describe("Product Service get all", () => {
  it("should return all products", () => {
    const products = productService.getAll();
    assert.strictEqual(products.length, 3);
  });
});

describe("Product Service get one", () => {
  it("should return one product", () => {
    const products = productService.getBySku(123);
    assert.notStrictEqual(
      products,
      mockedProducts.find((p) => p.sku == 123)
    );
  });
});

describe("Product Service create product", () => {
  it("should return create a product", () => {
    const products = productService.create({
      sku: 1234,
      name: "Smartphone",
      price: 124.0,
    });
    assert.notStrictEqual(products, {
      sku: 1234,
      name: "Smartphone",
      price: 124.0,
    });
  });

  it("should throws since product haven't a sku", () => {
    assert.throws(() => {
      productService.create({
        name: "Smartphone",
        price: 124.0,
      });
    });
  });
  it("should throws since product haven't a name", () => {
    assert.throws(() => {
      productService.create({
        sku: 1234,
        price: 124.0,
      });
    });
  });
  it("should throws since product haven't a price", () => {
    assert.throws(() => {
      productService.create({
        sku: 1234,
        name: "Smartphone",
      });
    });
  });
});
