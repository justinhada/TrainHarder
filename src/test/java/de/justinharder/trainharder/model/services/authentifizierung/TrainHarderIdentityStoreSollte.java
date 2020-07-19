package de.justinharder.trainharder.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;

public class TrainHarderIdentityStoreSollte
{
	private TrainHarderIdentityStore sut;
	private LoginService loginService;

	@BeforeEach
	public void setup()
	{
		sut = new TrainHarderIdentityStore();

		loginService = mock(LoginService.class);

		sut.setLoginService(loginService);
	}

	private void angenommenDerAuthentifizierungServiceWirftLoginExcepion() throws LoginException
	{
		when(loginService.login(anyString(), anyString())).thenThrow(LoginException.class);
	}

	private void angenommenDerAuthentifizierungServiceLoggtEin(final AuthentifizierungDto authentifizierungDto)
		throws LoginException
	{
		when(loginService.login(anyString(), anyString())).thenReturn(authentifizierungDto);
	}

	@Test
	@DisplayName("eine Authentifizierung validieren und ein invalides Ergebnis zurückgeben")
	public void test01() throws LoginException
	{
		final var erwartet = CredentialValidationResult.INVALID_RESULT;
		angenommenDerAuthentifizierungServiceWirftLoginExcepion();

		final var ergebnis = sut.validate(new UsernamePasswordCredential("harder", "JustinHarder98"));

		assertThat(ergebnis.getStatus()).isEqualTo(erwartet.getStatus());
	}

	@Test
	@DisplayName("eine Authentifizierung valdidieren und ein nicht valides Ergebnis zurückgeben")
	public void test02()
	{
		final var erwartet = CredentialValidationResult.NOT_VALIDATED_RESULT;

		final var ergebnis = sut.validate(new Credential()
		{});

		assertThat(ergebnis.getStatus()).isEqualTo(erwartet.getStatus());
	}

	@Test
	@DisplayName("eine Authentifizierung erfolgreich validieren")
	public void test03() throws LoginException
	{
		final var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		final var erwartet = new CredentialValidationResult(authentifizierungDto.getBenutzername());
		angenommenDerAuthentifizierungServiceLoggtEin(authentifizierungDto);

		final var ergebnis = sut.validate(new UsernamePasswordCredential("harder", "JustinHarder98"));

		assertThat(ergebnis.getStatus()).isEqualTo(erwartet.getStatus());
	}
}
