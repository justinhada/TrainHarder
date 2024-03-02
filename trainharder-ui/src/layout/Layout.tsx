import { Box, Grid } from "@mui/material";
import SideBar from "./SideBar.tsx";
import { Outlet } from "react-router-dom";
import { ReactNode } from "react";
import NavigationBar from "./NavigationBar.tsx";

interface Props {
  readonly children?: ReactNode;
}

const Layout = ({ children }: Props) => {
  return (
    <Grid container={true} spacing={0}>
      <Grid item={true} xs={2}>
        <SideBar />
      </Grid>

      <Grid item={true} xs={10}>
        <Grid container={true} spacing={0} direction="column">
          <Grid component="header" item={true}>
            <NavigationBar />
          </Grid>

          <Grid
            item={true}
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "center",
              marginTop: 10,
            }}
          >
            <Box component="main">{children ?? <Outlet />}</Box>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Layout;
