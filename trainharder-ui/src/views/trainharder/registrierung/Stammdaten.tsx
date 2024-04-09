import { Form, Link, useNavigate, useParams } from "react-router-dom";
import {
  Box,
  CircularProgress,
  Container,
  Grid,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { Logo } from "../../../layout/Logo.tsx";
import Copyright from "../../../layout/Copyright.tsx";
import EMailAdresseInput from "../../../components/EMailAdresseInput.tsx";
import {
  usePutRegistrierung,
  useRegistrierung,
  useRegistrierungCheckBenutzername,
} from "../../../hooks/useRegistrierung.ts";
import GeburtsdatumInput from "../../../components/GeburtsdatumInput.tsx";
import dayjs from "dayjs";
import GeschlechtSelect from "../../../components/GeschlechtSelect.tsx";
import { LoadingButton } from "@mui/lab";
import BenutzernameInput from "../../../components/BenutzernameInput.tsx";
import VornameInput from "../../../components/VornameInput.tsx";
import NachnameInput from "../../../components/NachnameInput.tsx";

const Stammdaten = () => {
  const { id } = useParams();

  const [benutzername, setBenutzername] = useState("");
  const [vorname, setVorname] = useState("");
  const [nachname, setNachname] = useState("");
  const [geschlecht, setGeschlecht] = useState("");
  const date = new Date();
  date.setFullYear(date.getFullYear() - 18);
  const [geburtsdatum, setGeburtsdatum] = useState(dayjs(date));

  const [benutzernameMeldung, setBenutzernameMeldung] = useState("");
  const [vornameMeldung, setVornameMeldung] = useState("");
  const [nachnameMeldung, setNachnameMeldung] = useState("");
  const [geschlechtMeldung, setGeschlechtMeldung] = useState("");
  const [geburtsdatumMeldung, setGeburtsdatumMeldung] = useState("");

  const navigate = useNavigate();
  const {
    data: registrierung,
    error,
    isLoading: isRegistrierungLoading,
  } = useRegistrierung(id!);
  const { isPending, mutateAsync } = usePutRegistrierung(id!);
  const {
    isLoading: isCheckBenutzernameLoading,
    refetch,
    isRefetching,
  } = useRegistrierungCheckBenutzername(benutzername);

  if (isRegistrierungLoading) return <CircularProgress color="secondary" />;

  if (error || !registrierung) throw error;

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
        <Logo sx={{ margin: 2, backgroundColor: "secondary.main" }} />

        <Typography component="h1" variant="h4">
          Registrierung abschließen
        </Typography>

        <Box marginTop={1}>
          <Form>
            <Grid container spacing={1}>
              <Grid item={true} xs={12}>
                <EMailAdresseInput
                  value={registrierung.EMailAdresse}
                  disabled={true}
                />
              </Grid>

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

              <Grid item xs={12} sm={6}>
                <VornameInput
                  value={vorname}
                  onChange={(event) => {
                    event.preventDefault();

                    const value = event.target.value;
                    setVorname(value);

                    if (value === "") {
                      setVornameMeldung("Der Vorname darf nicht leer sein!");
                      return;
                    }

                    setVornameMeldung("");
                  }}
                  error={vornameMeldung !== ""}
                  helperText={vornameMeldung}
                />
              </Grid>

              <Grid item xs={12} sm={6}>
                <NachnameInput
                  value={nachname}
                  onChange={(event) => {
                    event.preventDefault();

                    const value = event.target.value;
                    setNachname(value);

                    if (value === "") {
                      setNachnameMeldung("Der Nachname darf nicht leer sein!");
                      return;
                    }

                    setNachnameMeldung("");
                  }}
                  error={nachnameMeldung !== ""}
                  helperText={nachnameMeldung}
                />
              </Grid>

              <Grid item={true} xs={12}>
                <GeschlechtSelect
                  value={geschlecht}
                  onChange={(event) => {
                    event.preventDefault();

                    const value = event.target.value;
                    setGeschlecht(value);

                    if (value === "") {
                      setGeschlechtMeldung(
                        "Das Geschlecht darf nicht leer sein!",
                      );
                      return;
                    }

                    setGeschlechtMeldung("");
                  }}
                  error={geschlechtMeldung !== ""}
                  helperText={geschlechtMeldung}
                />
              </Grid>

              <Grid item={true} xs={12}>
                <GeburtsdatumInput
                  value={geburtsdatum}
                  onChange={(value) => {
                    if (value === null) {
                      setGeburtsdatumMeldung(
                        "Das Geburtsdatum darf nicht leer sein!",
                      );
                      return;
                    }

                    setGeburtsdatum(value);

                    const mindestens18 = new Date();
                    mindestens18.setFullYear(mindestens18.getFullYear() - 18);

                    if (value?.isAfter(mindestens18)) {
                      setGeburtsdatumMeldung(
                        "Du musst mindestens 18 Jahre alt sein!",
                      );
                      return;
                    }

                    setGeburtsdatumMeldung("");
                  }}
                  error={geburtsdatumMeldung !== ""}
                  helperText={geburtsdatumMeldung}
                />
              </Grid>
            </Grid>

            <LoadingButton
              type="submit"
              sx={{ marginTop: 3, marginBottom: 2 }}
              fullWidth={true}
              variant="contained"
              color="secondary"
              loading={isRefetching || isCheckBenutzernameLoading || isPending}
              disabled={
                benutzernameMeldung !== "" ||
                vornameMeldung !== "" ||
                nachnameMeldung !== "" ||
                geschlechtMeldung !== "" ||
                geburtsdatumMeldung !== "" ||
                benutzername === "" ||
                vorname === "" ||
                nachname === "" ||
                geschlecht === "" ||
                geburtsdatum === null
              }
              onClick={(event) => {
                event.preventDefault();

                refetch().then(async ({ data: checkBenutzername }) => {
                  if (checkBenutzername!.data) {
                    setBenutzernameMeldung(
                      "Der Benutzername ist bereits registriert!",
                    );
                  } else {
                    await mutateAsync({
                      benutzername: benutzername,
                      vorname: vorname,
                      nachname: nachname,
                      geschlecht: geschlecht,
                      geburtsdatum: geburtsdatum,
                    }).then((response) => {
                      if (response.status === 200)
                        navigate("/registrierung/abgeschlossen");
                    });
                  }
                });
              }}
            >
              Registrierung abschließen
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

export default Stammdaten;
