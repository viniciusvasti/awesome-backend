import { UUID } from 'crypto';
import Product from '../entities/Product.ts';
import ProductRepository from '../ports/ProductRepository.port.ts';

export class ExistingProduct {
  public id: UUID;
  public sku: string;
  public name: string;
  public price: number;
  public description?: string;

  constructor(
    id: UUID,
    sku: string,
    name: string,
    price: number,
    description?: string
  ) {
    this.id = id;
    this.sku = sku;
    this.name = name;
    this.price = price;
    this.description = description;
  }
}

export default class CreateProductUseCase {
  private readonly productRepository: ProductRepository;

  constructor(productRepository: ProductRepository) {
    this.productRepository = productRepository;
  }

  async execute(existingProduct: ExistingProduct): Promise<Product> {
    const product = new Product(
      existingProduct.id,
      existingProduct.sku,
      existingProduct.name,
      existingProduct.price,
      existingProduct.description
    );
    const validationErrors = product.validate();
    if (validationErrors) throw validationErrors;
    return await this.productRepository.update(product);
  }
}
