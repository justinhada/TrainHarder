package de.justinharder.trainharder.view.authentifizierung;

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.trainharder.model.services.authentifizierung.RegistrierungService;
import de.justinharder.trainharder.view.dto.Registrierung;
import lombok.AccessLevel;
import lombok.Setter;

@Setter
@Controller
@Path("/join")
public class RegistrierungController
{
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
	private RegistrierungService registrierungService;

	@GET
	public String index()
	{
		return "/join.xhtml";
	}

	@POST
	public String registriere(@BeanParam final Registrierung registrierung)
	{
		Preconditions.checkNotNull(registrierung, "Zum Beitreten wird eine gültige Registrierung benötigt!");

		if (bindingResult.isFailed())
		{
			models.put("fehler", bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList()));
			return index();
		}

		try
		{
			models.put("authentifizierung", registrierungService.registriere(registrierung));
			return erfolgreich();
		}
		catch (final MailBereitsRegistriertException | BenutzernameVergebenException | PasswortNichtSicherException e)
		{
			models.put("fehler", e.getMessage());
			return index();
		}
	}

	@GET
	@Path("/success")
	public String erfolgreich()
	{
		return "/success.xhtml";
	}
}
