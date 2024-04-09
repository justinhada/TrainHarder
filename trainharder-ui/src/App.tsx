import theme from "./themes/Theme.tsx";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { CssBaseline, ThemeProvider } from "@mui/material";
import AuthProvider from "./provider/AuthProvider.tsx";
import Routes from "./Routes.tsx";

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <QueryClientProvider client={new QueryClient()}>
        <ReactQueryDevtools />
        <CssBaseline />
        <AuthProvider>
          <Routes />
        </AuthProvider>
      </QueryClientProvider>
    </ThemeProvider>
  );
};

export default App;
