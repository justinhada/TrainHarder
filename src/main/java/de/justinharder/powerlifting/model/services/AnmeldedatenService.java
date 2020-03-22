package de.justinharder.powerlifting.model.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Anmeldedaten;
import de.justinharder.powerlifting.model.domain.dto.AnmeldedatenEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.AnmeldedatenNichtGefundenException;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;
import de.justinharder.powerlifting.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.powerlifting.model.repository.AnmeldedatenRepository;

public class AnmeldedatenService implements Serializable
{
	private static final long serialVersionUID = -1065462481382412629L;

	private final AnmeldedatenRepository anmeldedatenRepository;

	@Inject
	public AnmeldedatenService(final AnmeldedatenRepository anmeldedatenRepository)
	{
		this.anmeldedatenRepository = anmeldedatenRepository;
	}

	public List<AnmeldedatenEintrag> ermittleAlle()
	{
		return konvertiereAlle(anmeldedatenRepository.ermittleAlle());
	}

	public AnmeldedatenEintrag ermittleZuId(final String id) throws AnmeldedatenNichtGefundenException
	{
		final var anmeldedaten = anmeldedatenRepository.ermittleZuId(Integer.valueOf(id));
		if (anmeldedaten == null)
		{
			throw new AnmeldedatenNichtGefundenException(
				"Die Anmeldedaten mit der ID \"" + id + "\" existieren nicht!");
		}
		return konvertiere(anmeldedaten);
	}

	public AnmeldedatenEintrag ermittleZuBenutzer(final String benutzerId) throws AnmeldedatenNichtGefundenException
	{
		final var anmeldedaten = anmeldedatenRepository.ermittleZuBenutzer(Integer.valueOf(benutzerId));
		if (anmeldedaten == null)
		{
			throw new AnmeldedatenNichtGefundenException(
				"Die Anmeldedaten mit der BenutzerID \"" + benutzerId + "\" existieren nicht!");
		}
		return konvertiere(anmeldedaten);
	}

	public void erstelleAnmeldedaten(final AnmeldedatenEintrag anmeldedatenEintrag)
	{
		final var anmeldedaten = new Anmeldedaten(
			anmeldedatenEintrag.getMail(),
			anmeldedatenEintrag.getBenutzername(),
			anmeldedatenEintrag.getPasswort());
		anmeldedatenRepository.erstelleAnmeldedaten(anmeldedaten);
	}

	public void registriereBenutzer(final AnmeldedatenEintrag anmeldedatenEintrag)
		throws MailBereitsRegistriertException
	{
		if (anmeldedatenRepository.checkMail(anmeldedatenEintrag.getMail()))
		{
			throw new MailBereitsRegistriertException("Es existiert bereits ein Benutzer mit dieser E-Mail-Adresse!");
		}

		// -> Benutzername checken, ob schon vorhanden (Fehler: Benutzername ist leider schon vergeben!)
		//    -> Passwortcheck soll "live" sein mithilfe von JavaScript (Passwortrichtlinien festlegen, in ReadME festhalten)
	}

	public AnmeldedatenEintrag checkLogin(final String mail, final String passwort) throws LoginException
	{
		return konvertiere(anmeldedatenRepository.checkLogin(mail, passwort));
	}

	private List<AnmeldedatenEintrag> konvertiereAlle(final List<Anmeldedaten> alleAnmeldedaten)
	{
		return alleAnmeldedaten
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	private AnmeldedatenEintrag konvertiere(final Anmeldedaten anmeldedaten)
	{
		return new AnmeldedatenEintrag(
			anmeldedaten.getId(),
			anmeldedaten.getMail(),
			anmeldedaten.getBenutzername(),
			anmeldedaten.getPasswort());
	}
}
