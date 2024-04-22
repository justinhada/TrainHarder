import Benutzer from "../../entities/trainharder/Benutzer.ts";

interface Props {
  benutzer: Benutzer;
  benutzername: string;
}

const EigenerBenutzer = ({ benutzer, benutzername }: Props) => {
  return (
    <h1>
      Mein Benutzer: {benutzername} ({benutzer.vorname} {benutzer.nachname})
    </h1>
  );
};

export default EigenerBenutzer;
