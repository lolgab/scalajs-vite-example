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
./mill -w publicDev
```

## Production build

You need to run:

```bash
npm run build
```

Now you can find your production build in the `dist` folder.

## Testing

You can run the tests with:

```
./mill __.test
```

### Running specific tests

To test the dom we use jsdom. jsdom doesn't support modules so we need
building the app with Vite first. This is done in [project/vite.mill.scala](./project/vite.mill.scala)

Since this is slower than just building the tests with Scala.js we define two
separate test suites.

With:

```
./mill test-pure
```

You run the pure test suite which doesn't have access to the dom,

With:

```
./mill test-dom
```

You can run tests that require the dom against jsdom.
