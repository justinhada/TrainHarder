package de.justinharder.trainharder.view.authentifizierung;

import java.util.List;
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

import de.justinharder.trainharder.model.domain.dto.Registrierung;
import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzernameVergebenException;
import de.justinharder.trainharder.model.domain.exceptions.MailBereitsRegistriertException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortNichtSicherException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;

@Path("/join")
@Controller
public class RegistrierungController
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
	private AuthentifizierungService authentifizierungService;

	@GET
	public String index()
	{
		return "/join.xhtml";
	}

	@POST
	public String registriere(@BeanParam final Registrierung registrierung)
	{
		if (bindingResult.isFailed())
		{
			final List<String> errors = bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList());
			models.put("errors", errors);
			return index();
		}

		try
		{
			final var authentifizierungEintrag = authentifizierungService.registriere(registrierung);
			models.put("authentifizierung", authentifizierungEintrag);
			return erfolgreich();
		}
		catch (final MailBereitsRegistriertException | BenutzernameVergebenException | PasswortNichtSicherException
			| AuthentifizierungNichtGefundenException e)
		{
			models.put("errors", e.getMessage());
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
