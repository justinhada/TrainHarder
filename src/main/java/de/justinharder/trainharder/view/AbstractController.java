package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import lombok.Setter;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.security.enterprise.SecurityContext;

@Setter
public abstract class AbstractController
{
	@Inject
	protected Models models;
	@Inject
	protected SecurityContext securityContext;

	@Inject
	protected AuthentifizierungService authentifizierungService;
	@Inject
	protected BenutzerService benutzerService;

	protected String initialisiere(String pfad)
	{
		try
		{
			if (securityContext.getCallerPrincipal() != null)
			{
				var authentifizierungDto = getAuthentifizierungDto();
				models.put("authentifizierung", authentifizierungDto);
				models.put("benutzer", getBenutzerDto(authentifizierungDto.getPrimaerschluessel()));
			}
		}
		catch (AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
		}

		return pfad;
	}

	protected AuthentifizierungDto getAuthentifizierungDto() throws AuthentifizierungNichtGefundenException
	{
		return authentifizierungService.ermittleZuBenutzername(securityContext.getCallerPrincipal().getName());
	}

	protected BenutzerDto getBenutzerDto(String authentifizierungId) throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleZuAuthentifizierung(authentifizierungId);
	}

	public abstract String index();
}
