package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import jakarta.mvc.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import lombok.NonNull;

@Controller
@Path(value = "/benutzer")
public class BenutzerController extends AbstractController
{
	private static final String REDIRECT_TO_START = "redirect:start";
	private static final String REDIRECT_TO_LOGIN = "redirect:login";
	private static final String REDIRECT_TO_ERROR = "redirect:error";

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	public void setRequest(@NonNull HttpServletRequest request)
	{
		this.request = request;
	}

	@GET
	@Override
	public String index()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}
		models.put("alleBenutzer", benutzerService.ermittleAlle());

		return initialisiere("/benutzer/index.xhtml");
	}

	@GET
	@Path(value = "/{benutzername}")
	public String benutzerdaten(@NonNull @PathParam(value = "benutzername") String benutzername)
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}
		models.put("benutzername", benutzername);

		return initialisiere("/benutzer/benutzerdaten.xhtml");
	}

	@POST
	@Path(value = "/{benutzername}")
	public String aendereBenutzerdaten(@NonNull @BeanParam Benutzerdaten benutzerdaten)
	{
		try
		{
			var authentifizierungDto = getAuthentifizierungDto();

			aendereOderErstelle(benutzerdaten, authentifizierungDto);
			return benutzerdaten(authentifizierungDto.getBenutzername());
		}
		catch (AuthentifizierungNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
			return REDIRECT_TO_LOGIN;
		}
	}

	private void aendereOderErstelle(Benutzerdaten benutzerdaten, AuthentifizierungDto authentifizierungDto)		throws AuthentifizierungNichtGefundenException
	{
		try
		{
			var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());
			benutzerService.aktualisiereBenutzer(benutzerDto.getPrimaerschluessel(), benutzerdaten);
		}
		catch (BenutzerNichtGefundenException e)
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
		catch (ServletException e)
		{
			models.put("fehler", e.getMessage());
			return REDIRECT_TO_ERROR;
		}
	}
}
