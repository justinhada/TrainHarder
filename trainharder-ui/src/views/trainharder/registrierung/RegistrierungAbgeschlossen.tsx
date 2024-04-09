import { Alert, Box, Container, Typography } from "@mui/material";
import { Logo } from "../../../layout/Logo.tsx";
import CheckIcon from "@mui/icons-material/Check";
import Copyright from "../../../layout/Copyright.tsx";

const RegistrierungAbgeschlossen = () => {
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
            Deine Registrierung wurde erfolgreich abgeschlossen!
          </Alert>

          <Typography variant="body1" marginTop={2}>
            Wir haben dir eine Nachricht gesendet.
          </Typography>
        </Box>
      </Box>

      <Copyright sx={{ marginTop: 2 }} />
    </Container>
  );
};

export default RegistrierungAbgeschlossen;
