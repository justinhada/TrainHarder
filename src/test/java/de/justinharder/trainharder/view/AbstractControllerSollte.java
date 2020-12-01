package de.justinharder.trainharder.view;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;

import javax.mvc.Models;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;
import java.security.Principal;
import java.util.function.Supplier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AbstractControllerSollte
{
	protected Models models;
	protected SecurityContext securityContext;
	protected AuthentifizierungService authentifizierungService;
	protected BenutzerService benutzerService;

	protected void setup(AbstractController sut)
	{
		models = mock(Models.class);
		securityContext = mock(SecurityContext.class);
		authentifizierungService = mock(AuthentifizierungService.class);
		benutzerService = mock(BenutzerService.class);

		sut.setModels(models);
		sut.setSecurityContext(securityContext);
		sut.setAuthentifizierungService(authentifizierungService);
		sut.setBenutzerService(benutzerService);
	}

	protected void angenommenDerSecurityContextGibtCallerPrincipalZurueck(Principal principal)
	{
		when(securityContext.getCallerPrincipal()).thenReturn(principal);
	}

	protected void angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck()
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(null);
	}

	protected void angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(
		String benutzername) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername))
			.thenThrow(new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!"));
	}

	protected void angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(String benutzername,
		AuthentifizierungDto authentifizierungDto)
		throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierungDto);
	}

	protected void angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(String authentifizierungId,
		BenutzerDto benutzerDto) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId)).thenReturn(benutzerDto);
	}

	protected String zurSeiteNavigierenOhneAngemeldetenBenutzer(Supplier<String> methode)
	{
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		return methode.get();
	}

	protected String zurSeiteNavigierenMitServicefehler(Supplier<String> methode,
		AuthentifizierungDto authentifizierungDto) throws AuthentifizierungNichtGefundenException
	{
		var callerPrincipal = new CallerPrincipal(authentifizierungDto.getBenutzername());
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(callerPrincipal.getName());

		return methode.get();
	}

	protected String zurSeiteNavigierenMitAngemeldetenBenutzer(Supplier<String> methode,
		AuthentifizierungDto authentifizierungDto, BenutzerDto benutzerDto)
		throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		var callerPrincipal = new CallerPrincipal(authentifizierungDto.getBenutzername());
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
			callerPrincipal.getName(),
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(
			authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		return methode.get();
	}
}
