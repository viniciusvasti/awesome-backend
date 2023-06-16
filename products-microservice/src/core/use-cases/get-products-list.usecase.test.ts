import sinon from "sinon";
import Product from "../entities/Product.ts";
import ProductRepository from "../ports/ProductRepository.port.ts";
import GetProductsListUseCase from "./get-products-list.usecase.ts";
import { UUID } from "crypto";
import { productsList } from "../../../test/mocks/products.mock.json";

const products = productsList.map(
  (product) =>
    new Product(
      product.id as UUID,
      product.sku,
      product.name,
      product.price,
      product.description
    )
);

class ProductRepositoryMock implements ProductRepository {
  create = sinon.stub();
  getById = sinon.stub();
  listAll = sinon.stub();
  update = sinon.stub();
}

describe("GetProductsListUseCase", () => {
  afterEach(() => {
    sinon.restore();
  });

  describe("execute", () => {
    it("when there are products, should return a list of products", async () => {
      // Arrange
      const productRepository = new ProductRepositoryMock();
      productRepository.listAll.resolves(products);

      const getProductsListUseCase = new GetProductsListUseCase(
        productRepository
      );

      // Act
      const foundProducts = await getProductsListUseCase.execute();

      // Assert
      expect(foundProducts).toStrictEqual(products);
      expect(productRepository.listAll.calledOnce).toBe(true);
    });

    it("when there are no products, should return an empty list", async () => {
      // Arrange
      const productRepository = new ProductRepositoryMock();
      productRepository.listAll.resolves([]);

      const getProductsListUseCase = new GetProductsListUseCase(
        productRepository
      );

      // Act
      const foundProducts = await getProductsListUseCase.execute();

      // Assert
      expect(foundProducts).toStrictEqual([]);
      expect(productRepository.listAll.calledOnce).toBe(true);
    });
  });
});
