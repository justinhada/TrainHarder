package de.justinharder.trainharder.model.services.authentifizierung;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import de.justinharder.trainharder.model.domain.dto.AuthentifizierungEintrag;
import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.model.services.AuthentifizierungService;

@ApplicationScoped
public class TrainHarderIdentityStore implements IdentityStore
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
				return validate(authentifizierungService.login(benutzername, passwort));
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
