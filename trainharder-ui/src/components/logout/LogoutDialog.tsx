import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@mui/material";
import { useAuth } from "../../provider/AuthProvider.tsx";

interface Props {
  open: boolean;
  handleClose: () => void;
  handleOpenSnackbar: () => void;
}

const LogoutDialog = ({ open, handleClose, handleOpenSnackbar }: Props) => {
  const { setToken } = useAuth();

  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Logout</DialogTitle>

      <DialogContent>
        <DialogContentText>
          Möchtest du dich wirklich ausloggen?
        </DialogContentText>
      </DialogContent>

      <DialogActions>
        <Button
          variant="contained"
          color="error"
          onClick={(event) => {
            event.preventDefault();

            setToken("");

            handleOpenSnackbar();

            handleClose();
          }}
          autoFocus={true}
        >
          Logout
        </Button>

        <Button variant="outlined" color="secondary" onClick={handleClose}>
          Abbrechen
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default LogoutDialog;
