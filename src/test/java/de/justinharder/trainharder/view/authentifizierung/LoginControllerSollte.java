package de.justinharder.trainharder.view.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.UUID;

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

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.LoginService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Login;

public class LoginControllerSollte
{
	private LoginController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;
	private LoginService loginService;

	@BeforeEach
	public void setup()
	{
		sut = new LoginController();

		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);
		loginService = mock(LoginService.class);

		sut.setRequest(mock(HttpServletRequest.class));
		sut.setResponse(mock(HttpServletResponse.class));
		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setSecurityContext(securityContext);
		sut.setLoginService(loginService);
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

	private void angenommenDerLoginServiceWirftAuthentifizierungNichtGefundenException(final String mail)
		throws AuthentifizierungNichtGefundenException
	{
		doThrow(new AuthentifizierungNichtGefundenException(
			"Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!")).when(loginService)
				.sendeResetMail(anyString(), any(UUID.class));
	}

	private void angenommenDerLoginServiceWirftPasswortUnsicherException(final UUID resetUuid, final String passwort)
		throws PasswortUnsicherException, AuthentifizierungNichtGefundenException, InvalidKeySpecException,
		NoSuchAlgorithmException
	{
		doThrow(new PasswortUnsicherException("Das Passwort ist unsicher!")).when(loginService).resetPassword(resetUuid,
			passwort);
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
	@DisplayName("zur Start-Seite navigieren, wenn der Login erfolgreich ist")
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

	@Test
	@DisplayName("zur Reset-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	public void test08()
	{
		final var callerPrincipal = new CallerPrincipal("harder");
		final var erwartet = "redirect:benutzer/" + callerPrincipal.getName();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		final var ergebnis = sut.resetMailView();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Reset-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	public void test09()
	{
		final var erwartet = "/reset.xhtml";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		final var ergebnis = sut.resetMailView();

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die Mail null ist")
	public void test10()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird eine gültige Mail benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.resetMail(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlerhafter Mail zurück zur Reset-Seite navigieren")
	public void test11() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/reset.xhtml";
		final var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		angenommenDerLoginServiceWirftAuthentifizierungNichtGefundenException(mail);

		final var ergebnis = sut.resetMail(mail);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(loginService).sendeResetMail(eq(mail), any(UUID.class));
		verify(models).put("fehler", "Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!");
	}

	@Test
	@DisplayName("bei erfolgreichem Senden der Reset-Mail zur Reset-Success-Seite navigieren")
	public void test12() throws AuthentifizierungNichtGefundenException
	{
		final var erwartet = "/reset-success.xhtml";

		final var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		final var ergebnis = sut.resetMail(mail);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(loginService).sendeResetMail(eq(mail), any(UUID.class));
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ResetUUID null ist")
	public void test13()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.resetPasswordView(null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("zur Reset-Password-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	public void test14()
	{
		final var callerPrincipal = new CallerPrincipal("harder");
		final var erwartet = "redirect:benutzer/" + callerPrincipal.getName();
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		final var ergebnis = sut.resetPasswordView(UUID.randomUUID().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Reset-Password-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	public void test15()
	{
		final var erwartet = "/reset-password.xhtml";
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		final var ergebnis = sut.resetPasswordView(UUID.randomUUID().toString());

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn die ResetUUID null ist")
	public void test16()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird eine gültige ResetUUID benötigt!";

		final var exception = assertThrows(NullPointerException.class, () -> sut.resetPassword(null, null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("NullPointerException werfen, wenn das Passwort null ist")
	public void test17()
	{
		final var erwartet = "Zum Zurücksetzen des Passworts wird ein gültiges Passwort benötigt!";

		final var exception =
			assertThrows(NullPointerException.class, () -> sut.resetPassword(UUID.randomUUID().toString(), null));

		assertThat(exception.getMessage()).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("bei fehlerhaftem Passwort zurück zur Reset-Password-Seite navigieren")
	public void test18() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException,
		InvalidKeySpecException, NoSuchAlgorithmException
	{

		final var erwartet = "/reset-password.xhtml";
		final var resetUuid = UUID.randomUUID();
		final var passwort = "UnsicheresPasswort";
		angenommenDerLoginServiceWirftPasswortUnsicherException(resetUuid, passwort);

		final var ergebnis = sut.resetPassword(resetUuid.toString(), passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(loginService).resetPassword(resetUuid, passwort);
		verify(models).put("fehler", "Das Passwort ist unsicher!");
	}

	@Test
	@DisplayName("bei erfolgreichem Password-Reset zur Reset-Password-Success-Seite navigieren")
	public void test19() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException,
		InvalidKeySpecException, NoSuchAlgorithmException
	{
		final var erwartet = "/reset-password-success.xhtml";

		final var resetUuid = UUID.randomUUID();
		final var passwort = "UnsicheresPasswort";
		final var ergebnis = sut.resetPassword(resetUuid.toString(), passwort);

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(loginService).resetPassword(resetUuid, passwort);
	}
}
