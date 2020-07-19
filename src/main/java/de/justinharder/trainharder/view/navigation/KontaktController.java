package de.justinharder.trainharder.view.navigation;

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.ParamError;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.model.services.KontaktService;
import de.justinharder.trainharder.view.dto.Kontaktformular;
import lombok.AccessLevel;
import lombok.Setter;

@Setter
@Controller
@Path("/kontakt")
public class KontaktController
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
	private SecurityContext securityContext;
	@Inject
	private BindingResult bindingResult;

	@Inject
	private AuthentifizierungService authentifizierungService;
	@Inject
	private BenutzerService benutzerService;
	@Inject
	private KontaktService kontaktService;

	@GET
	public String index()
	{
		initialisiere();

		return "/kontakt.xhtml";
	}

	private void initialisiere()
	{
		try
		{
			if (securityContext.getCallerPrincipal() != null)
			{
				final var authentifizierungDto =
					authentifizierungService.ermittleZuBenutzername(securityContext.getCallerPrincipal().getName());
				models.put("authentifizierung", authentifizierungDto);
				models.put("benutzer",
					benutzerService.ermittleZuAuthentifizierung(authentifizierungDto.getPrimaerschluessel()));
			}
		}
		catch (final AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
		}
	}

	@POST
	public String kontaktiere(@BeanParam final Kontaktformular kontaktformular)
	{
		Preconditions.checkNotNull(kontaktformular, "Zum Kontaktieren wird ein gültiges Kontaktformular benötigt!");

		if (bindingResult.isFailed())
		{
			models.put("fehler", bindingResult.getAllErrors().stream()
				.map(ParamError::getMessage)
				.collect(Collectors.toList()));
			return index();
		}

		kontaktService.kontaktiere(kontaktformular);
		models.put("kontaktformular", kontaktformular);
		return erfolgreich();
	}

	@GET
	@Path("/success")
	public String erfolgreich()
	{
		return "/kontaktiert.xhtml";
	}
}
