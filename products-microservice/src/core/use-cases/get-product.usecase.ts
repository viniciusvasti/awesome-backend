import { UUID, randomUUID } from "crypto";
import Product from "../entities/Product.ts";
import ProductRepository from "../ports/ProductRepository.port.ts";

export default class GetProductUseCase {
  constructor(private productRepository: ProductRepository) {}

  async execute(id: UUID): Promise<Product | null> {
    return await this.productRepository.getById(id);
  }
}
