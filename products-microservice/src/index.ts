import "dotenv/config";
import Koa from "koa";
import pinoLogger from "koa-pino-logger";

const app = new Koa();
app.silent = true; // disable console.errors

const pino = pinoLogger({
  level: process.env.LOG_LEVEL || "info",
});

const PORT = process.env.PORT || 8213;

// pino logger
app.use(pino);

// Global error handling
app.on("error", (err, ctx) => {
  pino.logger.error(err, ctx);
});

// logging
app.use(async (ctx, next) => {
  ctx.log.debug("request received");
  await next();
  const rt = ctx.response.get("X-Response-Time");
  ctx.log.info(`${ctx.method} ${ctx.url} - ${rt}`);
});

// x-response-time
app.use(async (ctx, next) => {
  const start = Date.now();
  await next();
  const ms = Date.now() - start;
  ctx.set("X-Response-Time", `${ms}ms`);
});

// response
app.use(async (ctx) => {
  ctx.body = "Hello World";
});

app.listen(PORT, () => {
  console.log(`Koa is listening to http://localhost:${PORT}`);
});
