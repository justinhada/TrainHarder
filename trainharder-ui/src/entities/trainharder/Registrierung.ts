import { Dayjs } from "dayjs";

export default interface Registrierung {
  EMailAdresse?: string;
  benutzername?: string;
  passwort?: string;
  vorname?: string;
  nachname?: string;
  geschlecht?: string;
  geburtsdatum?: Dayjs;
}
