import { Grid } from "@mui/material";
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
      <Grid item={true} xs={12} sm={4} md={3} lg={2} height="100%">
        <SideBar />
      </Grid>

      <Grid item={true} xs={12} sm={8} md={9} lg={10}>
        <Grid container={true} spacing={0} display="flex" direction="column">
          <Grid component="header" item={true}>
            <NavigationBar />
          </Grid>

          <Grid
            component="main"
            item={true}
            sx={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
              justifyContent: "center",
              marginTop: 10,
            }}
          >
            {children ?? <Outlet />}
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Layout;
