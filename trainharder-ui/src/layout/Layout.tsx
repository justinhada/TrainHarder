import { Container, Grid } from "@mui/material";
import SideBar from "../layout/SideBar.tsx";
import NavigationBar from "../layout/NavigationBar.tsx";
import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <Grid container={true} spacing={0}>
      <Grid item={true} xs={2}>
        <SideBar />
      </Grid>

      <Grid item={true} xs={10}>
        <Grid container={true} spacing={0} direction="column">
          <Grid item={true}>
            <NavigationBar />
          </Grid>

          <Grid item={true}>
            <Container component="main" maxWidth={false}>
              <Outlet />
            </Container>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Layout;
