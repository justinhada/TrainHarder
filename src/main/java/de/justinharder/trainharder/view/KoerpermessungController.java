package de.justinharder.trainharder.view;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.KoerpermessungService;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;
import lombok.AccessLevel;
import lombok.Setter;

@Setter
@Controller
@Path(value = "/koerpermessungen")
public class KoerpermessungController extends AbstractController
{
	private static final String REDIRECT_TO_LOGIN = "redirect:login";

	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletRequest request;
	@Context
	@Setter(value = AccessLevel.NONE)
	private HttpServletResponse response;

	@Inject
	private KoerpermessungService koerpermessungService;

	@GET
	@Override
	public String index()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}
		initialisiere();

		return "/koerpermessungen/index.xhtml";
	}

	@GET
	@Path(value = "/{benutzername}")
	public String koerpermessdaten(@PathParam(value = "benutzername") final String benutzername)
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}

		try
		{
			final var authentifizierungDto = getAuthentifizierungDto();
			final var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());

			models.put("authentifizierung", authentifizierungDto);
			models.put("benutzer", benutzerDto);
			models.put("koerpermessungen", benutzerDto.getKoerpermessungen());

			return "/koerpermessungen/benutzerdaten.xhtml";
		}
		catch (AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
			return REDIRECT_TO_LOGIN;
		}
	}

	@POST
	@Path(value = "/{benutzername}")
	public String addKoerpermessung(@BeanParam final Koerpermessdaten koerpermessdaten)
	{
		Preconditions.checkNotNull(koerpermessdaten,
			"Die Erstellung der Koerpermessung benötigt gültige Koerpermessdaten!");

		try
		{
			final var authentifizierungDto = getAuthentifizierungDto();
			final var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());
			koerpermessungService.erstelleKoerpermessung(koerpermessdaten, benutzerDto.getPrimaerschluessel());
			return koerpermessdaten(authentifizierungDto.getBenutzername());
		}
		catch (AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
			return REDIRECT_TO_LOGIN;
		}
	}
}
