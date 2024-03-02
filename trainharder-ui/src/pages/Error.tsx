import { isRouteErrorResponse, useRouteError } from "react-router-dom";
import { Typography } from "@mui/material";

const Error = () => {
  const error = useRouteError();

  return (
    <>
      <Typography component="h1" variant="h3">
        Error
      </Typography>

      <Typography variant="body1">
        {isRouteErrorResponse(error)
          ? "Diese Seite existiert nicht."
          : "Ein unerwarteter Fehler ist aufgetreten."}
      </Typography>
    </>
  );
};

export default Error;
