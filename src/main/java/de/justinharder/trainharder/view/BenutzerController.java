package de.justinharder.trainharder.view;

import javax.mvc.Controller;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

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
