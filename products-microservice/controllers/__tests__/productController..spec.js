const chai = require("chai");
const server = require("../../index");
const Product = require("../../models/Product");

const { expect } = chai;

chai.use(require("chai-http"));
chai.use(require("chai-as-promised"));

const BASE_PATH = "/api/v1/product-management/products";

describe("Products API", () => {
  beforeEach(() => {
    Product.create({ sku: "testing6", name: "product test 1", price: 1000.55 });
    Product.create({ sku: "testing7", name: "product test 2", price: 1040 });
    Product.create({ sku: "testing8", name: "product test 3", price: 300.5 });
  });

  after(async () => {
    await Product.deleteMany({ sku: /testing.*/g });
  });

  it("should return all products and 200", (done) => {
    chai
      .request(server)
      .get(BASE_PATH)
      .end(async (err, res) => {
        expect(res).to.have.status(200);
        expect(res.body).to.be.an("array");
        expect(res.body).to.have.lengthOf(await Product.count());
        done();
      });
  });

  describe("While fetching a product", () => {
    it("should return one product and 200", (done) => {
      chai
        .request(server)
        .get(`${BASE_PATH}/testing7`)
        .end((err, res) => {
          expect(res).to.have.status(200);
          expect(res.body).to.be.an("object");
          expect(res.body).to.have.property("sku", "testing7");
          expect(res.body).to.have.property("name", "product test 2");
          expect(res.body).to.have.property("price", 1040);
          done();
        });
    });

    it("should not return a product and 404", (done) => {
      const sku = 423423;
      chai
        .request(server)
        .get(`${BASE_PATH}/${sku}`)
        .end((err, res) => {
          expect(res).to.have.status(404);
          expect(res.body).to.be.an("object");
          expect(res.body).to.have.property(
            "message",
            `Product not found for SKU ${sku}`
          );
          done();
        });
    });
  });

  describe("While creating a product", () => {
    it("shout create a product and return 201", async () => {
      const count = await Product.count();
      chai
        .request(server)
        .post(BASE_PATH)
        .send({
          sku: "testing9",
          name: "product test 5",
          price: 124.0,
        })
        .end(async (err, res) => {
          expect(res).to.have.status(201);
          expect(res.body).to.be.an("object");
          expect(res.body).to.have.property("sku");
          expect(res.body).to.have.property("name");
          expect(res.body).to.have.property("price");
          expect(await Product.count()).to.equal(count + 1);
        });
    });

    it("shout not create a product and return 400", (done) => {
      chai
        .request(server)
        .post(BASE_PATH)
        .send({
          name: "Smartphone",
          price: 124.0,
        })
        .end((err, res) => {
          expect(res).to.have.status(400);
          expect(res.body).to.be.an("object");
          expect(res.body).to.have.property("message");
          expect(res.body).to.have.property("message", "Product is not valid");
          done();
        });
    });
  });
});
