package de.justinharder.trainharder.model.services.authentifizierung;

import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Setter
@ApplicationScoped
public class TrainHarderIdentityStore implements IdentityStore
{
	@Inject
	private LoginService loginService;

	@Override
	public CredentialValidationResult validate(final Credential credential)
	{
		try
		{
			if (credential instanceof UsernamePasswordCredential)
			{
				final var benutzername = ((UsernamePasswordCredential) credential).getCaller();
				final var passwort = ((UsernamePasswordCredential) credential).getPasswordAsString();
				return validate(loginService.login(benutzername, passwort));
			}
		}
		catch (final LoginException | InvalidKeySpecException | NoSuchAlgorithmException e)
		{
			return CredentialValidationResult.INVALID_RESULT;
		}

		return CredentialValidationResult.NOT_VALIDATED_RESULT;
	}

	private CredentialValidationResult validate(final AuthentifizierungDto authentifizierungDto)
	{
		return new CredentialValidationResult(authentifizierungDto.getBenutzername());
	}
}
