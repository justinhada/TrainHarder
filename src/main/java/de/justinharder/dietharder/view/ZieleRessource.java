package de.justinharder.dietharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.dietharder.domain.model.exceptions.ZielException;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.dietharder.domain.services.dto.ziel.AktualisiertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.NeuesZiel;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/ziele")
@RequiredArgsConstructor
public class ZieleRessource implements Ressource<GespeichertesZiel, NeuesZiel, AktualisiertesZiel>
{
	@NonNull
	private final ZielService zielService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle()
	{
		return Response
			.ok(zielService.findeAlle())
			.header("Access-Control-Allow-Origin", "*")
			.build();
	}

	@GET
	@Override
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response finde(@PathParam("id") @NonNull String id)
	{
		try
		{
			return Response
				.ok(zielService.finde(id))
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (ZielException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@POST
	@Override
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response erstelle(@NonNull NeuesZiel neuesZiel)
	{
		try
		{
			return Response
				.ok(zielService.erstelle(neuesZiel))
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (BenutzerException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@PUT
	@Override
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response aktualisiere(@PathParam("id") @NonNull String id, @NonNull AktualisiertesZiel aktualisiertesZiel)
	{
		try
		{
			return Response
				.ok(zielService.aktualisiere(id, aktualisiertesZiel))
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (ZielException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@DELETE
	@Override
	@Path("/{id}")
	public Response loesche(@PathParam("id") @NonNull String id)
	{
		try
		{
			zielService.loesche(id);

			return Response
				.ok()
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (ZielException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
