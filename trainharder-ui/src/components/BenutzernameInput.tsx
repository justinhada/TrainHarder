import { ChangeEvent } from "react";
import Input from "./Input.tsx";

interface Props {
  autoFocus?: boolean;
  value?: string;
  disabled?: boolean;
  onChange?: (event: ChangeEvent<HTMLInputElement>) => void;
  error?: boolean;
  helperText?: string;
}

const BenutzernameInput = ({
  autoFocus,
  value,
  disabled,
  onChange,
  error,
  helperText,
}: Props) => (
  <Input
    id="benutzername"
    name="benutzername"
    label="Benutzername"
    type="text"
    error={error}
    helperText={helperText}
    autoFocus={autoFocus}
    disabled={disabled}
    value={value}
    onChange={onChange}
  />
);

export default BenutzernameInput;
