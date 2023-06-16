import { UUID } from 'crypto';
import Product from '../entities/Product.ts';
import ProductRepository from '../ports/ProductRepository.port.ts';

export default class GetProductUseCase {
  private readonly productRepository: ProductRepository;

  constructor(productRepository: ProductRepository) {
    this.productRepository = productRepository;
  }

  async execute(id: UUID): Promise<Product | null> {
    return await this.productRepository.getById(id);
  }
}
