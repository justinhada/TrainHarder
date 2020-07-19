package de.justinharder.trainharder.view;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.mvc.Models;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.BenutzerNichtGefundenException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;
import de.justinharder.trainharder.model.services.BenutzerService;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import de.justinharder.trainharder.view.dto.BenutzerDto;

public abstract class AbstractControllerSollte
{
	protected Models models;
	protected SecurityContext securityContext;
	protected AuthentifizierungService authentifizierungService;
	protected BenutzerService benutzerService;

	protected void setup(final AbstractController sut)
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

	protected void angenommenDerSecurityContextGibtCallerPrincipalZurueck(final Principal principal)
	{
		when(securityContext.getCallerPrincipal()).thenReturn(principal);
	}

	protected void angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck()
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(null);
	}

	protected void angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(
		final String benutzername) throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername))
			.thenThrow(new AuthentifizierungNichtGefundenException(
				"Die Authentifizierung mit dem Benutzernamen \"" + benutzername + "\" existiert nicht!"));
	}

	protected void angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
		final String benutzername, final AuthentifizierungDto authentifizierungDto)
		throws AuthentifizierungNichtGefundenException
	{
		when(authentifizierungService.ermittleZuBenutzername(benutzername)).thenReturn(authentifizierungDto);
	}

	protected void angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(final String authentifizierungId,
		final BenutzerDto benutzerDto) throws BenutzerNichtGefundenException
	{
		when(benutzerService.ermittleZuAuthentifizierung(authentifizierungId)).thenReturn(benutzerDto);
	}

	protected String zurSeiteNavigierenOhneAngemeldetenBenutzer(final AbstractController sut)
	{
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		return sut.index();
	}

	protected String zurSeiteNavigierenMitServicefehler(
		final AbstractController sut,
		final AuthentifizierungDto authentifizierungDto) throws AuthentifizierungNichtGefundenException
	{
		final var callerPrincipal = new CallerPrincipal(authentifizierungDto.getBenutzername());
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceWirftAuthentifizierungNichtGefundenException(callerPrincipal.getName());

		return sut.index();
	}

	protected String zurSeiteNavigierenMitAngemeldetenBenutzer(
		final AbstractController sut,
		final AuthentifizierungDto authentifizierungDto,
		final BenutzerDto benutzerDto) throws AuthentifizierungNichtGefundenException, BenutzerNichtGefundenException
	{
		final var callerPrincipal = new CallerPrincipal(authentifizierungDto.getBenutzername());
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);
		angenommenDerAuthentifizierungServiceErmitteltAuthentifizierungDtoZuBenutzername(
			callerPrincipal.getName(),
			authentifizierungDto);
		angenommenDerBenutzerServiceErmitteltBenutzerDtoZuAuthentifizierung(
			authentifizierungDto.getPrimaerschluessel(),
			benutzerDto);

		return sut.index();
	}
}
