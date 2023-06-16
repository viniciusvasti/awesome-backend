import Crypto from "crypto";
import Product from "../entities/Product.ts";
import UpdateProductUseCase, {
  ExistingProduct,
} from "./update-product.usecase.ts";
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

describe("UpdateProductUseCase", () => {
  afterEach(() => {
    sinon.restore();
  });

  describe("execute", () => {
    it("with a valid product should update a product", async () => {
      // Arrange
      const existingProduct = new ExistingProduct(
        uuid,
        "12345678",
        "Adidas Superstar",
        80,
        "A very nice pair of shoes"
      );
      const productRepository = new ProductRepositoryMock();

      const updateProductUseCase = new UpdateProductUseCase(productRepository);

      // Act
      const updatedProduct = await updateProductUseCase.execute(
        existingProduct
      );

      // Assert
      expect(updatedProduct).toStrictEqual(product);
      expect(productRepository.update.calledOnce).toBe(true);
    });

    it("with an invalid product should throw an error", async () => {
      // Arrange
      const existingProduct = new ExistingProduct(
        uuid,
        "1234567",
        "Adidas Superstar",
        80,
        "A very nice pair of shoes"
      );
      const productRepository = new ProductRepositoryMock();

      const updateProductUseCase = new UpdateProductUseCase(productRepository);

      // Act
      await expect(
        updateProductUseCase.execute(existingProduct)
      ).rejects.toEqual(
        new Error('"sku" length must be at least 8 characters long')
      );

      // Assert
      expect(productRepository.update.notCalled).toBe(true);
    });
  });
});
