import { spawn, spawnSync } from "child_process";
import { defineConfig } from "vite";

var alias = isDev()
  ? runMillCommand("chart.publicDev")
  : runMillCommand("chart.publicProd");

export default defineConfig({
  resolve: {
    alias: alias,
  },
});

function isDev() {
  return process.env.NODE_ENV !== "production";
}

function runMillCommand(command) {
  const result = spawnSync("./mill", ["show", command], {
    stdio: [
      "pipe", // StdIn.
      "pipe", // StdOut.
      "inherit", // StdErr.
    ],
  });

  // Run watch mode as well
  spawn("./mill", ["-w", command], {
    stdio: [
      "inherit", // StdIn.
      "inherit", // StdOut.
      "inherit", // StdErr.
    ],
  })

  return JSON.parse(result.stdout);
}
