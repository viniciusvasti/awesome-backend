import app from "./app.ts";

const PORT = process.env.PORT || 8213;

app.listen(PORT, () => {
  console.log(`Koa is listening to http://localhost:${PORT}`);
});
