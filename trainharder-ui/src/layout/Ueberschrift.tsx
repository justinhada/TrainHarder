import { Link } from "react-router-dom";
import { Typography } from "@mui/material";

interface Props {
  readonly title: string;
  readonly link: string;
}

const Ueberschrift = ({ title, link }: Props) => {
  return (
    <Link to={link} title={title} style={{ textDecoration: "none" }}>
      <Typography variant="caption">{title}</Typography>
    </Link>
  );
};

export default Ueberschrift;
