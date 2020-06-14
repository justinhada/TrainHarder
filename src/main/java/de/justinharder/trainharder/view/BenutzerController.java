package de.justinharder.trainharder.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.view.dto.BenutzerDto;

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

	public List<BenutzerDto> getBenutzer()
	{
		return benutzerService.ermittleAlle();
	}

	public BenutzerDto getBenutzerZuId(final String id) throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleZuId(id);
	}

	public List<BenutzerDto> getBenutzerZuNachname(final String nachname) throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleAlleZuNachname(nachname);
	}
}
