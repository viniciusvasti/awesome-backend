import Product from "../entities/Product.ts";
import ProductRepository from "../ports/ProductRepository.port.ts";

export default class GetProductsListUseCase {
  constructor(private productRepository: ProductRepository) {}

  async execute(): Promise<Product[]> {
    return await this.productRepository.listAll();
  }
}
