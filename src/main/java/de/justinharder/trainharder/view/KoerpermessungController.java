package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.KoerpermessungService;
import de.justinharder.trainharder.view.dto.Koerpermessdaten;
import lombok.NonNull;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

@Controller
@Path(value = "/koerpermessungen")
public class KoerpermessungController extends AbstractController
{
	private static final String REDIRECT_TO_LOGIN = "redirect:login";

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	@Inject
	private KoerpermessungService koerpermessungService;

	public void setKoerpermessungService(@NonNull KoerpermessungService koerpermessungService)
	{
		this.koerpermessungService = koerpermessungService;
	}

	@GET
	@Override
	public String index()
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}

		return initialisiere("/koerpermessungen/index.xhtml");
	}

	@GET
	@Path(value = "/{benutzername}")
	public String koerpermessdaten(@NonNull @PathParam(value = "benutzername") String benutzername)
	{
		if (securityContext.getCallerPrincipal() == null)
		{
			return REDIRECT_TO_LOGIN;
		}

		try
		{
			var authentifizierungDto = getAuthentifizierungDto();
			var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());

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
	public String addKoerpermessung(@NonNull @BeanParam Koerpermessdaten koerpermessdaten)
	{
		try
		{
			var authentifizierungDto = getAuthentifizierungDto();
			var benutzerDto = getBenutzerDto(authentifizierungDto.getPrimaerschluessel());
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