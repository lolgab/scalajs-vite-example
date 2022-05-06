# Scala.js and Vite example

This example project shows how to use [Vite](https://vitejs.dev) together
with [Scala.js](https://scala-js.org).

## Getting started

You need to:

- Install the javascript libraries:

```bash
npm install
```

- Create the bundle:

```bash
./mill --no-server -j 0 -w chart.publicDev
```

- Run Vite dev server (in a separate terminal):

```bash
npm run dev
```

## Production build

You need to run:

```bash
npm run build
```

Now you can find your production build in the `dist` folder.
