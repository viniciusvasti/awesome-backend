const db = require("../database/db");

const ProductSchema = new db.Schema({
  sku: { type: String, require: true },
  name: { type: String, require: true, lowercase: true },
  price: { type: Number, require: true },
  createdAt: { type: Date, default: Date.now, select: false },
});

module.exports = db.model("Product", ProductSchema);