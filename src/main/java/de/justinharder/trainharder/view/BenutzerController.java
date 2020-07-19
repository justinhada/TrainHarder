package de.justinharder.trainharder.view;

import javax.mvc.Controller;
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
	@Setter(AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(AccessLevel.NONE)
	private HttpServletResponse response;

	@GET
	public String index()
	{
		return "/benutzer/index.xhtml";
	}

	@GET
	@Path("/{benutzername}")
	public String benutzerdaten(@PathParam("benutzername") final String benutzername)
	{
		initialisiere();

		return "/benutzer/benutzer.xhtml";
	}
}
