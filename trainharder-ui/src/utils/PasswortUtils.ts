const hasLowerCase = (passwort: string) => passwort.toUpperCase() != passwort;

const hasUpperCase = (passwort: string) => passwort.toLowerCase() != passwort;

const hasNumber = (passwort: string) => /\d/.test(passwort);

const hasSpecialCharacter = (passwort: string) =>
  /[ `!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/.test(passwort);

const hasLength = (passwort: string) => passwort.length >= 12;

const isGueltig = (passwort: string) =>
  hasLowerCase(passwort) &&
  hasUpperCase(passwort) &&
  hasNumber(passwort) &&
  hasSpecialCharacter(passwort) &&
  hasLength(passwort);

export default isGueltig;
