package de.justinharder.dietharder.api;

import de.justinharder.base.api.Ressource;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.dietharder.domain.model.exceptions.ZielException;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.dietharder.domain.services.dto.ziel.AktualisiertesZiel;
import de.justinharder.dietharder.domain.services.dto.ziel.GeloeschtesZiel;
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
public class ZieleRessource implements Ressource<
	GespeichertesZiel,
	NeuesZiel,
	AktualisiertesZiel,
	GeloeschtesZiel>
{
	@NonNull
	private final ZielService zielService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull PaginationRequest<GespeichertesZiel> paginationRequest)
	{
		return Response
			.ok(zielService.findeAlle(paginationRequest))
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
	public Response aktualisiere(@BeanParam @NonNull AktualisiertesZiel aktualisiertesZiel)
	{
		try
		{
			return Response
				.ok(zielService.aktualisiere(aktualisiertesZiel))
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
	public Response loesche(@BeanParam @NonNull GeloeschtesZiel geloeschtesZiel)
	{
		try
		{
			return Response
				.ok(zielService.loesche(geloeschtesZiel))
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
