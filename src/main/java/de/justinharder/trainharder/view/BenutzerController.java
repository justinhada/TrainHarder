package de.justinharder.trainharder.view;

import javax.mvc.Controller;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import lombok.AccessLevel;
import lombok.Setter;

@Controller
@Path(value = "/benutzer")
public class BenutzerController extends AbstractController
{
	private static final String REDIRECT_TO_START = "redirect:start";
	private static final String REDIRECT_TO_LOGIN = "redirect:login";
	private static final String REDIRECT_TO_ERROR = "redirect:error";

	@Context
	@Setter(value = AccessLevel.PUBLIC)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;

	@GET
	@Override
	public String index()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}
		initialisiere();

		return "/benutzer/index.xhtml";
	}

	@GET
	@Path(value = "/{benutzername}")
	public String benutzerdaten(@PathParam(value = "benutzername") final String benutzername)
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}
		initialisiere();

		return "/benutzer/benutzerdaten.xhtml";
	}

	@POST
	@Path(value = "/{benutzername}")
	public String aendereBenutzerdaten(@BeanParam final Benutzerdaten benutzerdaten)
	{
		Preconditions.checkNotNull(benutzerdaten, "Zum Ändern des Benutzers werden gültige Benutzerdaten benötigt!");

		try
		{
			final var authentifizierungDto = getAuthentifizierungDto();

			aendereOderErstelle(benutzerdaten, authentifizierungDto);
			return benutzerdaten(authentifizierungDto.getBenutzername());
		}
		catch (final AuthentifizierungNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
			return REDIRECT_TO_LOGIN;
		}
	}

	private void aendereOderErstelle(final Benutzerdaten benutzerdaten, final AuthentifizierungDto authentifizierungDto)
		throws AuthentifizierungNichtGefundenException
	{
		try
		{
			final var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());
			benutzerService.aktualisiereBenutzer(benutzerDto.getPrimaerschluessel(), benutzerdaten);
		}
		catch (final BenutzerNichtGefundenException e)
		{
			benutzerService.erstelleBenutzer(benutzerdaten, authentifizierungDto.getPrimaerschluessel());
		}
	}

	@GET
	@Path(value = "/logout")
	public String logout()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}

		try
		{
			request.logout();
			return REDIRECT_TO_START;
		}
		catch (final ServletException e)
		{
			models.put("fehler", e.getMessage());
			return REDIRECT_TO_ERROR;
		}
	}
}
