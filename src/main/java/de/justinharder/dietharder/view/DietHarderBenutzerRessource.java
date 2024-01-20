package de.justinharder.dietharder.view;

import de.justinharder.dietharder.domain.services.HautfaltendickeService;
import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.trainharder.domain.service.BenutzerService;
import de.justinharder.trainharder.view.BenutzerRessource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;

public class DietHarderBenutzerRessource extends BenutzerRessource
{
	@NonNull
	private final MessungService messungService;

	@NonNull
	private final ZielService zielService;

	@NonNull
	private final HautfaltendickeService hautfaltendickeService;

	public DietHarderBenutzerRessource(
		BenutzerService benutzerService,
		@NonNull MessungService messungService,
		@NonNull ZielService zielService,
		@NonNull HautfaltendickeService hautfaltendickeService)
	{
		super(benutzerService);
		this.messungService = messungService;
		this.zielService = zielService;
		this.hautfaltendickeService = hautfaltendickeService;
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

	@GET
	@Path("/{id}/hautfaltendicken")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleHautfaltendicken(@PathParam("id") @NonNull String id)
	{
		return Response
			.ok(hautfaltendickeService.findeAlle(id))
			.header("Access-Control-Allow-Origin", "*")
			.build();
	}
}
