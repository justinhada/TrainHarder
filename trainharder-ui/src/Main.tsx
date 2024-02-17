import React from "react";
import ReactDOM from "react-dom/client";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { RouterProvider } from "react-router-dom";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import "@fontsource/league-spartan/500.css";
import { CssBaseline, ThemeProvider } from "@mui/material";
import router from "./Routes.tsx";
import theme from "./theme/Theme.tsx";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <QueryClientProvider client={new QueryClient()}>
        <ReactQueryDevtools />
        <CssBaseline />
        <RouterProvider router={router} />
      </QueryClientProvider>
    </ThemeProvider>
  </React.StrictMode>,
);
