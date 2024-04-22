import { IconButton, Snackbar } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";

interface Props {
  open: boolean;
  handleClose: () => void;
}

const LogoutSnackbar = ({ open, handleClose }: Props) => (
  <Snackbar
    open={open}
    onClose={handleClose}
    autoHideDuration={6000}
    message="Du hast dich erfolgreich abgemeldet."
    action={
      <IconButton
        size="small"
        aria-label="close"
        color="inherit"
        onClick={handleClose}
      >
        <CloseIcon fontSize="small" />
      </IconButton>
    }
  />
);

export default LogoutSnackbar;
