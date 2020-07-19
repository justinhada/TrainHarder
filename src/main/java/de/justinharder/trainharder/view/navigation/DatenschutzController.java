package de.justinharder.trainharder.view.navigation;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import lombok.AccessLevel;
import lombok.Setter;

@Setter
@Controller
@Path("/datenschutz")
public class DatenschutzController
{
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;
	@Inject
	private Models models;
	@Inject
	private SecurityContext securityContext;

	@Inject
	private AuthentifizierungService authentifizierungService;
	@Inject
	private BenutzerService benutzerService;

	@GET
	public String index()
	{
		initialisiere();

		return "/datenschutz.xhtml";
	}

	private void initialisiere()
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
}
