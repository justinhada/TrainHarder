package de.justinharder.trainharder.view.navigation;

import de.justinharder.trainharder.view.AbstractController;
import lombok.AccessLevel;
import lombok.Setter;

import javax.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Controller
@Path(value = "/impressum")
public class ImpressumController extends AbstractController
{
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;

	@GET
	@Override
	public String index()
	{
		initialisiere();

		return "/impressum.xhtml";
	}
}
