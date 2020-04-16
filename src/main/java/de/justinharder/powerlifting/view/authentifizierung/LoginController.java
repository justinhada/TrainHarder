package de.justinharder.powerlifting.view.authentifizierung;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import de.justinharder.powerlifting.model.domain.dto.Login;

@Path("/login")
@Controller
public class LoginController
{
	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;
	@Inject
	private Models models;
	@Inject
	private BindingResult bindingResult;
	@Inject
	private SecurityContext securityContext;

	private final Map<String, String> fehlermeldungen = new HashMap<>();

	@GET
	public String index()
	{
		System.out.println("LoginController.index() aufgerufen");
		return "/login.xhtml";
	}

	@POST
	public String login(@BeanParam final Login login)
	{
		System.out.println("LoginController.login() wird ausgeführt");

		if (bindingResult.isFailed())
		{
			final List<String> errors = bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList());
			models.put("errors", errors);
			return index();
		}

		final var credential = new UsernamePasswordCredential(login.getBenutzername(), login.getPasswort());
		final var authenticationStatus = securityContext.authenticate(request, response,
			AuthenticationParameters
				.withParams()
				.credential(credential)
				.newAuthentication(true));

		if (authenticationStatus.equals(AuthenticationStatus.SUCCESS))
		{
			return "redirect:benutzer";
		}
		if (authenticationStatus.equals(AuthenticationStatus.SEND_FAILURE))
		{
			fehlermeldungen.put("AuthentifizierungFehlgeschlagen", "Benutzername oder Passwort falsch!");
			return index();
		}
		else
		{
			fehlermeldungen.put("UnerwarteteException",
				"Unerwarteter Fehler während des Logins: " + authenticationStatus.name());
			return index();
		}
	}
}
