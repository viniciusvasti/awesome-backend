const expect = require("chai").expect;
const assert = require("chai").assert;
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
    expect(products).to.have.lengthOf(3);
  });
});

describe("Product Service get one", () => {
  it("should return one product", () => {
    const product = productService.getBySku(0001);
    expect(product).to.equal(mockedProducts.find((p) => p.sku == 0001));
  });
});

describe("Product Service create product", () => {
  it("should return create a product", () => {
    const product = productService.create({
      sku: 1234,
      name: "Smartphone",
      price: 124.0,
    });
    expect(product).to.deep.equal({
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
