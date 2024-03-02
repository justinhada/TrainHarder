import { Link } from "react-router-dom";
import { Typography } from "@mui/material";

interface Props {
  readonly title: string;
  readonly link: string;
  onClick: () => void;
}

const Ueberschrift = ({ title, link, onClick }: Props) => {
  return (
    <Link
      to={link}
      title={title}
      style={{ textDecoration: "none" }}
      onClick={onClick}
    >
      <Typography variant="caption">{title}</Typography>
    </Link>
  );
};

export default Ueberschrift;
