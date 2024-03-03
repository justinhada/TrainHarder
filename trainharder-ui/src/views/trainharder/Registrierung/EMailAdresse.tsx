import {
  Box,
  Button,
  Container,
  Grid,
  TextField,
  Typography,
} from "@mui/material";
import { Form, Link } from "react-router-dom";
import Copyright from "../../../layout/Copyright.tsx";
import { Logo } from "../../../layout/Logo.tsx";

const Index = () => {
  return (
    <Container
      sx={{
        backgroundColor: "primary.main",
        paddingY: 3,
        borderRadius: 4,
      }}
      maxWidth="sm"
    >
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Logo
          cssProperties={{ margin: 2, backgroundColor: "secondary.main" }}
        />

        <Typography component="h1" variant="h4">
          Mit TrainHarder durchstarten
        </Typography>

        <Box marginTop={1}>
          <Form method="POST" onSubmit={() => {}}>
            <TextField
              id="eMailAdresse"
              name="eMailAdresse"
              label="E-Mail-Adresse"
              type="email"
              color="secondary"
              margin="normal"
              required={true}
              fullWidth={true}
              autoFocus={true}
            />

            <Button
              type="submit"
              fullWidth={true}
              variant="contained"
              color="secondary"
              sx={{ marginTop: 3, marginBottom: 2 }}
            >
              Mit E-Mail-Adresse registrieren
            </Button>

            <Grid container={true} justifyContent="flex-end">
              <Grid item={true}>
                <Link to="/login" title="Bereits dabei? Hier einloggen!">
                  Bereits dabei? Hier einloggen!
                </Link>
              </Grid>
            </Grid>
          </Form>
        </Box>
      </Box>

      <Copyright sx={{ marginTop: 2 }} />
    </Container>
  );
};

export default Index;
