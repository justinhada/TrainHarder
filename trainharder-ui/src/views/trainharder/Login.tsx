import { Form, Link, useNavigate } from "react-router-dom";
import {
  Alert,
  Avatar,
  Box,
  Checkbox,
  Container,
  FormControlLabel,
  Grid,
  Typography,
} from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Copyright from "../../layout/Copyright.tsx";
import PasswortInput from "../../components/PasswortInput.tsx";
import { useState } from "react";
import BenutzernameInput from "../../components/BenutzernameInput.tsx";
import { LoadingButton } from "@mui/lab";
import { usePostLogin } from "../../hooks/useLogin.ts";
import isGueltig from "../../utils/PasswortUtils.ts";
import { useAuth } from "../../provider/AuthProvider.tsx";

const Login = () => {
  const { setToken } = useAuth();
  const [benutzername, setBenutzername] = useState("");
  const [passwort, setPasswort] = useState("");

  const [meldung, setMeldung] = useState("");
  const [benutzernameMeldung, setBenutzernameMeldung] = useState("");
  const [passwortMeldung, setPasswortMeldung] = useState("");

  const navigate = useNavigate();
  const { isPending, mutateAsync } = usePostLogin();

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
          <Form>
            <Grid container spacing={1}>
              {meldung && (
                <Grid item={true} xs={12}>
                  <Alert severity="error" sx={{ marginY: 1 }}>
                    {meldung}
                  </Alert>
                </Grid>
              )}

              <Grid item={true} xs={12}>
                <BenutzernameInput
                  autoFocus={true}
                  value={benutzername}
                  onChange={(event) => {
                    event.preventDefault();

                    const value = event.target.value;
                    setBenutzername(value);

                    if (value === "") {
                      setBenutzernameMeldung(
                        "Der Benutzername darf nicht leer sein!",
                      );
                      return;
                    }

                    setBenutzernameMeldung("");
                  }}
                  error={benutzernameMeldung !== ""}
                  helperText={benutzernameMeldung}
                />
              </Grid>

              <Grid item={true} xs={12}>
                <PasswortInput
                  value={passwort}
                  onChange={(event) => {
                    event.preventDefault();

                    const value = event.target.value;
                    setPasswort(value);

                    if (value === "") {
                      setPasswortMeldung("Das Passwort darf nicht leer sein!");
                      return;
                    }

                    if (!isGueltig(value)) {
                      setPasswortMeldung("Das Passwort ist ungültig!");
                      return;
                    }

                    setPasswortMeldung("");
                  }}
                  error={passwortMeldung !== ""}
                  helperText={passwortMeldung}
                />
              </Grid>
            </Grid>

            <FormControlLabel
              control={<Checkbox value="angemeldetBleiben" color="secondary" />}
              label="Angemeldet bleiben?"
            />

            <LoadingButton
              type="submit"
              sx={{ marginTop: 3, marginBottom: 2 }}
              fullWidth={true}
              variant="contained"
              color="secondary"
              loading={isPending}
              disabled={
                benutzernameMeldung !== "" ||
                passwortMeldung !== "" ||
                benutzername === "" ||
                passwort === ""
              }
              onClick={(event) => {
                event.preventDefault();

                mutateAsync({
                  benutzername: benutzername,
                  passwort: passwort,
                })
                  .then((response) => {
                    if (response.status === 200) {
                      setToken(response.data.token);
                      navigate("/");
                    } else {
                      setMeldung(
                        "Der Benutzername oder das Passwort ist falsch!",
                      );
                    }
                  })
                  .catch((error) => {
                    if (error.response.status === 401) {
                      setMeldung(
                        "Der Benutzername oder das Passwort ist falsch!",
                      );
                    }
                  });
              }}
            >
              Login
            </LoadingButton>

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
