package de.justinharder.trainharder.view;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Authenticated
@Path("/api/admin")
public class AdminRessource
{
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String admin()
	{
		return "granted";
	}
}
