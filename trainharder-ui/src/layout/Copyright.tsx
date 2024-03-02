import Typography from "@mui/material/Typography/Typography";
import { SxProps } from "@mui/material";

interface Props {
  sx: SxProps;
}

const Copyright = ({ sx }: Props) => {
  return (
    <Typography variant="body2" color="text.secondary" align="center" sx={sx}>
      © TrainHarder – Justin Harder
    </Typography>
  );
};

export default Copyright;
