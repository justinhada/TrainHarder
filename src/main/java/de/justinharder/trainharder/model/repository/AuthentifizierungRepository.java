package de.justinharder.trainharder.model.repository;

import java.util.List;

import de.justinharder.trainharder.model.domain.Authentifizierung;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;

public interface AuthentifizierungRepository
{
	List<Authentifizierung> ermittleAlle();

	Authentifizierung ermittleZuId(final int id);

	Authentifizierung ermittleZuBenutzer(final int benutzerId) throws AuthentifizierungNichtGefundenException;

	Authentifizierung ermittleZuMail(final String mail) throws AuthentifizierungNichtGefundenException;

	void erstelleAuthentifizierung(Authentifizierung authentifizierung);

	Authentifizierung checkLogin(String benutzername, String passwort) throws LoginException;

	boolean checkMail(String mail);

	boolean checkBenutzername(String benutzername);
}
