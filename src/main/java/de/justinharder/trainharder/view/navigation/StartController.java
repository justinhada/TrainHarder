package de.justinharder.trainharder.view.navigation;

import javax.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import de.justinharder.trainharder.view.AbstractController;
import lombok.AccessLevel;
import lombok.Setter;

@Controller
@Path("/start")
public class StartController extends AbstractController
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

		return "/index.xhtml";
	}
}
