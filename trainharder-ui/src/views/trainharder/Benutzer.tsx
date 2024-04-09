import { useAuth } from "../../provider/AuthProvider.tsx";
import { jwtDecode } from "jwt-decode";
import { useParams } from "react-router-dom";

const Benutzer = () => {
  const { token } = useAuth();
  const { benutzername } = useParams();

  if (token) {
    const { sub } = jwtDecode(token);

    if (sub === benutzername?.substring(1)) {
      return <h1>Mein Benutzer: {benutzername}</h1>;
    }
  }

  return <h1>Benutzer: {benutzername}</h1>;
};

export default Benutzer;
