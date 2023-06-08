import { spawnSync } from "child_process";
import { defineConfig } from "vite";

function alias(mode) {
  if (mode === "development") return runMillTask("publicDev");
  if (mode === "production") return runMillTask("publicProd");
  const prefix = "test:";
  if (mode.startsWith(prefix))
    return {
      "@public": mode.substring(prefix.length),
    };
}

export default defineConfig(({ mode }) => {
  return {
    resolve: {
      alias: alias(mode),
    },
  };
});

function runMillTask(task) {
  const result = spawnSync("./mill", ["show", task], {
    stdio: [
      "pipe", // StdIn.
      "pipe", // StdOut.
      "inherit", // StdErr.
    ],
  });

  return JSON.parse(result.stdout);
}
