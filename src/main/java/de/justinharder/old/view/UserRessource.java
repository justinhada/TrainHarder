package de.justinharder.old.view;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import org.jboss.resteasy.annotations.cache.NoCache;

@Path("/api/users")
public class UserRessource
{
	@Inject
	SecurityIdentity identity;

	@GET
	@NoCache
	@Path("/me")
	@Produces(MediaType.APPLICATION_JSON)
	public User me()
	{
		return new User(identity);
	}

	@Getter
	public static class User
	{
		private final String username;

		User(SecurityIdentity identity)
		{
			this.username = identity.getPrincipal().getName();
		}
	}
}
