package de.justinharder.trainharder.view.navigation;

import java.security.Principal;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;

public abstract class AbstractController
{
	@Context
	protected HttpServletRequest request;
	@Context
	protected HttpServletResponse response;
	@Inject
	protected Models models;
	@Inject
	protected SecurityContext securityContext;

	@Inject
	protected AuthentifizierungService authentifizierungService;
	@Inject
	protected BenutzerService benutzerService;

	protected void initialisiere(final Principal principal)
	{
		try
		{
			final var authentifizierungDto = authentifizierungService.ermittleZuBenutzername(principal.getName());
			final var benutzerDto = benutzerService
				.ermittleZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel());

			models.put("authentifizierung", authentifizierungDto);
			models.put("benutzer", benutzerDto);
		}
		catch (final AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
		}
	}
}
