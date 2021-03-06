package de.justinharder.trainharder.view.authentifizierung;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.RegistrierungService;
import de.justinharder.trainharder.view.dto.Registrierung;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.stream.Collectors;

@Controller
@Path(value = "/join")
public class RegistrierungController
{
	private static final String FEHLER = "fehler";

	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;
	@Inject
	private Models models;
	@Inject
	private BindingResult bindingResult;
	@Inject
	private SecurityContext securityContext;

	@Inject
	private RegistrierungService registrierungService;

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

	public void setRegistrierungService(@NonNull RegistrierungService registrierungService)
	{
		this.registrierungService = registrierungService;
	}

	@GET
	public String index()
	{
		if (securityContext.getCallerPrincipal() != null)
		{
			return "redirect:benutzer/" + securityContext.getCallerPrincipal().getName();
		}

		return "/join.xhtml";
	}

	@POST
	public String registriere(@NonNull @BeanParam Registrierung registrierung)
	{
		if (bindingResult.isFailed())
		{
			models.put(FEHLER, bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList()));
			return index();
		}

		try
		{
			models.put("authentifizierung", registrierungService.registriere(registrierung));
			return erfolgreich();
		}
		catch (MailVergebenException | BenutzernameVergebenException | PasswortUnsicherException | InvalidKeySpecException | NoSuchAlgorithmException e)
		{
			models.put(FEHLER, e.getMessage());
			return index();
		}
	}

	@GET
	@Path(value = "/success")
	public String erfolgreich()
	{
		return "/success.xhtml";
	}

	@GET
	@Path(value = "/{id}")
	public String aktiviere(@NonNull @PathParam(value = "id") String id)
	{
		try
		{
			registrierungService.aktiviere(id);
			return "/aktiviert.xhtml";
		}
		catch (AuthentifizierungNichtGefundenException e)
		{
			models.put(FEHLER, e.getMessage());
			return "/error";
		}
	}
}