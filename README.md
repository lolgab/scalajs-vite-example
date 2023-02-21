# Scala.js and Vite example

This example project shows how to use [Vite](https://vitejs.dev) together
with [Scala.js](https://scala-js.org).

## Getting started

You need to:

- Install the javascript libraries:

```bash
npm install
```

- Run the Vite Dev Server:

```bash
npm run dev
```

- Start rebuilding the Javascript code on every source change  (in another terminal):

```bash
./mill -w chart.publicDev
```

## Production build

You need to run:

```bash
npm run build
```

Now you can find your production build in the `dist` folder.
