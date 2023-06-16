import { UUID, randomUUID } from "crypto";
import Product from "../entities/Product.ts";
import ProductRepository from "../ports/ProductRepository.port.ts";

export class ExistingProduct {
  constructor(
    public id: UUID,
    public sku: string,
    public name: string,
    public price: number,
    public description?: string
  ) {}
}

export default class CreateProductUseCase {
  constructor(private productRepository: ProductRepository) {}

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
