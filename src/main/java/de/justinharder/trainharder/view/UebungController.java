package de.justinharder.trainharder.view;

import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

@Controller
@Path(value = "/uebungen")
public class UebungController extends AbstractController
{
	private static final String REDIRECT_TO_LOGIN = "redirect:login";

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@GET
	@Override
	public String index()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}

		return initialisiere("/uebungen/index.xhtml");
	}

	@GET
	@Path(value = "/create")
	public String erstelle()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}

		return initialisiere("/uebungen/create.xhtml");
	}
}
