package de.justinharder.trainharder.model.services.authentifizierung;

import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Setter
@AutoApplySession
@LoginToContinue(
	loginPage = "/trainharder/login",
	useForwardToLogin = false)
@ApplicationScoped
public class TrainHarderAuthenticationMechanism implements HttpAuthenticationMechanism
{
	@Inject
	private IdentityStore identityStore;

	@Override
	public AuthenticationStatus validateRequest(
		HttpServletRequest request,
		HttpServletResponse response,
		HttpMessageContext httpMessageContext)
	{
		var credential = httpMessageContext.getAuthParameters().getCredential();

		if (credential != null)
		{
			return httpMessageContext.notifyContainerAboutLogin(identityStore.validate(credential));
		}
		return httpMessageContext.doNothing();
	}

	@Override
	public void cleanSubject(
		HttpServletRequest request,
		HttpServletResponse response,
		HttpMessageContext httpMessageContext)
	{
		HttpAuthenticationMechanism.super.cleanSubject(request, response, httpMessageContext);
	}
}
