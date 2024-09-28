package de.justinharder.dietharder.api;

import de.justinharder.base.api.Ressource;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.dietharder.domain.model.exceptions.HautfaltendickeException;
import de.justinharder.dietharder.domain.services.HautfaltendickeService;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.AktualisierteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GeloeschteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.NeueHautfaltendicke;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
@Path("/hautfaltendicken")
public class HautfaltendickenRessource implements Ressource<
	GespeicherteHautfaltendicke,
	NeueHautfaltendicke,
	AktualisierteHautfaltendicke,
	GeloeschteHautfaltendicke>
{
	@NonNull
	private final HautfaltendickeService hautfaltendickeService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull PaginationRequest<GespeicherteHautfaltendicke> paginationRequest)
	{
		return Response
			.ok(hautfaltendickeService.findeAlle(paginationRequest))
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
				.ok(hautfaltendickeService.finde(id))
				.build();
		}
		catch (HautfaltendickeException e)
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
	public Response erstelle(@BeanParam @NonNull NeueHautfaltendicke neueHautfaltendicke)
	{
		try
		{
			return Response
				.ok(hautfaltendickeService.erstelle(neueHautfaltendicke))
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
	public Response aktualisiere(@BeanParam @NonNull AktualisierteHautfaltendicke aktualisierteHautfaltendicke)
	{
		try
		{
			return Response
				.ok(hautfaltendickeService.aktualisiere(aktualisierteHautfaltendicke))
				.build();
		}
		catch (HautfaltendickeException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@DELETE
	@Override
	@Path("/{id}")
	public Response loesche(@BeanParam @NonNull GeloeschteHautfaltendicke geloeschteHautfaltendicke)
	{
		try
		{
			return Response
				.ok(hautfaltendickeService.loesche(geloeschteHautfaltendicke))
				.build();
		}
		catch (HautfaltendickeException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
