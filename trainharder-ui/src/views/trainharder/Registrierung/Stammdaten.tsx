import { Form, Link, useParams } from "react-router-dom";
import {
  Avatar,
  Box,
  Button,
  Checkbox,
  CircularProgress,
  Container,
  FormControlLabel,
  Grid,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import { FormEvent } from "react";
import { Logo } from "../../../layout/Logo.tsx";
import Copyright from "../../../layout/Copyright.tsx";
import EMailAdresseInput from "../../../components/EMailAdresseInput.tsx";
import { useRegistrierung } from "../../../hooks/useRegistrierung.ts";

const Stammdaten = () => {
  const { id } = useParams();
  const { data: registrierung, error, isLoading } = useRegistrierung(id!);

  if (isLoading) return <CircularProgress color="secondary" />;

  if (error || !registrierung) throw error;

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get("email"),
      password: data.get("password"),
    });
  };

  return (
    <>
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
              <TextField
                autoComplete="given-name"
                name="firstName"
                required
                fullWidth
                id="firstName"
                label="First Name"
                autoFocus
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                required
                fullWidth
                id="lastName"
                label="Last Name"
                name="lastName"
                autoComplete="family-name"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="email"
                label="Email Address"
                name="email"
                autoComplete="email"
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="password"
                label="Password"
                type="password"
                id="password"
                autoComplete="new-password"
              />
            </Grid>
            <Grid item xs={12}>
              <FormControlLabel
                control={<Checkbox value="allowExtraEmails" color="primary" />}
                label="I want to receive inspiration, marketing promotions and updates via email."
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            Sign Up
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link to="#">Already have an account? Sign in</Link>
            </Grid>
          </Grid>
        </Box>
      </Box>

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
            <Form method="POST" onSubmit={() => {}}>
              <Grid container spacing={1}>
                <Grid item={true} xs={12}>
                  <EMailAdresseInput
                    value={registrierung.EMailAdresse}
                    disabled={true}
                  />
                </Grid>

                <Grid item={true} xs={12}>
                  <TextField
                    id="benutzername"
                    name="benutzername"
                    label="Benutzername"
                    type="text"
                    color="secondary"
                    margin="dense"
                    required={true}
                    fullWidth={true}
                  />
                </Grid>

                <Grid item xs={12} sm={6}>
                  <TextField
                    id="vorname"
                    name="vorname"
                    label="Vorname"
                    type="text"
                    color="secondary"
                    margin="dense"
                    required={true}
                    fullWidth={true}
                  />
                </Grid>

                <Grid item xs={12} sm={6}>
                  <TextField
                    id="nachname"
                    name="nachname"
                    label="Nachname"
                    type="text"
                    color="secondary"
                    margin="dense"
                    required={true}
                    fullWidth={true}
                  />
                </Grid>

                <Grid item={true} xs={12}>
                  <Select
                    id="geschlecht"
                    labelId="geschlechtLabel"
                    name="geschlecht"
                    label="Geschlecht"
                    color="secondary"
                    margin="dense"
                    required={true}
                    fullWidth={true}
                  >
                    <MenuItem value="MAENNLICH">Männlich</MenuItem>
                    <MenuItem value="WEIBLICH">Weiblich</MenuItem>
                    <MenuItem value="DIVERS">Divers</MenuItem>
                  </Select>
                </Grid>
              </Grid>

              <Button
                type="submit"
                fullWidth={true}
                variant="contained"
                color="secondary"
                sx={{ marginTop: 3, marginBottom: 2 }}
              >
                Registrierung abschließen
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
    </>
  );
};

export default Stammdaten;
