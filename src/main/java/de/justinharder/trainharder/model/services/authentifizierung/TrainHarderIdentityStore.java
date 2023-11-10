package de.justinharder.trainharder.model.services.authentifizierung;

import de.justinharder.trainharder.model.domain.exceptions.LoginException;
import de.justinharder.trainharder.view.dto.AuthentifizierungDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import lombok.NonNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class TrainHarderIdentityStore implements IdentityStore
{
	@Inject
	private LoginService loginService;

	public void setLoginService(@NonNull LoginService loginService)
	{
		this.loginService = loginService;
	}

	@Override
	public CredentialValidationResult validate(@NonNull Credential credential)
	{
		try
		{
			if (credential instanceof UsernamePasswordCredential)
			{
				var benutzername = ((UsernamePasswordCredential) credential).getCaller();
				var passwort = ((UsernamePasswordCredential) credential).getPasswordAsString();
				return validate(loginService.login(benutzername, passwort));
			}
		}
		catch (LoginException | InvalidKeySpecException | NoSuchAlgorithmException e)
		{
			return CredentialValidationResult.INVALID_RESULT;
		}

		return CredentialValidationResult.NOT_VALIDATED_RESULT;
	}

	private CredentialValidationResult validate(AuthentifizierungDto authentifizierungDto)
	{
		return new CredentialValidationResult(authentifizierungDto.getBenutzername());
	}
}
