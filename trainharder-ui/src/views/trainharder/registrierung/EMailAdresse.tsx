import { Box, Container, Grid, Typography } from "@mui/material";
import { Form, Link, useNavigate } from "react-router-dom";
import Copyright from "../../../layout/Copyright.tsx";
import { Logo } from "../../../layout/Logo.tsx";
import EMailAdresseInput from "../../../components/EMailAdresseInput.tsx";
import { useRegistrierungCheckEMail } from "../../../hooks/useRegistrierung.ts";
import { useState } from "react";
import { LoadingButton } from "@mui/lab";

interface Props {
  eMailAdresse: string;
  setEMailAdresse: (value: string) => void;
}

const EMailAdresse = ({ eMailAdresse, setEMailAdresse }: Props) => {
  const [meldung, setMeldung] = useState("");

  const navigate = useNavigate();
  const { isLoading, refetch, isRefetching } =
    useRegistrierungCheckEMail(eMailAdresse);

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
          Mit TrainHarder durchstarten
        </Typography>

        <Box marginTop={1}>
          <Form>
            <Grid container spacing={1}>
              <Grid item={true} xs={12}>
                <EMailAdresseInput
                  autoFocus={true}
                  value={eMailAdresse}
                  onChange={(event) => {
                    event.preventDefault();

                    const value = event.target.value;
                    setEMailAdresse(value);

                    if (value === "") {
                      setMeldung("Die E-Mail-Adresse darf nicht leer sein!");
                      return;
                    }

                    if (!value.includes("@")) {
                      setMeldung("Die E-Mail-Adresse muss ein '@' enthalten!");
                      return;
                    }

                    setMeldung("");
                  }}
                  error={meldung !== ""}
                  helperText={meldung}
                />
              </Grid>
            </Grid>

            <LoadingButton
              type="submit"
              sx={{ marginTop: 3, marginBottom: 2 }}
              fullWidth={true}
              variant="contained"
              color="secondary"
              loading={isRefetching || isLoading}
              disabled={meldung !== "" || eMailAdresse === ""}
              onClick={(event) => {
                event.preventDefault();

                refetch().then(({ data: checkEMail }) => {
                  if (checkEMail!.data) {
                    setMeldung("Die E-Mail-Adresse ist bereits registriert!");
                  } else {
                    navigate("/registrierung/passwort");
                  }
                });
              }}
            >
              Mit E-Mail-Adresse registrieren
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

export default EMailAdresse;
