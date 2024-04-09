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

const VornameInput = ({
  autoFocus,
  value,
  disabled,
  onChange,
  error,
  helperText,
}: Props) => (
  <Input
    id="vorname"
    name="vorname"
    label="Vorname"
    type="text"
    error={error}
    helperText={helperText}
    autoFocus={autoFocus}
    disabled={disabled}
    value={value}
    onChange={onChange}
  />
);

export default VornameInput;
