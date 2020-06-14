package de.justinharder.trainharder.view.navigation;

import javax.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Controller
@Path("/impressum")
public class ImpressumController
{
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@GET
	public String index()
	{
		return "/impressum.xhtml";
	}
}
