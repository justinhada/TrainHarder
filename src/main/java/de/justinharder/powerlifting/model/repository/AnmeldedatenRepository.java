package de.justinharder.powerlifting.model.repository;

import java.util.List;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;

public interface AnmeldedatenRepository
{
	List<Anmeldedaten> ermittleAlle();

	Anmeldedaten ermittleZuId(final int id);

	Anmeldedaten ermittleZuBenutzer(final int benutzerId);

	void erstelleAnmeldedaten(Anmeldedaten anmeldedaten);

	Anmeldedaten checkLogin(String mail, String passwort) throws LoginException;

	boolean checkMail(String mail);
}
