import { Box, Container, Typography, useTheme } from "@mui/material";

const NavigationBar = () => {
  const theme = useTheme();

  return (
    <Container
      component="nav"
      maxWidth={false}
      sx={{ backgroundColor: theme.palette.secondary.main }}
    >
      <Box
        sx={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "space-between",
          alignItems: "center",
        }}
      >
        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            alignItems: "center",
          }}
        >
          <Typography variant="h4" sx={{ color: theme.palette.primary.main }}>
            TrainHarder{/*TODO: Badge: Basic, Pro, Coach, ...*/}
          </Typography>
        </Box>

        <Box
          sx={{
            display: "flex",
            flexDirection: "row",
            justifyContent: "space-between",
            padding: "10px",
          }}
        ></Box>
      </Box>
    </Container>
  );
};

export default NavigationBar;
