import { randomUUID } from "crypto";
import Product from "./Product.ts";

describe("Product entity", () => {
  it("should create a valid product", () => {
    const product = new Product(
      randomUUID(),
      "42363t4656",
      "Adidas Superstar",
      80
    );

    expect(product.validate()).toStrictEqual(null);
  });

  it("should return an error if sku is invalid", () => {
    const product = new Product(
      randomUUID(),
      "inv alid",
      "Adidas Superstar",
      80
    );

    expect(product.validate()).toEqual(
      new Error('"sku" must only contain alpha-numeric characters')
    );
  });

  it("should return an error if name is invalid", () => {
    const product = new Product(randomUUID(), "42363t4656", "Ad", 80);

    expect(product.validate()).toEqual(
      new Error('"name" length must be at least 3 characters long')
    );
  });

  it("should return an error if price is invalid", () => {
    const product = new Product(
      randomUUID(),
      "42363t4656",
      "Adidas Superstar",
      -80
    );

    expect(product.validate()).toEqual(
      new Error('"price" must be a positive number')
    );
  });

  it("should return an error if id is invalid", () => {
    const product = new Product(
      "3-3-3-3-3",
      "42363t4656",
      "Adidas Superstar",
      80
    );

    expect(product.validate()).toEqual(new Error('"id" must be a valid GUID'));
  });

  it("should return an error if sku is missing", () => {
    const product = new Product(randomUUID(), "", "Adidas Superstar", 80);

    expect(product.validate()).toEqual(
      new Error('"sku" is not allowed to be empty')
    );
  });

  it("should return an error if name is missing", () => {
    const product = new Product(randomUUID(), "42363t4656", "", 80);

    expect(product.validate()).toEqual(
      new Error('"name" is not allowed to be empty')
    );
  });

  it("should return an error if price is missing", () => {
    const product = new Product(
      randomUUID(),
      "42363t4656",
      "Adidas Superstar",
      0
    );

    expect(product.validate()).toEqual(
      new Error('"price" must be a positive number')
    );
  });
});
