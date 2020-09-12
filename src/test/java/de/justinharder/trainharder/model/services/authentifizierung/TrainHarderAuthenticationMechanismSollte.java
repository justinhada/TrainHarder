package de.justinharder.trainharder.model.services.authentifizierung;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TrainHarderAuthenticationMechanismSollte
{
	private TrainHarderAuthenticationMechanism sut;
	private IdentityStore identityStore;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpMessageContext httpMessageContext;
	private Credential credential;
	private CredentialValidationResult credentialValidationResult;
	private AuthenticationParameters authenticationParameters;

	@BeforeEach
	void setup()
	{
		sut = new TrainHarderAuthenticationMechanism();

		identityStore = mock(IdentityStore.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		httpMessageContext = mock(HttpMessageContext.class);
		credential = mock(Credential.class);
		credentialValidationResult = mock(CredentialValidationResult.class);
		authenticationParameters = mock(AuthenticationParameters.class);

		sut.setIdentityStore(identityStore);
	}

	private void angenommenDerHttpMessageContextGibtNullZurueck()
	{
		when(httpMessageContext.getAuthParameters()).thenReturn(authenticationParameters);
		when(authenticationParameters.getCredential()).thenReturn(null);
	}

	private void angenommenDerHttpMessageContextGibtNichtsZurueck()
	{
		when(httpMessageContext.doNothing()).thenReturn(AuthenticationStatus.NOT_DONE);
	}

	private void angenommenDerHttpMessageContextGibtCredentialZurueck()
	{
		when(httpMessageContext.getAuthParameters()).thenReturn(authenticationParameters);
		when(authenticationParameters.getCredential()).thenReturn(credential);
	}

	private void angenommenDerHttpMessageContextBenachrichtigtUeberLogin(final AuthenticationStatus erwartet)
	{
		when(identityStore.validate(any(Credential.class))).thenReturn(credentialValidationResult);
		when(httpMessageContext.notifyContainerAboutLogin(credentialValidationResult)).thenReturn(erwartet);
	}

	@Test
	@DisplayName("eine Anfrage validieren, wenn die Credentials null sind")
	void test01() throws AuthenticationException
	{
		final var erwartet = AuthenticationStatus.NOT_DONE;
		angenommenDerHttpMessageContextGibtNullZurueck();
		angenommenDerHttpMessageContextGibtNichtsZurueck();

		final var ergebnis = sut.validateRequest(request, response, httpMessageContext);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Anfrage validieren, wenn die Credentials gültig sind")
	void test02() throws AuthenticationException
	{
		final var erwartet = AuthenticationStatus.SUCCESS;
		angenommenDerHttpMessageContextGibtCredentialZurueck();
		angenommenDerHttpMessageContextBenachrichtigtUeberLogin(erwartet);

		final var ergebnis = sut.validateRequest(request, response, httpMessageContext);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("ein Anfrage aufräumen (bei einem Logout)")
	void test03()
	{}
}
