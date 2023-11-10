package de.justinharder.trainharder.model.services.authentifizierung;

import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.setup.Testdaten;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrainHarderIdentityStoreSollte
{
	private TrainHarderIdentityStore sut;
	private LoginService loginService;

	@BeforeEach
	void setup()
	{
		loginService = mock(LoginService.class);

		sut = new TrainHarderIdentityStore();

		sut.setLoginService(loginService);
	}

	private void angenommenDerAuthentifizierungServiceWirftLoginExcepion() throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		when(loginService.login(anyString(), anyString())).thenThrow(LoginException.class);
	}

	private void angenommenDerAuthentifizierungServiceLoggtEin(AuthentifizierungDto authentifizierungDto) throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		when(loginService.login(anyString(), anyString())).thenReturn(authentifizierungDto);
	}

	@Test
	@DisplayName("eine Authentifizierung validieren und ein invalides Ergebnis zurückgeben")
	void test01() throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var erwartet = CredentialValidationResult.INVALID_RESULT;
		angenommenDerAuthentifizierungServiceWirftLoginExcepion();

		var ergebnis = sut.validate(new UsernamePasswordCredential("harder", "JustinHarder98"));

		assertThat(ergebnis.getStatus()).isEqualTo(erwartet.getStatus());
	}

	@Test
	@DisplayName("eine Authentifizierung valdidieren und ein nicht valides Ergebnis zurückgeben")
	void test02()
	{
		var erwartet = CredentialValidationResult.NOT_VALIDATED_RESULT;

		var ergebnis = sut.validate(new Credential() {});

		assertThat(ergebnis.getStatus()).isEqualTo(erwartet.getStatus());
	}

	@Test
	@DisplayName("eine Authentifizierung erfolgreich validieren")
	void test03() throws LoginException, InvalidKeySpecException, NoSuchAlgorithmException
	{
		var authentifizierungDto = Testdaten.AUTHENTIFIZIERUNG_DTO_JUSTIN;
		var erwartet = new CredentialValidationResult(authentifizierungDto.getBenutzername());
		angenommenDerAuthentifizierungServiceLoggtEin(authentifizierungDto);

		var ergebnis = sut.validate(new UsernamePasswordCredential("harder", "JustinHarder98"));

		assertThat(ergebnis.getStatus()).isEqualTo(erwartet.getStatus());
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.setLoginService(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.validate(null)));
	}
}
