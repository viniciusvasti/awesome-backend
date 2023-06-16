import Crypto from "crypto";
import Product from "../entities/Product.ts";
import CreateProductUseCase, { NewProduct } from "./create-product.usecase.ts";
import { createSandbox } from "sinon";
import ProductRepository from "../ports/ProductRepository.port.ts";

const sinon = createSandbox();

const uuid = Crypto.randomUUID();

const product = new Product(
  uuid,
  "12345678",
  "Adidas Superstar",
  80,
  "A very nice pair of shoes"
);

class ProductRepositoryMock implements ProductRepository {
  create = sinon.stub().resolves(product);
  getById = sinon.stub().resolves(null);
  listAll = sinon.stub().resolves([]);
  update = sinon.stub().resolves(product);
}

describe("CreateProductUseCase", () => {
  afterEach(() => {
    sinon.restore();
  });

  describe("execute", () => {
    it("with a valid product should create a product", async () => {
      // Arrange
      const newProduct = new NewProduct(
        "12345678",
        "Adidas Superstar",
        80,
        "A very nice pair of shoes"
      );
      const productRepository = new ProductRepositoryMock();
      sinon.stub(Crypto, "randomUUID").returns(uuid);

      const createProductUseCase = new CreateProductUseCase(productRepository);

      // Act
      const createdProduct = await createProductUseCase.execute(newProduct);

      // Assert
      expect(createdProduct).toStrictEqual(product);
      expect(productRepository.create.calledOnce).toBe(true);
    });

    it("with an invalid product should throw an error", async () => {
      // Arrange
      const newProduct = new NewProduct(
        "1234567",
        "Adidas Superstar",
        80,
        "A very nice pair of shoes"
      );
      const productRepository = new ProductRepositoryMock();
      sinon.stub(Crypto, "randomUUID").returns(uuid);

      const createProductUseCase = new CreateProductUseCase(productRepository);

      // Act
      await expect(createProductUseCase.execute(newProduct)).rejects.toEqual(
        new Error('"sku" length must be at least 8 characters long')
      );

      // Assert
      expect(productRepository.create.notCalled).toBe(true);
    });
  });
});
