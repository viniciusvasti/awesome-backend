import { UUID } from 'crypto';
import Product from '../entities/Product.ts';

interface ProductRepository {
  create(product: Product): Promise<Product>;
  update(product: Product): Promise<Product>;
  getById(id: UUID): Promise<Product | null>;
  listAll(): Promise<Product[]>;
}

export default ProductRepository;
