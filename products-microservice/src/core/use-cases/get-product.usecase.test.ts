import sinon from 'sinon';
import Product from '../entities/Product.ts';
import ProductRepository from '../ports/ProductRepository.port.ts';
import GetProductUseCase from './get-product.usecase.ts';
import { randomUUID } from 'crypto';

const existingUuid = randomUUID();
const nonExistingUuid = randomUUID();

const product = new Product(
  existingUuid,
  '12345678',
  'Adidas Superstar',
  80,
  'A very nice pair of shoes'
);

class ProductRepositoryMock implements ProductRepository {
  create = sinon.stub();
  getById = sinon.stub();
  listAll = sinon.stub();
  update = sinon.stub();
}

describe('GetProductUseCase', () => {
  afterEach(() => {
    sinon.restore();
  });

  describe('execute', () => {
    it('with a valid product id should return a product', async () => {
      // Arrange
      const productRepository = new ProductRepositoryMock();
      productRepository.getById.resolves(product);

      const getProductUseCase = new GetProductUseCase(productRepository);

      // Act
      const createdProduct = await getProductUseCase.execute(existingUuid);

      // Assert
      expect(createdProduct).toStrictEqual(product);
      expect(productRepository.getById.calledOnce).toBe(true);
    });

    it('with an invalid product id should throw an error', async () => {
      // Arrange
      const productRepository = new ProductRepositoryMock();
      productRepository.getById.resolves(null);

      const getProductUseCase = new GetProductUseCase(productRepository);

      // Act
      const result = await getProductUseCase.execute(nonExistingUuid);

      // Assert
      expect(result).toBeNull();
      expect(productRepository.getById.calledOnce).toBe(true);
    });
  });
});
