package de.justinharder.powerlifting.model.repository;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Authentifizierung;
import de.justinharder.powerlifting.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;

public interface AuthentifizierungRepository
{
	List<Authentifizierung> ermittleAlle();

	Authentifizierung ermittleZuId(final int id);

	Authentifizierung ermittleZuBenutzer(final int benutzerId) throws AuthentifizierungNichtGefundenException;

	Authentifizierung ermittleZuMail(final String mail) throws AuthentifizierungNichtGefundenException;

	void erstelleAuthentifizierung(Authentifizierung authentifizierung);

	Authentifizierung checkLogin(String mail, String passwort) throws LoginException;

	boolean checkMail(String mail);

	boolean checkBenutzername(String benutzername);
}
