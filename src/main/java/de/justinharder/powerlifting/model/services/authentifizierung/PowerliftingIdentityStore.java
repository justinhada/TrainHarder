package de.justinharder.powerlifting.model.services.authentifizierung;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import de.justinharder.powerlifting.model.domain.dto.AuthentifizierungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.LoginException;
import de.justinharder.powerlifting.model.services.AuthentifizierungService;

@ApplicationScoped
public class PowerliftingIdentityStore implements IdentityStore
{
	@Inject
	private AuthentifizierungService authentifizierungService;

	@Override
	public CredentialValidationResult validate(final Credential credential)
	{
		try
		{
			if (credential instanceof UsernamePasswordCredential)
			{
				final var benutzername = ((UsernamePasswordCredential) credential).getCaller();
				final var passwort = ((UsernamePasswordCredential) credential).getPasswordAsString();
				return validate(authentifizierungService.checkLogin(benutzername, passwort));
			}
		}
		catch (final LoginException e)
		{
			return CredentialValidationResult.INVALID_RESULT;
		}

		return CredentialValidationResult.NOT_VALIDATED_RESULT;
	}

	private CredentialValidationResult validate(final AuthentifizierungEintrag authentifizierungEintrag)
	{
		return new CredentialValidationResult(authentifizierungEintrag.getBenutzername());
	}
}
