import Product from '../entities/Product.ts';
import ProductRepository from '../ports/ProductRepository.port.ts';

export default class GetProductsListUseCase {
  private readonly productRepository: ProductRepository;

  constructor(productRepository: ProductRepository) {
    this.productRepository = productRepository;
  }

  async execute(): Promise<Product[]> {
    return await this.productRepository.listAll();
  }
}
