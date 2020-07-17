package de.justinharder.trainharder.view.authentifizierung;

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

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.view.dto.Login;
import lombok.Setter;

@Setter
@Controller
@Path("/login")
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

	@GET
	public String index()
	{
		return "/login.xhtml";
	}

	@POST
	public String login(@BeanParam final Login login)
	{
		Preconditions.checkNotNull(login, "Zur Authentifizierung wird ein gültiger Login benötigt!");

		if (bindingResult.isFailed())
		{
			models.put("fehler", bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList()));
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
			return "redirect:start";
		}
		if (authenticationStatus.equals(AuthenticationStatus.SEND_FAILURE))
		{
			models.put("fehler", "Der Benutzername oder das Passwort ist leider falsch!");
		}
		else
		{
			models.put("unerwartet", "Unerwarteter Fehler während des Logins: " + authenticationStatus.toString());
		}
		return index();
	}
}
