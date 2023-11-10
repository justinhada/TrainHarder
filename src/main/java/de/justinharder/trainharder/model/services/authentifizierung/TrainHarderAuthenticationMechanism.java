package de.justinharder.trainharder.model.services.authentifizierung;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

@AutoApplySession
@LoginToContinue(loginPage = "/trainharder/login", useForwardToLogin = false)
@ApplicationScoped
public class TrainHarderAuthenticationMechanism implements HttpAuthenticationMechanism
{
	@Inject
	private IdentityStore identityStore;

	public void setIdentityStore(@NonNull IdentityStore identityStore)
	{
		this.identityStore = identityStore;
	}

	@Override
	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext)
	{
		var credential = httpMessageContext.getAuthParameters().getCredential();

		if (credential != null)
		{
			return httpMessageContext.notifyContainerAboutLogin(identityStore.validate(credential));
		}
		return httpMessageContext.doNothing();
	}

	@Override
	public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext)
	{
		HttpAuthenticationMechanism.super.cleanSubject(request, response, httpMessageContext);
	}
}
