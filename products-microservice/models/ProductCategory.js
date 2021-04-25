const db = require("../database/db");

const ProductCategorySchema = new db.Schema({
  name: { type: String, require: true, lowercase: true, index: true },
  createdAt: { type: Date, default: Date.now, select: false },
});

module.exports = db.model("ProductCategory", ProductCategorySchema);
