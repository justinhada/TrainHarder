package de.justinharder.trainharder.view.authentifizierung;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.LoginService;
import de.justinharder.trainharder.view.dto.Login;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import lombok.NonNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@Path(value = "/login")
public class LoginController
{
	private static final String FEHLER = "fehler";
	private static final String REDIRECT_TO_BENUTZER = "redirect:benutzer/";

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

	public void setRequest(@NonNull HttpServletRequest request)
	{
		this.request = request;
	}

	public void setResponse(@NonNull HttpServletResponse response)
	{
		this.response = response;
	}

	public void setModels(@NonNull Models models)
	{
		this.models = models;
	}

	public void setBindingResult(@NonNull BindingResult bindingResult)
	{
		this.bindingResult = bindingResult;
	}

	public void setSecurityContext(@NonNull SecurityContext securityContext)
	{
		this.securityContext = securityContext;
	}

	public void setLoginService(@NonNull LoginService loginService)
	{
		this.loginService = loginService;
	}

	@GET
	public String index()
	{
		if (securityContext.getCallerPrincipal() != null)
		{
			return REDIRECT_TO_BENUTZER + securityContext.getCallerPrincipal().getName();
		}

		return "/login.xhtml";
	}

	@POST
	public String login(@NonNull @BeanParam Login login)
	{
		if (bindingResult.isFailed())
		{
			models.put(FEHLER, bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList()));
			return index();
		}

		var credential = new UsernamePasswordCredential(login.getBenutzername(), login.getPasswort());
		var authenticationStatus = securityContext.authenticate(request, response, AuthenticationParameters.withParams().credential(credential).newAuthentication(true));

		if (authenticationStatus.equals(AuthenticationStatus.SUCCESS))
		{
			return "redirect:start";
		}
		if (authenticationStatus.equals(AuthenticationStatus.SEND_FAILURE))
		{
			models.put(FEHLER, "Der Benutzername oder das Passwort ist leider falsch!");
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
			return REDIRECT_TO_BENUTZER + securityContext.getCallerPrincipal().getName();
		}

		return "/reset.xhtml";
	}

	@POST
	@Path(value = "/reset")
	public String resetMail(@NonNull @FormParam(value = "mail") String mail)
	{
		try
		{
			loginService.sendeResetMail(mail, UUID.randomUUID());
			return "/reset-success.xhtml";
		}
		catch (AuthentifizierungNichtGefundenException e)
		{
			models.put(FEHLER, e.getMessage());
			return resetMailView();
		}
	}

	@GET
	@Path(value = "/reset/{id}")
	public String resetPasswordView(@NonNull @PathParam(value = "id") String resetUuid)
	{
		if (securityContext.getCallerPrincipal() != null)
		{
			return REDIRECT_TO_BENUTZER + securityContext.getCallerPrincipal().getName();
		}

		models.put("resetUuid", resetUuid);
		return "/reset-password.xhtml";
	}

	@POST
	@Path(value = "/reset/{id}")
	public String resetPassword(@NonNull @PathParam(value = "id") String resetUuid, @NonNull @FormParam(value = "passwort") String passwort)
	{
		try
		{
			loginService.resetPassword(UUID.fromString(resetUuid), passwort);
			return "/reset-password-success.xhtml";
		}
		catch (PasswortUnsicherException | AuthentifizierungNichtGefundenException | InvalidKeySpecException | NoSuchAlgorithmException e)
		{
			models.put(FEHLER, e.getMessage());
			return resetPasswordView(resetUuid);
		}
	}
}
