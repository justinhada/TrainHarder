import { useAuth } from "../../provider/AuthProvider.tsx";
import { jwtDecode } from "jwt-decode";
import { useParams } from "react-router-dom";
import { useBenutzer } from "../../hooks/useBenutzer.ts";
import EigenerBenutzer from "./EigenerBenutzer.tsx";
import AndererBenutzer from "./AndererBenutzer.tsx";

const Benutzer = () => {
  const { token } = useAuth();
  const { benutzername } = useParams();
  const {
    data: benutzer,
    error,
    isLoading,
  } = useBenutzer(benutzername!.substring(1));

  if (isLoading) return `${benutzername} wird abgerufen, bitte warten ...`;

  if (error || !token || !benutzer || !benutzername) throw error;

  const { sub } = jwtDecode(token);
  return sub === benutzername?.substring(1) ? (
    <EigenerBenutzer benutzer={benutzer} benutzername={benutzername} />
  ) : (
    <AndererBenutzer benutzer={benutzer} benutzername={benutzername} />
  );
};

export default Benutzer;
