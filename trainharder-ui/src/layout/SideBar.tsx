import {
  Box,
  Container,
  ListItemIcon,
  ListItemText,
  MenuItem,
  MenuList,
  Typography,
  useTheme,
} from "@mui/material";
import { useState } from "react";
import SideBarLink from "./SideBarLink.ts";
import SettingsOutlinedIcon from "@mui/icons-material/SettingsOutlined";
import PolicyOutlinedIcon from "@mui/icons-material/PolicyOutlined";
import SecurityOutlinedIcon from "@mui/icons-material/SecurityOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";
import LoginOutlinedIcon from "@mui/icons-material/LoginOutlined";
import PersonAddAltOutlinedIcon from "@mui/icons-material/PersonAddAltOutlined";
import AddBoxOutlinedIcon from "@mui/icons-material/AddBoxOutlined";
import ScaleOutlinedIcon from "@mui/icons-material/ScaleOutlined";
import PercentOutlinedIcon from "@mui/icons-material/PercentOutlined";
import StraightenOutlinedIcon from "@mui/icons-material/StraightenOutlined";
import FitnessCenterOutlinedIcon from "@mui/icons-material/FitnessCenterOutlined";
import NewspaperOutlinedIcon from "@mui/icons-material/NewspaperOutlined";
import AssignmentOutlinedIcon from "@mui/icons-material/AssignmentOutlined";
import MovingOutlinedIcon from "@mui/icons-material/MovingOutlined";
import RestaurantOutlinedIcon from "@mui/icons-material/RestaurantOutlined";
import CalendarMonthOutlinedIcon from "@mui/icons-material/CalendarMonthOutlined";
import { Link } from "react-router-dom";
import { Logo } from "./Logo.tsx";
import Ueberschrift from "./Ueberschrift.tsx";
import Copyright from "./Copyright.tsx";

const SideBar = () => {
  const theme = useTheme();
  const [selectedMenuItem, setSelectedMenuItem] = useState<string>("");

  const displaySideBarLinks = (sideBarLinks: SideBarLink[]) => {
    return sideBarLinks.map((value) => {
      return (
        <MenuItem
          key={value.link}
          component={Link}
          to={value.link}
          title={value.title}
          sx={{
            ...{
              menuItems: {
                "&.MuiMenuItem-root": {
                  borderRadius: "10px",
                  height: "45px",
                  "&:hover": {
                    backgroundColor: "action.hover",
                  },
                },
              },
            }.menuItems,
            backgroundColor:
              selectedMenuItem === value.link ? "action.hover" : "",
            color: selectedMenuItem === value.link ? "white" : "black",
            "&:hover": {
              color: "white",
              ".MuiListItemIcon-root": {
                color: "white",
              },
            },
          }}
          onClick={() => setSelectedMenuItem(value.link)}
        >
          <ListItemIcon
            sx={{
              color: selectedMenuItem === value.link ? "white" : "black",
            }}
          >
            {value.icon}
          </ListItemIcon>
          <ListItemText>{value.title}</ListItemText>
        </MenuItem>
      );
    });
  };

  return (
    <Container
      component="aside"
      sx={{
        backgroundColor: theme.palette.primary.main,
        display: "flex",
        flexDirection: "column",
        height: "100vh",
      }}
    >
      <Link to="/" title="Startseite" onClick={() => setSelectedMenuItem("")}>
        <Logo
          sx={{
            marginLeft: "auto",
            marginRight: "auto",
            display: "block",
          }}
        />
      </Link>

      <MenuList>
        <Box marginBottom={2}>
          <Ueberschrift
            title="PlanHarder"
            link="planharder"
            onClick={() => setSelectedMenuItem("")}
          />

          {displaySideBarLinks([
            {
              title: "Kalender",
              icon: <CalendarMonthOutlinedIcon />,
              link: "planharder/kalender",
            },
          ])}
        </Box>

        <Box marginBottom={2}>
          <Ueberschrift
            title="DietHarder"
            link="dietharder"
            onClick={() => setSelectedMenuItem("")}
          />

          {displaySideBarLinks([
            {
              title: "Körpergewicht",
              icon: <ScaleOutlinedIcon />,
              link: "dietharder/koerpergewicht",
            },
            {
              title: "KFA",
              icon: <PercentOutlinedIcon />,
              link: "dietharder/kfa",
            },
            {
              title: "Umfänge",
              icon: <StraightenOutlinedIcon />,
              link: "dietharder/umfaenge",
            },
          ])}
        </Box>

        <Box marginBottom={2}>
          <Ueberschrift
            title="EatHarder"
            link="eatharder"
            onClick={() => setSelectedMenuItem("")}
          />

          {displaySideBarLinks([
            {
              title: "Ernährungsplan",
              icon: <RestaurantOutlinedIcon />,
              link: "eatharder/ernaehrungsplan",
            },
          ])}
        </Box>

        <Box marginBottom={2}>
          <Ueberschrift
            title="SuppHarder"
            link="suppharder"
            onClick={() => setSelectedMenuItem("")}
          />

          {displaySideBarLinks([
            {
              title: "Supplements",
              icon: <AddBoxOutlinedIcon />,
              link: "suppharder/supplements",
            },
          ])}
        </Box>

        <Box marginBottom={2}>
          <Ueberschrift
            title="WorkHarder"
            link="workharder"
            onClick={() => setSelectedMenuItem("")}
          />

          {displaySideBarLinks([
            {
              title: "Übungen",
              icon: <FitnessCenterOutlinedIcon />,
              link: "workharder/uebungen",
            },
            {
              title: "Trainingspläne",
              icon: <NewspaperOutlinedIcon />,
              link: "workharder/trainingsplaene",
            },
            {
              title: "Tagebuch",
              icon: <AssignmentOutlinedIcon />,
              link: "workharder/tagebuch",
            },
            {
              title: "Kraftwerte",
              icon: <MovingOutlinedIcon />,
              link: "workharder/kraftwerte",
            },
          ])}
        </Box>

        <Box marginBottom={2}>
          <Ueberschrift
            title="TrainHarder"
            link="trainharder"
            onClick={() => setSelectedMenuItem("")}
          />

          {displaySideBarLinks([
            {
              title: "Benutzer",
              icon: <PersonOutlinedIcon />,
              link: "benutzer",
            },
            {
              title: "Logout",
              icon: <LogoutOutlinedIcon />,
              link: "logout",
            },
          ])}
          {displaySideBarLinks([
            {
              title: "Login",
              icon: <LoginOutlinedIcon />,
              link: "login",
            },
            {
              title: "Registrierung",
              icon: <PersonAddAltOutlinedIcon />,
              link: "registrierung",
            },
          ])}
        </Box>
      </MenuList>

      <Box marginTop="auto" marginBottom={2}>
        <Typography variant="caption">Base</Typography>

        <MenuList>
          {displaySideBarLinks([
            {
              title: "Einstellungen",
              icon: <SettingsOutlinedIcon />,
              link: "einstellungen",
            },
            {
              title: "Impressum",
              icon: <PolicyOutlinedIcon />,
              link: "impressum",
            },
            {
              title: "Datenschutz",
              icon: <SecurityOutlinedIcon />,
              link: "datenschutz",
            },
          ])}
        </MenuList>
      </Box>

      <Box marginBottom={2}>
        <Copyright />
      </Box>
    </Container>
  );
};

export default SideBar;
