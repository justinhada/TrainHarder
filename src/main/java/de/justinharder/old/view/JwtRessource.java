package de.justinharder.old.view;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashSet;
import java.util.List;

@Path("/jwt")
@ApplicationScoped
public class JwtRessource
{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getJwt()
	{
		return Response.ok(
				Jwt.issuer("trainharder")
					.subject("trainharder")
					.groups(new HashSet<>(List.of("admin", "user")))
					.expiresAt(System.currentTimeMillis() + 3600)
					.sign())
			.build();
	}

}
