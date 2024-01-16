package de.justinharder.dietharder.view;

import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.trainharder.view.BenutzerRessource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;

import javax.inject.Inject;

public class DietHarderBenutzerRessource extends BenutzerRessource
{
	@Inject
	public DietHarderBenutzerRessource(@NonNull MessungService messungService, @NonNull ZielService zielService)
	{
		super(messungService, zielService);
	}

	@GET
	@Path("/{id}/messungen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleMessungen(@PathParam("id") @NonNull String id)
	{
		return Response
			.ok(messungService.findeAlle(id))
			.header("Access-Control-Allow-Origin", "*")
			.build();
	}

	@GET
	@Path("/{id}/ziele")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleZiele(@PathParam("id") @NonNull String id)
	{
		return Response
			.ok(zielService.findeAlle(id))
			.header("Access-Control-Allow-Origin", "*")
			.build();
	}
}