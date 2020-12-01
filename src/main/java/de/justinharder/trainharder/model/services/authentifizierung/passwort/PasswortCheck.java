package de.justinharder.trainharder.model.services.authentifizierung.passwort;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswortCheck
{
	private static final int MINDESTLAENGE = 12;
	private static final int KLEINBUCHSTABE_MINDESTCODE = 'a';
	private static final int KLEINBUCHSTABE_MAXIMALCODE = 'z';
	private static final int GROSSBUCHSTABE_MINDESTCODE = 'A';
	private static final int GROSSBUCHSTABE_MAXIMALCODE = 'Z';
	private static final int ZAHL_MINDESTCODE = '0';
	private static final int ZAHL_MAXIMALCODE = '9';
	private static final char[] ERLAUBTE_SONDERZEICHEN = new char[]
		{ '$', '%', '&', '?', '#', '_' };

	private boolean hatKleinbuchstabe;
	private boolean hatGrossbuchstabe;
	private boolean hatSonderzeichen;
	private boolean hatZahl;

	public boolean isUnsicher(String passwort)
	{
		hatKleinbuchstabe = false;
		hatGrossbuchstabe = false;
		hatSonderzeichen = false;
		hatZahl = false;

		if (passwort.length() < MINDESTLAENGE)
		{
			return true;
		}

		for (int i = 0; i < passwort.length(); i++)
		{
			hatKleinbuchstabe = checkKleinbuchstabe(passwort.charAt(i));
			hatGrossbuchstabe = checkGrossbuchstabe(passwort.charAt(i));
			hatSonderzeichen = checkSonderzeichen(passwort.charAt(i));
			hatZahl = checkZahl(passwort.charAt(i));
		}

		return !(hatKleinbuchstabe && hatGrossbuchstabe && hatSonderzeichen && hatZahl);
	}

	private boolean checkKleinbuchstabe(char buchstabe)
	{
		if (hatKleinbuchstabe)
		{
			return true;
		}

		return buchstabe >= KLEINBUCHSTABE_MINDESTCODE && buchstabe <= KLEINBUCHSTABE_MAXIMALCODE;
	}

	private boolean checkGrossbuchstabe(char buchstabe)
	{
		if (hatGrossbuchstabe)
		{
			return true;
		}

		return buchstabe >= GROSSBUCHSTABE_MINDESTCODE && buchstabe <= GROSSBUCHSTABE_MAXIMALCODE;
	}

	private boolean checkZahl(char buchstabe)
	{
		if (hatZahl)
		{
			return true;
		}

		return buchstabe >= ZAHL_MINDESTCODE && buchstabe <= ZAHL_MAXIMALCODE;
	}

	private boolean checkSonderzeichen(char buchstabe)
	{
		if (hatSonderzeichen)
		{
			return true;
		}

		for (var sonderzeichen : ERLAUBTE_SONDERZEICHEN)
		{
			if (sonderzeichen == buchstabe)
			{
				return true;
			}
		}

		return false;
	}
}
