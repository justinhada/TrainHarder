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
import InsertChartOutlinedOutlinedIcon from "@mui/icons-material/InsertChartOutlinedOutlined";
import FitnessCenterOutlinedIcon from "@mui/icons-material/FitnessCenterOutlined";
import NewspaperOutlinedIcon from "@mui/icons-material/NewspaperOutlined";
import AssignmentOutlinedIcon from "@mui/icons-material/AssignmentOutlined";
import MovingOutlinedIcon from "@mui/icons-material/MovingOutlined";
import { Link } from "react-router-dom";
import { Logo } from "./Logo.tsx";

const SideBar = () => {
  const theme = useTheme();

  const [selectedMenuItem, setSelectedMenuItem] = useState<string>("");

  const BaseHarderSideBarLinks: SideBarLink[] = [
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
  ];

  const TrainHarderLoggedInSideBarLinks: SideBarLink[] = [
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
  ];

  const TrainHarderGuestSideBarLinks: SideBarLink[] = [
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
  ];

  const PlanHarderSideBarLinks: SideBarLink[] = [];

  const SuppHarderSideBarLinks: SideBarLink[] = [
    {
      title: "Supplements",
      icon: <AddBoxOutlinedIcon />,
      link: "supplements",
    },
  ];

  const DietHarderSideBarLinks: SideBarLink[] = [
    {
      title: "Körpergewicht",
      icon: <ScaleOutlinedIcon />,
      link: "koerpergewicht",
    },
    {
      title: "KFA",
      icon: <PercentOutlinedIcon />,
      link: "kfa",
    },
    {
      title: "Umfänge",
      icon: <StraightenOutlinedIcon />,
      link: "umfaenge",
    },
    {
      title: "Auswertung",
      icon: <InsertChartOutlinedOutlinedIcon />,
      link: "auswertung",
    },
  ];

  const EatHarderSideBarLinks: SideBarLink[] = [];

  const WorkHarderSideBarLinks: SideBarLink[] = [
    {
      title: "Übungen",
      icon: <FitnessCenterOutlinedIcon />,
      link: "/uebungen",
    },
    {
      title: "Trainingspläne",
      icon: <NewspaperOutlinedIcon />,
      link: "/trainingsplaene",
    },
    {
      title: "Tagebuch",
      icon: <AssignmentOutlinedIcon />,
      link: "/tagebuch",
    },
    {
      title: "Kraftwerte",
      icon: <MovingOutlinedIcon />,
      link: "/kraftwerte",
    },
  ];

  const displaySideBarLinks = (sideBarLinks: SideBarLink[]) => {
    return sideBarLinks.map((value) => {
      return (
        <MenuItem
          key={value.link}
          component={Link}
          to={value.link}
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
      maxWidth={false}
      sx={{
        backgroundColor: theme.palette.primary.main,
        height: "100vh",
      }}
    >
      <Link to="/">
        <Logo />
      </Link>

      <MenuList>
        <Box mb={4}>
          <Typography variant="caption">PlanHarder</Typography>

          {displaySideBarLinks(PlanHarderSideBarLinks)}
        </Box>

        <Box mb={4}>
          <Typography variant="caption">DietHarder</Typography>

          {displaySideBarLinks(DietHarderSideBarLinks)}
        </Box>

        <Box mb={4}>
          <Typography variant="caption">EatHarder</Typography>

          {displaySideBarLinks(EatHarderSideBarLinks)}
        </Box>

        <Box mb={4}>
          <Typography variant="caption">SuppHarder</Typography>

          {displaySideBarLinks(SuppHarderSideBarLinks)}
        </Box>

        <Box mb={4}>
          <Typography variant="caption">WorkHarder</Typography>

          {displaySideBarLinks(WorkHarderSideBarLinks)}
        </Box>

        <Box mb={4}>
          <Typography variant="caption">TrainHarder</Typography>

          {displaySideBarLinks(TrainHarderLoggedInSideBarLinks)}
          {displaySideBarLinks(TrainHarderGuestSideBarLinks)}
        </Box>

        <Box>
          <Typography variant="caption">Base</Typography>

          {displaySideBarLinks(BaseHarderSideBarLinks)}
        </Box>
      </MenuList>
    </Container>
  );
};

export default SideBar;
