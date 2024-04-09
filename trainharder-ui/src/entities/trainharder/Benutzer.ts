import { Geschlecht } from "../attribute/Geschlecht.ts";

export default interface Benutzer {
  id: string;
  nachname: string;
  vorname: string;
  geschlecht: Geschlecht;
  geburtsdatum: Date;
}
