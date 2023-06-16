import { randomUUID } from 'crypto';
import Product from '../entities/Product.ts';
import ProductRepository from '../ports/ProductRepository.port.ts';

export class NewProduct {
  public sku: string;
  public name: string;
  public price: number;
  public description?: string;

  constructor(sku: string, name: string, price: number, description?: string) {
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

  async execute(newProduct: NewProduct): Promise<Product> {
    const product = new Product(
      randomUUID(),
      newProduct.sku,
      newProduct.name,
      newProduct.price,
      newProduct.description
    );
    const validationErrors = product.validate();
    if (validationErrors) throw validationErrors;
    return await this.productRepository.create(product);
  }
}
