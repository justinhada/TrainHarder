package de.justinharder.trainharder.view.authentifizierung;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.LoginService;
import de.justinharder.trainharder.view.dto.Login;
import lombok.Setter;

@Setter
@Controller
@Path(value = "/login")
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

	@Inject
	private LoginService loginService;

	@GET
	public String index()
	{
		if (securityContext.getCallerPrincipal() != null)
		{
			return "redirect:benutzer/" + securityContext.getCallerPrincipal().getName();
		}

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

	@GET
	@Path(value = "/reset")
	public String resetMailView()
	{
		if (securityContext.getCallerPrincipal() != null)
		{
			return "redirect:benutzer/" + securityContext.getCallerPrincipal().getName();
		}

		return "/reset.xhtml";
	}

	@POST
	@Path(value = "/reset")
	public String resetMail(@FormParam(value = "mail") final String mail)
	{
		Preconditions.checkNotNull(mail, "Zum Zurücksetzen des Passworts wird eine gültige Mail benötigt!");

		try
		{
			loginService.sendeResetMail(mail, UUID.randomUUID());
			return "/reset-success.xhtml";
		}
		catch (final AuthentifizierungNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
			return resetMailView();
		}
	}

	@GET
	@Path(value = "/reset/{id}")
	public String resetPasswordView(@PathParam(value = "id") final String resetUuid)
	{
		Preconditions.checkNotNull(resetUuid, "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!");

		if (securityContext.getCallerPrincipal() != null)
		{
			return "redirect:benutzer/" + securityContext.getCallerPrincipal().getName();
		}

		models.put("resetUuid", resetUuid);
		return "/reset-password.xhtml";
	}

	@POST
	@Path(value = "/reset/{id}")
	public String resetPassword(
		@PathParam(value = "id") final String resetUuid,
		@FormParam("passwort") final String passwort)
	{
		Preconditions.checkNotNull(resetUuid, "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!");
		Preconditions.checkNotNull(passwort, "Zum Zurücksetzen des Passworts wird ein gültiges Passwort benötigt!");

		try
		{
			loginService.resetPassword(UUID.fromString(resetUuid), passwort);
			return "/reset-password-success.xhtml";
		}
		catch (PasswortUnsicherException | AuthentifizierungNichtGefundenException | InvalidKeySpecException
			| NoSuchAlgorithmException e)
		{
			models.put("fehler", e.getMessage());
			return resetPasswordView(resetUuid);
		}
	}
}
