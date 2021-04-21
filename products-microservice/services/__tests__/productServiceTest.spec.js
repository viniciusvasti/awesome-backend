const chai = require("chai");
const productServiceFactory = require("../productService");
const mockedProducts = require("../../mocks/products");

chai.use(require("chai-as-promised"));
const { expect } = chai;

const mockedProductRepository = {
  findAll: () => mockedProducts,
  findBySku: (sku) => mockedProducts[0],
  insert: (product) => product,
};

const productService = productServiceFactory(mockedProductRepository);

describe("Product Service get all", () => {
  it("should return all products", async () => {
    const products = await productService.getAll();
    expect(products).to.have.lengthOf(3);
  });
});

describe("Product Service get one", () => {
  it("should return one product", async () => {
    const product = await productService.getBySku(0001);
    expect(product).to.equal(mockedProducts.find((p) => p.sku == 0001));
  });
});

describe("Product Service create product", () => {
  it("should return create a product", async () => {
    const product = await productService.create({
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

  it("should throws since product haven't a sku", async () => {
    await expect(
      productService.create({
        name: "Smartphone",
        price: 124.0,
      })
    ).to.be.rejectedWith(Error);
  });
  it("should throws since product haven't a name", async () => {
    await expect(
      productService.create({
        sku: 1234,
        price: 124.0,
      })
    ).to.be.rejectedWith(Error);
  });
  it("should throws since product haven't a price", async () => {
    await expect(
      productService.create({
        sku: 1234,
        name: "Smartphone",
      })
    ).to.be.rejectedWith(Error);
  });
});
