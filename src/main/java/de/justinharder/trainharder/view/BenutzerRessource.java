package de.justinharder.trainharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.trainharder.domain.service.dto.benutzer.AktualisierterBenutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.GespeicherterBenutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.NeuerBenutzer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/benutzer")
@RequiredArgsConstructor
public class BenutzerRessource implements Ressource<GespeicherterBenutzer, NeuerBenutzer, AktualisierterBenutzer>
{
	@NonNull
	protected final MessungService messungService;

	@NonNull
	protected final ZielService zielService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle()
	{
		return null;
	}

	@GET
	@Override
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finde(@PathParam("id") @NonNull String id)
	{
		return null;
	}

	@POST
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response erstelle(@NonNull NeuerBenutzer neuerBenutzer)
	{
		return null;
	}

	@PUT
	@Override
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response aktualisiere(
		@PathParam("id") @NonNull String id,
		@NonNull AktualisierterBenutzer aktualisierterBenutzer)
	{
		return null;
	}

	@DELETE
	@Override
	@Path("/{id}")
	public Response loesche(@PathParam("id") @NonNull String id)
	{
		return null;
	}
}
