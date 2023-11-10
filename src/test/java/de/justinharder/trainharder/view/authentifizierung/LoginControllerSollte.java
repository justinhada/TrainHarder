package de.justinharder.trainharder.view.authentifizierung;

import de.justinharder.trainharder.model.domain.exceptions.AuthentifizierungNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.PasswortUnsicherException;
import de.justinharder.trainharder.model.services.authentifizierung.LoginService;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.Login;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LoginControllerSollte
{
	private LoginController sut;

	private Models models;
	private BindingResult bindingResult;
	private SecurityContext securityContext;
	private LoginService loginService;

	@BeforeEach
	void setup()
	{
		models = mock(Models.class);
		bindingResult = mock(BindingResult.class);
		securityContext = mock(SecurityContext.class);
		loginService = mock(LoginService.class);

		sut = new LoginController();

		sut.setRequest(mock(HttpServletRequest.class));
		sut.setResponse(mock(HttpServletResponse.class));
		sut.setModels(models);
		sut.setBindingResult(bindingResult);
		sut.setSecurityContext(securityContext);
		sut.setLoginService(loginService);
	}

	private void angenommenDerSecurityContextGibtCallerPrincipalZurueck(Principal principal)
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

	private void angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus authenticationStatus)
	{
		when(securityContext.authenticate(any(HttpServletRequest.class), any(HttpServletResponse.class), any(
			AuthenticationParameters.class))).thenReturn(authenticationStatus);
	}

	private void angenommenDerLoginServiceWirftAuthentifizierungNichtGefundenException(String mail) throws AuthentifizierungNichtGefundenException
	{
		doThrow(new AuthentifizierungNichtGefundenException("Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!")).when(loginService).sendeResetMail(anyString(), any(UUID.class));
	}

	private void angenommenDerLoginServiceWirftPasswortUnsicherException(UUID resetUuid, String passwort)
		throws PasswortUnsicherException, AuthentifizierungNichtGefundenException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		doThrow(new PasswortUnsicherException("Das Passwort ist unsicher!")).when(loginService).resetPassword(resetUuid, passwort);
	}

	@Test
	@DisplayName("zur Login-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	void test01()
	{
		var callerPrincipal = new CallerPrincipal("harder");
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		assertThat(sut.index()).isEqualTo("redirect:benutzer/" + callerPrincipal.getName());
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Login-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	void test02()
	{
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		assertThat(sut.index()).isEqualTo("/login.xhtml");
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setModels(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setSecurityContext(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setLoginService(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setRequest(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setResponse(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setBindingResult(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.login(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.resetMail(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.resetPasswordView(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.resetPassword(null, "passwort")),
			() -> assertThrows(NullPointerException.class, () -> sut.resetPassword("resetUuid", null)));
	}

	@Test
	@DisplayName("bei fehlgeschlagenem BindingResult zurück zur Login-Seite navigieren")
	void test04()
	{
		angenommenDasBindingResultFailed();

		assertThat(sut.login(new Login("harder", "Justinharder#98"))).isEqualTo("/login.xhtml");
		verify(bindingResult).isFailed();
		verify(models).put("fehler", new ArrayList<>());
	}

	@Test
	@DisplayName("bei falschem Benutzernamen oder Passwort zurück zur Login-Seite navigieren")
	void test05()
	{
		var erwartet = "/login.xhtml";
		angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus.SEND_FAILURE);

		var ergebnis = sut.login(new Login("harder", "Justinharder#98"));

		assertThat(ergebnis).isEqualTo(erwartet);
		verify(bindingResult).isFailed();
		verify(models).put("fehler", "Der Benutzername oder das Passwort ist leider falsch!");
	}

	@Test
	@DisplayName("zur Start-Seite navigieren, wenn der Login erfolgreich ist")
	void test06()
	{
		angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus.SUCCESS);

		assertThat(sut.login(new Login("harder", "Justinharder#98"))).isEqualTo("redirect:start");
		verify(bindingResult).isFailed();
	}

	@Test
	@DisplayName("bei unerwartetem Ergebnis zurück zur Login-Seite navigieren")
	void test07()
	{
		angenommenDerSecurityContextGibtAuthenticationStatusZurueck(AuthenticationStatus.NOT_DONE);

		assertThat(sut.login(new Login("harder", "Justinharder#98"))).isEqualTo("/login.xhtml");
		verify(bindingResult).isFailed();
		verify(models).put("unerwartet", "Unerwarteter Fehler während des Logins: NOT_DONE");
	}

	@Test
	@DisplayName("zur Reset-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	void test08()
	{
		var callerPrincipal = new CallerPrincipal("harder");
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		assertThat(sut.resetMailView()).isEqualTo("redirect:benutzer/" + callerPrincipal.getName());
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Reset-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	void test09()
	{
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		assertThat(sut.resetMailView()).isEqualTo("/reset.xhtml");
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("bei fehlerhafter Mail zurück zur Reset-Seite navigieren")
	void test10() throws AuthentifizierungNichtGefundenException
	{
		var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();
		angenommenDerLoginServiceWirftAuthentifizierungNichtGefundenException(mail);

		assertThat(sut.resetMail(mail)).isEqualTo("/reset.xhtml");
		verify(loginService).sendeResetMail(eq(mail), any(UUID.class));
		verify(models).put("fehler", "Die Authentifizierung mit der Mail \"" + mail + "\" existiert nicht!");
	}

	@Test
	@DisplayName("bei erfolgreichem Senden der Reset-Mail zur Reset-Success-Seite navigieren")
	void test11() throws AuthentifizierungNichtGefundenException
	{
		var mail = Testdaten.AUTHENTIFIZIERUNG_JUSTIN.getMail();

		assertThat(sut.resetMail(mail)).isEqualTo("/reset-success.xhtml");
		verify(loginService).sendeResetMail(eq(mail), any(UUID.class));
	}

	@Test
	@DisplayName("zur Reset-Password-Seite per GET navigieren, wenn ein Benutzer angemeldet ist")
	void test12()
	{
		var callerPrincipal = new CallerPrincipal("harder");
		angenommenDerSecurityContextGibtCallerPrincipalZurueck(callerPrincipal);

		assertThat(sut.resetPasswordView(UUID.randomUUID().toString())).isEqualTo("redirect:benutzer/" + callerPrincipal.getName());
		verify(securityContext, times(2)).getCallerPrincipal();
	}

	@Test
	@DisplayName("zur Reset-Password-Seite per GET navigieren, wenn kein Benutzer angemeldet ist")
	void test13()
	{
		angenommenDerSecurityContextGibtKeinCallerPrincipalZurueck();

		assertThat(sut.resetPasswordView(UUID.randomUUID().toString())).isEqualTo("/reset-password.xhtml");
		verify(securityContext).getCallerPrincipal();
	}

	@Test
	@DisplayName("bei fehlerhaftem Passwort zurück zur Reset-Password-Seite navigieren")
	void test14() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var resetUuid = UUID.randomUUID();
		var passwort = "UnsicheresPasswort";
		angenommenDerLoginServiceWirftPasswortUnsicherException(resetUuid, passwort);

		assertThat(sut.resetPassword(resetUuid.toString(), passwort)).isEqualTo("/reset-password.xhtml");
		verify(loginService).resetPassword(resetUuid, passwort);
		verify(models).put("fehler", "Das Passwort ist unsicher!");
	}

	@Test
	@DisplayName("bei erfolgreichem Password-Reset zur Reset-Password-Success-Seite navigieren")
	void test15() throws PasswortUnsicherException, AuthentifizierungNichtGefundenException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var resetUuid = UUID.randomUUID();
		var passwort = "UnsicheresPasswort";

		assertThat(sut.resetPassword(resetUuid.toString(), passwort)).isEqualTo("/reset-password-success.xhtml");
		verify(loginService).resetPassword(resetUuid, passwort);
	}
}
