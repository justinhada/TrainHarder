package de.justinharder.trainharder.view.navigation.error;

import javax.inject.Inject;
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
@Path(value = "/error")
public class ErrorController extends AbstractController
{
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;

	@Inject
	private Errors errors;

	@GET
	@Override
	public String index()
	{
		initialisiere();

		return "/error/index.xhtml";
	}
}
