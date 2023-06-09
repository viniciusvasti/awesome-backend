import Koa from "koa";

const app = new Koa();
app.silent = true; // disable console.errors

const PORT = process.env.PORT || 8213;

// response
app.use(async (ctx) => {
  ctx.body = "Hello World";
});

app.listen(PORT, () => {
  console.log(`Koa is listening to http://localhost:${PORT}`);
});
