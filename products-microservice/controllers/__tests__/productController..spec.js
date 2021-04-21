const chai = require("chai");
const chaiHttp = require("chai-http");
const server = require("../../index");

const { assert, expect } = chai;

chai.use(chaiHttp);

const BASE_PATH = "/api/v1/product-management/products";

describe("Products API", () => {
  let productsDB = [];

  beforeEach(() => {
    productsDB = require("../../mocks/products");
  });

  it("should return all products and 200", (done) => {
    chai
      .request(server)
      .get(BASE_PATH)
      .end((err, res) => {
        expect(res).to.have.status(200);
        expect(res.body).to.be.an("array");
        expect(res.body).to.have.lengthOf(3);
        done();
      });
  });

  describe("While fetching a product", () => {
    it("should return one product and 200", (done) => {
      chai
        .request(server)
        .get(`${BASE_PATH}/0001`)
        .end((err, res) => {
          expect(res).to.have.status(200);
          expect(res.body).to.be.an("object");
          expect(res.body).to.have.property("sku");
          expect(res.body).to.have.property("name");
          expect(res.body).to.have.property("price");
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
    it("shout create a product and return 201", (done) => {
      chai
        .request(server)
        .post(BASE_PATH)
        .send({
          sku: 1234,
          name: "Smartphone",
          price: 124.0,
        })
        .end((err, res) => {
          expect(res).to.have.status(201);
          expect(res.body).to.be.an("object");
          expect(res.body).to.have.property("sku");
          expect(res.body).to.have.property("name");
          expect(res.body).to.have.property("price");
          done();
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
