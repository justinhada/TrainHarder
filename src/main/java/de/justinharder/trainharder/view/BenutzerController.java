package de.justinharder.trainharder.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.dto.BenutzerEintrag;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.BenutzerService;

@Named
@SessionScoped
public class BenutzerController implements Serializable
{
	private static final long serialVersionUID = -6981486797529025609L;

	private final BenutzerService benutzerService;

	@Inject
	public BenutzerController(final BenutzerService benutzerService)
	{
		this.benutzerService = benutzerService;
	}

	public List<BenutzerEintrag> getBenutzer()
	{
		return benutzerService.ermittleAlle();
	}

	public BenutzerEintrag getBenutzerZuId(final String id) throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleZuId(id);
	}

	public List<BenutzerEintrag> getBenutzerZuNachname(final String nachname) throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleAlleZuNachname(nachname);
	}
}
