import app from './app.ts';

const PORT = process.env.PORT || 8213;

app.listen(PORT, () => {
  // eslint-disable-next-line no-console
  console.log(`Koa is listening to http://localhost:${PORT}`);
});
