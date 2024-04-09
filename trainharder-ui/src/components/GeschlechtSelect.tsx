import {
  FormControl,
  FormHelperText,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
} from "@mui/material";

interface Props {
  autoFocus?: boolean;
  value?: string;
  disabled?: boolean;
  onChange?: (event: SelectChangeEvent) => void;
  error?: boolean;
  helperText?: string;
}

const GeschlechtSelect = ({
  autoFocus,
  value,
  disabled,
  onChange,
  error,
  helperText,
}: Props) => (
  <FormControl fullWidth={true} margin="dense">
    <InputLabel
      id="geschlechtLabel"
      color="secondary"
      margin="dense"
      error={error}
    >
      Geschlecht
    </InputLabel>

    <Select
      id="geschlecht"
      name="geschlecht"
      labelId="geschlechtLabel"
      label="Geschlecht"
      color="secondary"
      margin="dense"
      required={true}
      fullWidth={true}
      error={error}
      autoFocus={autoFocus}
      disabled={disabled}
      value={value}
      onChange={onChange}
    >
      <MenuItem value="" divider={true}>
        Bitte wähle dein Geschlecht aus ...
      </MenuItem>
      <MenuItem value="MAENNLICH">Männlich</MenuItem>
      <MenuItem value="WEIBLICH">Weiblich</MenuItem>
      <MenuItem value="DIVERS">Divers</MenuItem>
    </Select>

    <FormHelperText error={error}>{helperText}</FormHelperText>
  </FormControl>
);

export default GeschlechtSelect;
