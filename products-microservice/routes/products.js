const express = require("express");
const router = express.Router();
let mockedProducts = require("../mocks/products");

router.get("/", (req, res) => {
  console.log("Receiving request on", req.originalUrl);
  res.send(mockedProducts);
});

// By appending the RegEx "\d+" to ":sku" path param, it is defining that ":sku" needs to be one or more digits
router.get("/:sku(\\d+)", (req, res) => {
  console.log("Receiving request on", req.originalUrl);
  res.send(mockedProducts.filter(({ sku }) => sku === req.params.sku));
});

router.post("/", (req, res) => {
  console.log(
    "Receiving request on",
    req.originalUrl,
    "| having body",
    req.body
  );
  // TODO validation over body
  mockedProducts = [...mockedProducts, req.body];
  res.send(mockedProducts);
});

module.exports = router;
