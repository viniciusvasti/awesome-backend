import { randomUUID } from "crypto";
import Product from "../entities/Product.ts";
import ProductRepository from "../ports/ProductRepository.port.ts";

export class NewProduct {
  constructor(
    public sku: string,
    public name: string,
    public price: number,
    public description?: string
  ) {}
}

export default class CreateProductUseCase {
  constructor(private productRepository: ProductRepository) {}

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
