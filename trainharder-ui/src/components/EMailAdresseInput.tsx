import { TextField } from "@mui/material";

interface Props {
  autoFocus?: boolean;
  value?: string;
  disabled?: boolean;
}

const EMailAdresseInput = ({ autoFocus, value, disabled }: Props) => {
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
      autoFocus={autoFocus}
      disabled={disabled}
      value={value}
    />
  );
};

export default EMailAdresseInput;
