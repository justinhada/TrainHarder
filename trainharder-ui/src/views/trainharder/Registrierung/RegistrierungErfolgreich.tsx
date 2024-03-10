import { Alert, Box, Container, Typography } from "@mui/material";
import { Logo } from "../../../layout/Logo.tsx";
import Copyright from "../../../layout/Copyright.tsx";
import CheckIcon from "@mui/icons-material/Check";

interface Props {
  readonly eMailAdresse: string;
}

const RegistrierungErfolgreich = ({ eMailAdresse }: Props) => {
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
          Registrierung erfolgreich
        </Typography>

        <Box marginTop={1}>
          <Alert icon={<CheckIcon fontSize="inherit" />} severity="info">
            Deine Registrierung war erfolgreich!
          </Alert>

          <Typography variant="body1" marginTop={2}>
            Wir haben dir eine Nachricht an {eMailAdresse} gesendet.
          </Typography>
        </Box>
      </Box>

      <Copyright sx={{ marginTop: 2 }} />
    </Container>
  );
};

export default RegistrierungErfolgreich;
