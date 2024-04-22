import Benutzer from "../../entities/trainharder/Benutzer.ts";

interface Props {
  benutzer: Benutzer;
  benutzername: string;
}

const AndererBenutzer = ({ benutzer, benutzername }: Props) => {
  return (
    <h1>
      Benutzer: {benutzername} ({benutzer.vorname} {benutzer.nachname})
    </h1>
  );
};

export default AndererBenutzer;
