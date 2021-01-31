package de.justinharder.trainharder.model.services.authentifizierung;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		identityStore = mock(IdentityStore.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		httpMessageContext = mock(HttpMessageContext.class);
		credential = mock(Credential.class);
		credentialValidationResult = mock(CredentialValidationResult.class);
		authenticationParameters = mock(AuthenticationParameters.class);

		sut = new TrainHarderAuthenticationMechanism();

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

	private void angenommenDerHttpMessageContextBenachrichtigtUeberLogin(AuthenticationStatus authenticationStatus)
	{
		when(identityStore.validate(any(Credential.class))).thenReturn(credentialValidationResult);
		when(httpMessageContext.notifyContainerAboutLogin(credentialValidationResult)).thenReturn(authenticationStatus);
	}

	@Test
	@DisplayName("eine Anfrage validieren, wenn die Credentials null sind")
	void test01()
	{
		var erwartet = AuthenticationStatus.NOT_DONE;
		angenommenDerHttpMessageContextGibtNullZurueck();
		angenommenDerHttpMessageContextGibtNichtsZurueck();

		var ergebnis = sut.validateRequest(request, response, httpMessageContext);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("eine Anfrage validieren, wenn die Credentials gültig sind")
	void test02()
	{
		var erwartet = AuthenticationStatus.SUCCESS;
		angenommenDerHttpMessageContextGibtCredentialZurueck();
		angenommenDerHttpMessageContextBenachrichtigtUeberLogin(erwartet);

		var ergebnis = sut.validateRequest(request, response, httpMessageContext);

		assertThat(ergebnis).isEqualTo(erwartet);
	}

	@Test
	@DisplayName("ein Anfrage aufräumen (bei einem Logout)")
	void test03()
	{
		sut.cleanSubject(request, response, httpMessageContext);

		assertThat(request.getUserPrincipal()).isNull();
	}

	@Test
	@DisplayName("null validieren")
	void test04()
	{
		assertThrows(NullPointerException.class, () -> sut.setIdentityStore(null));
	}
}