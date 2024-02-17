import { UUID } from "node:crypto";
import { Geschlecht } from "../attribute/Geschlecht.ts";

export default interface Benutzer {
  id: UUID;
  nachname: string;
  vorname: string;
  geschlecht: Geschlecht;
  geburtsdatum: Date;
}
