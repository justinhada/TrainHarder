package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.view.AbstractController;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

@Controller
@Path(value = "/impressum")
public class ImpressumController extends AbstractController
{
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@GET
	@Override
	public String index()
	{
		return initialisiere("/impressum.xhtml");
	}
}
