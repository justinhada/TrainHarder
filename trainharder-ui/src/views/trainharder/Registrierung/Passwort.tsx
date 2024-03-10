import { Alert, Box, Container, Grid, Typography } from "@mui/material";
import { Form, Link, useNavigate } from "react-router-dom";
import Copyright from "../../../layout/Copyright.tsx";
import { Logo } from "../../../layout/Logo.tsx";
import PasswortInput from "../../../components/PasswortInput.tsx";
import { useState } from "react";
import CheckIcon from "@mui/icons-material/Check";
import { usePostRegistrierungen } from "../../../hooks/useRegistrierung.ts";
import { LoadingButton } from "@mui/lab";

interface Props {
  eMailAdresse: string;
}

const Passwort = ({ eMailAdresse }: Props) => {
  const navigate = useNavigate();
  const [passwort, setPasswort] = useState("");
  const [meldung, setMeldung] = useState("");

  const { isPending, isSuccess, mutate } = usePostRegistrierungen();

  if (isSuccess) navigate("/registrierung/erfolgreich");

  const hasLowerCase = (passwort: string) => passwort.toUpperCase() != passwort;
  const hasUpperCase = (passwort: string) => passwort.toLowerCase() != passwort;
  const hasNumber = (passwort: string) => /\d/.test(passwort);
  const hasSpecialCharacter = (passwort: string) =>
    /[ `!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/.test(passwort);
  const hasLength = (passwort: string) => passwort.length >= 12;

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
        {/*<IconButton>*/}
        {/*  <ArrowBackIcon />*/}
        {/*</IconButton>*/}

        <Logo sx={{ margin: 2, backgroundColor: "secondary.main" }} />

        <Typography component="h1" variant="h4" marginBottom={2}>
          Passwort erstellen
        </Typography>

        <Alert icon={<CheckIcon fontSize="inherit" />} severity="info">
          {eMailAdresse} ist nicht registriert und kann verwendet werden!
        </Alert>

        <Typography variant="body2" marginTop={2}>
          Gib das Passwort ein, das du für deinen Benutzer verwenden möchtest.
        </Typography>

        <Box marginTop={1}>
          <Form>
            <PasswortInput
              autoFocus={true}
              value={passwort}
              onChange={(event) => {
                event.preventDefault();

                const value = event.target.value;
                setPasswort(value);

                if (value === "") {
                  setMeldung("Das Passwort darf nicht leer sein!");
                  return;
                }

                if (
                  !hasLowerCase(value) ||
                  !hasUpperCase(value) ||
                  !hasNumber(value) ||
                  !hasSpecialCharacter(value) ||
                  !hasLength(value)
                ) {
                  setMeldung("Das Passwort ist ungültig!");
                  return;
                }

                setMeldung("");
              }}
              error={meldung !== ""}
              helperText={meldung}
            />

            <LoadingButton
              type="submit"
              sx={{ marginTop: 3, marginBottom: 2 }}
              fullWidth={true}
              variant="contained"
              color="secondary"
              loading={isPending}
              disabled={meldung !== ""}
              onClick={(event) => {
                event.preventDefault();

                mutate({ EMailAdresse: eMailAdresse, passwort: passwort });
              }}
            >
              Mit Passwort registrieren
            </LoadingButton>

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
