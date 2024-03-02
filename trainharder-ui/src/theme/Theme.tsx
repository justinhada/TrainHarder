import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    primary: {
      main: "#edf2f4",
    },
    secondary: {
      main: "#2b2d42",
    },
    background: {},
    action: {
      hover: "#8d99ae",
    },
  },
  typography: {
    fontFamily: ["League Spartan", "serif"].join(","),
  },
  components: {
    MuiTypography: {
      styleOverrides: {
        caption: {
          color: "#70809080",
          fontSize: 16,
        },
      },
    },
  },
});

export default theme;
