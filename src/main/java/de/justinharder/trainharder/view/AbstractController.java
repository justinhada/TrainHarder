package de.justinharder.trainharder.view;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.security.enterprise.SecurityContext;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import lombok.Setter;

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

	protected void initialisiere()
	{
		try
		{
			if (securityContext.getCallerPrincipal() != null)
			{
				final var authentifizierungDto =
					authentifizierungService.ermittleZuBenutzername(securityContext.getCallerPrincipal().getName());
				models.put("authentifizierung", authentifizierungDto);
				models.put("benutzer",
					benutzerService.ermittleZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel()));
			}
		}
		catch (final AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
		}
	}

	public abstract String index();
}
