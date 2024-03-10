import { IconButton, InputAdornment, TextField } from "@mui/material";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { ChangeEvent, useState } from "react";

interface Props {
  autoFocus?: boolean;
  value?: string;
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void;
  error?: boolean;
  helperText?: string;
}

const PasswortInput = ({
  autoFocus,
  value,
  onChange,
  error,
  helperText,
}: Props) => {
  const [showPassword, setShowPassword] = useState(false);

  const handleClickShowPassword = () => {
    setShowPassword(!showPassword);
  };

  return (
    <TextField
      id="passwort"
      name="passwort"
      label="Passwort"
      type={showPassword ? "text" : "password"}
      color="secondary"
      margin="dense"
      required={true}
      fullWidth={true}
      error={error}
      helperText={helperText}
      autoFocus={autoFocus}
      value={value}
      onChange={onChange}
      InputProps={{
        endAdornment: (
          <InputAdornment position="end">
            <IconButton
              aria-label="toggle password visibility"
              onClick={handleClickShowPassword}
              edge="end"
            >
              {showPassword ? <Visibility /> : <VisibilityOff />}
            </IconButton>
          </InputAdornment>
        ),
      }}
    />
  );
};

export default PasswortInput;
