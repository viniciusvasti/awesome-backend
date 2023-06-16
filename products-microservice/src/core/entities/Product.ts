import { UUID } from 'crypto';
import Joi from 'joi';

const ProductSchema = Joi.object({
  id: Joi.string().uuid().required(),
  sku: Joi.string().alphanum().min(8).required(),
  name: Joi.string().min(3).required(),
  description: Joi.string(),
  price: Joi.number().positive().required(),
});

export default class Product {
  id: UUID;
  sku: string;
  name: string;
  description?: string;
  price: number;

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

  validate() {
    return ProductSchema.validate(this).error || null;
  }
}
