package de.justinharder.trainharder.api;

import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.service.LoginService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RequestScoped
@Path("/login")
@RequiredArgsConstructor
public class LoginEndpunkt
{
	@NonNull
	private final LoginService loginService;

	@POST
	@APIResponse(responseCode = "200", description = "")
	@APIResponse(responseCode = "401", description = "")
	public Response login(@NonNull LoginDaten loginDaten)
	{
		try
		{
			return Response
				.ok(loginService.login(loginDaten))
				.build();
		}
		catch (LoginException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e)
		{
			return Response
				.status(Response.Status.UNAUTHORIZED.getStatusCode(), e.getMessage())
				.build();
		}
	}
}
