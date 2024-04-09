import { ChangeEvent } from "react";
import { InputProps, TextField } from "@mui/material";

interface Props {
  id: string;
  name: string;
  label: string;
  type: string;
  autoFocus?: boolean;
  value?: string;
  disabled?: boolean;
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void;
  error?: boolean;
  helperText?: string;
  InputProps?: InputProps;
}

const Input = ({
  id,
  name,
  label,
  type,
  autoFocus,
  value,
  disabled,
  onChange,
  error,
  helperText,
  InputProps,
}: Props) => (
  <TextField
    id={id}
    name={name}
    label={label}
    type={type}
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
    InputProps={InputProps}
  />
);

export default Input;
