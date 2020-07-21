package de.justinharder.trainharder.view.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;

import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.view.dto.Login;

public class LoginControllerSollte
{
	private LoginController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;

	@BeforeEach
	public void setup()
	{
		sut = new LoginController();

		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);

		sut.setRequest(mock(HttpServletRequest.class));
		sut.setResponse(mock(HttpServletResponse.class));
		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setSecurityContext(securityContext);
	}

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck(final Principal principal)
	{
		when(securityContext.getCallerPrincipal()).thenReturn(principal);
	}

	private void angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck()
	{
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(null);
	}

	private void angenommenDasBindingResultFailed()
	{
		when(bindingResult.isFailed()).thenReturn(true);
	}

	private void angenommenDerSecurityContextGibtAuthenticationStatusZurueck(
		final AuthenticationStatus authenticationStatus)
	{
		when(securityContext.authenticate(any(HttpServletRequest.class), any(HttpServletResponse.class),
			any(AuthenticationParameters.class))).thenReturn(authenticationStatus);
	}

	@Test
	@DisplayName("zur Login-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	public void test01()
	{
		final var callerPrincipal = new CallerPrincipal("harder");
		final var erwartet = "redirect:benutzer/" + callerPrincipal.getName();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Login-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	public void test02()
	{
		final var erwartet = "/login.xhtml";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		final var ergebnis = sut.index();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn der Login null ist")
	public void test03()
	{
		final var erwartet = "Zur Authentifizierung wird ein gültiger Login benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.login(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Login-Seite navigieren")
	public void test04()
	{
		final var erwartet = "/login.xhtml";
		angenommenDasBindingResultFailed();

		final var ergebnis = sut.login(new Login("harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(bindingResult).isFailed();
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei falschem Benutzernamen oder Passwort zurück zur Login-Seite navigieren")
	public void test05()
	{
		final var erwartet = "/login.xhtml";
		angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus.SEND_FAILURE);

		final var ergebnis = sut.login(new Login("harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(bindingResult).isFailed();
		verify(models).put("fehler", "Der Benutzername oder das Passwort ist leider falsch!");
	}

	@Test
	@DisplayName("bei erfolgreichem Login zur Start-Seite navigieren")
	public void test06()
	{
		final var erwartet = "redirect:start";
		angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus.SUCCESS);

		final var ergebnis = sut.login(new Login("harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(bindingResult).isFailed();
	}

	@Test
	@DisplayName("bei unerwartetem Ergebnis zurück zur Login-Seite navigieren")
	public void test07()
	{
		final var erwartet = "/login.xhtml";
		angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus.NOT_DONE);

		final var ergebnis = sut.login(new Login("harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(bindingResult).isFailed();
		verify(models).put("unerwartet", "Unerwarteter Fehler während des Logins: NOT_DONE");
	}
}
