const mongoose = require("mongoose");

mongoose.connect("mongodb://localhost:27050/products", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});
mongoose.Promise = global.Promise;

module.exports = mongoose;
