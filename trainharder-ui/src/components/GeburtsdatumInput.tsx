import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DateField, LocalizationProvider } from "@mui/x-date-pickers";
import { Dayjs } from "dayjs";

interface Props {
  autoFocus?: boolean;
  value?: Dayjs;
  disabled?: boolean;
  onChange?: (value: Dayjs | null) => void;
  error?: boolean;
  helperText?: string;
}

const GeburtsdatumInput = ({
  autoFocus,
  value,
  disabled,
  onChange,
  error,
  helperText,
}: Props) => (
  <LocalizationProvider dateAdapter={AdapterDayjs}>
    <DateField
      label="Geburtsdatum"
      color="secondary"
      margin="dense"
      required={true}
      fullWidth={true}
      format="DD.MM.YYYY"
      slotProps={{ textField: { helperText: helperText, error: error } }}
      helperText={helperText}
      autoFocus={autoFocus}
      disabled={disabled}
      value={value}
      onChange={onChange}
    />
  </LocalizationProvider>
);

export default GeburtsdatumInput;
