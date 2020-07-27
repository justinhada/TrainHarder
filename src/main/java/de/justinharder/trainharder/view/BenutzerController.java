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
import de.justinharder.trainharder.view.dto.Benutzerdaten;
import lombok.AccessLevel;
import lombok.Setter;

@Controller
@Path("/benutzer")
public class BenutzerController extends AbstractController
{
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
		initialisiere();

		return "/benutzer/index.xhtml";
	}

	@GET
	@Path("/{benutzername}")
	public String benutzer(@PathParam("benutzername") final String benutzername)
	{
		initialisiere();

		return "/benutzer/benutzerdaten.xhtml";
	}

	@POST
	@Path("/{benutzername}")
	public String aendereBenutzer(@BeanParam final Benutzerdaten benutzerdaten)
	{
		Preconditions.checkNotNull(benutzerdaten, "Zum Ändern des Benutzers werden gültige Benutzerdaten benötigt!");

		try
		{
			final var authentifizierungDto = getAuthentifizierungDto();
			try
			{
				final var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());
				benutzerService.aktualisiereBenutzer(benutzerDto.getPrimaerschluessel(), benutzerdaten);
			}
			catch (final BenutzerNichtGefundenException e)
			{
				benutzerService.erstelleBenutzer(benutzerdaten, authentifizierungDto.getPrimaerschluessel());
			}
			return benutzer(authentifizierungDto.getBenutzername());
		}
		catch (final AuthentifizierungNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
			return "/error";
		}
	}

	@GET
	@Path("/logout")
	public String logout()
	{
		try
		{
			request.logout();
			return "redirect:start";
		}
		catch (final ServletException e)
		{
			models.put("fehler", e.getMessage());
			return "/error";
		}
	}
}
