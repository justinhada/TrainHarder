import { Form, Link } from "react-router-dom";
import {
  Avatar,
  Box,
  Button,
  Checkbox,
  Container,
  FormControlLabel,
  Grid,
  TextField,
  Typography,
} from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Copyright from "../../layout/Copyright.tsx";
import PasswortInput from "../../components/PasswortInput.tsx";

const Login = () => {
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
        <Avatar sx={{ margin: 2, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>

        <Typography component="h1" variant="h4">
          Login
        </Typography>

        <Box marginTop={1}>
          <Form method="POST" action="/login" onSubmit={() => {}}>
            <TextField
              id="benutzername"
              name="benutzername"
              label="Benutzername"
              type="text"
              color="secondary"
              margin="normal"
              required={true}
              fullWidth={true}
              autoFocus={true}
            />

            <PasswortInput />

            <FormControlLabel
              control={<Checkbox value="angemeldetBleiben" color="secondary" />}
              label="Angemeldet bleiben?"
            />

            <Button
              type="submit"
              fullWidth={true}
              variant="contained"
              color="secondary"
              sx={{ marginTop: 3, marginBottom: 2 }}
            >
              Login
            </Button>

            <Grid container={true}>
              <Grid item={true} xs={true}>
                <Link to="/passwort-vergessen" title="Passwort vergessen?">
                  Passwort vergessen?
                </Link>
              </Grid>

              <Grid item={true}>
                <Link
                  to="/registrierung"
                  title="Noch nicht dabei? Hier registrieren!"
                >
                  Noch nicht dabei? Hier registrieren!
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

export default Login;
