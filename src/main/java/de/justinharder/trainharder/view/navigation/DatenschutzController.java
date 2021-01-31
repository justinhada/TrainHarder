package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.view.AbstractController;

import javax.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Controller
@Path(value = "/datenschutz")
public class DatenschutzController extends AbstractController
{
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@GET
	@Override
	public String index()
	{
		return initialisiere("/datenschutz.xhtml");
	}
}