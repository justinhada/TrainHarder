package de.justinharder.old.view;

import de.justinharder.old.domain.services.BenutzerService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/benutzer")
@RequiredArgsConstructor
public class BenutzerRessource
{
	@NonNull
	private final BenutzerService benutzerService;

	// TODO: Abstrakte "Ressource" Klasse, die eine findeAlle()-Methode hat.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle()
	{
		return Response
			.ok(benutzerService.findeAlle())
			.build();
	}
}
