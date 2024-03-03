import { Box, Button, Container, Grid, Typography } from "@mui/material";
import { Form, Link } from "react-router-dom";
import Copyright from "../../../layout/Copyright.tsx";
import { Logo } from "../../../layout/Logo.tsx";
import PasswortInput from "../../../components/PasswortInput.tsx";

const Passwort = () => {
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
          Passwort erstellen
        </Typography>

        <Typography variant="body2" marginTop={2}>
          Die E-Mail-Adresse scheint noch nicht vergeben zu sein.
        </Typography>

        <Typography variant="body2" marginTop={2}>
          Gib das Kennwort ein, das du für deinen Benutzer verwenden möchtest.
        </Typography>

        <Box marginTop={1}>
          <Form method="POST" onSubmit={() => {}}>
            <PasswortInput />

            <Button
              type="submit"
              fullWidth={true}
              variant="contained"
              color="secondary"
              sx={{ marginTop: 3, marginBottom: 2 }}
            >
              Mit Passwort registrieren
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

export default Passwort;
