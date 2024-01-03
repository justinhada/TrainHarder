package de.justinharder.trainharder.view;

import de.justinharder.trainharder.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.trainharder.domain.services.AuthentifizierungService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
@Path("/authentifizierungen")
public class AuthentifizierungRessource
{
	@NonNull
	private final AuthentifizierungService authentifizierungService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finde(@PathParam("id") String id)
	{
		try
		{
			return Response
				.ok(authentifizierungService.finde(id))
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (AuthentifizierungException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
