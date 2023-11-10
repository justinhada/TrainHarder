package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;
import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.security.enterprise.SecurityContext;
import lombok.NonNull;

public abstract class AbstractController
{
	@Inject
	protected Models models;
	@Inject
	protected SecurityContext securityContext;

	@Inject
	protected AuthentifizierungService authentifizierungService;
	@Inject
	protected BenutzerService benutzerService;

	public void setModels(@NonNull Models models)
	{
		this.models = models;
	}

	public void setSecurityContext(@NonNull SecurityContext securityContext)
	{
		this.securityContext = securityContext;
	}

	public void setAuthentifizierungService(@NonNull AuthentifizierungService authentifizierungService)
	{
		this.authentifizierungService = authentifizierungService;
	}

	public void setBenutzerService(@NonNull BenutzerService benutzerService)
	{
		this.benutzerService = benutzerService;
	}

	protected String initialisiere(String pfad)
	{
		try
		{
			if (securityContext.getCallerPrincipal() != null)
			{
				var authentifizierungDto = getAuthentifizierungDto();
				models.put("authentifizierung", authentifizierungDto);
				models.put("benutzer", getBenutzerDto(authentifizierungDto.getPrimaerschluessel()));
			}
		}
		catch (AuthentifizierungNichtGefundenException | BenutzerNichtGefundenException e)
		{
			models.put("fehler", e.getMessage());
		}

		return pfad;
	}

	protected AuthentifizierungDto getAuthentifizierungDto() throws AuthentifizierungNichtGefundenException
	{
		return authentifizierungService.ermittleZuBenutzername(securityContext.getCallerPrincipal().getName());
	}

	protected BenutzerDto getBenutzerDto(String authentifizierungId) throws BenutzerNichtGefundenException
	{
		return benutzerService.ermittleZuAuthentifizierung(authentifizierungId);
	}

	public abstract String index();
}
