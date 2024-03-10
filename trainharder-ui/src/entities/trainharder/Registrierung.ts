import { UUID } from "node:crypto";

export default interface Registrierung {
  id?: UUID;
  EMailAdresse: string;
  benutzername?: string;
  passwort: string;
  benutzerId?: string;
}
