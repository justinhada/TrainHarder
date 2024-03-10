import { TextField } from "@mui/material";
import { ChangeEvent } from "react";

interface Props {
  autoFocus?: boolean;
  value?: string;
  disabled?: boolean;
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void;
  error?: boolean;
  helperText?: string;
}

const EMailAdresseInput = ({
  autoFocus,
  value,
  disabled,
  onChange,
  error,
  helperText,
}: Props) => {
  return (
    <TextField
      id="eMailAdresse"
      name="eMailAdresse"
      label="E-Mail-Adresse"
      type="email"
      color="secondary"
      margin="dense"
      required={true}
      fullWidth={true}
      error={error}
      helperText={helperText}
      autoFocus={autoFocus}
      disabled={disabled}
      value={value}
      onChange={onChange}
    />
  );
};

export default EMailAdresseInput;
