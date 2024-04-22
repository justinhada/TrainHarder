import { ListItemIcon, ListItemText, MenuItem } from "@mui/material";
import LogoutOutlinedIcon from "@mui/icons-material/LogoutOutlined";

interface Props {
  selectedMenuItem: string;
  handleOpenDialog: () => void;
}

const LogoutMenuItem = ({ selectedMenuItem, handleOpenDialog }: Props) => (
  <MenuItem
    title="Logout"
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
      backgroundColor: selectedMenuItem === "logout" ? "action.hover" : "",
      color: selectedMenuItem === "logout" ? "white" : "black",
      "&:hover": {
        color: "white",
        ".MuiListItemIcon-root": {
          color: "white",
        },
      },
    }}
    onClick={handleOpenDialog}
  >
    <ListItemIcon
      sx={{
        color: selectedMenuItem === "logout" ? "white" : "black",
      }}
    >
      <LogoutOutlinedIcon />
    </ListItemIcon>

    <ListItemText>Logout</ListItemText>
  </MenuItem>
);

export default LogoutMenuItem;
