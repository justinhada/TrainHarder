package de.justinharder.dietharder.api;

import de.justinharder.base.api.Ressource;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.dietharder.domain.model.exceptions.MessungException;
import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.dto.messung.AktualisierteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.GeloeschteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.NeueMessung;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/messungen")
@RequiredArgsConstructor
public class MessungenRessource implements Ressource<
	GespeicherteMessung,
	NeueMessung,
	AktualisierteMessung,
	GeloeschteMessung>
{
	@NonNull
	private final MessungService messungService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull PaginationRequest<GespeicherteMessung> paginationRequest)
	{
		return Response
			.ok(messungService.findeAlle(paginationRequest))
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
				.ok(messungService.finde(id))
				.build();
		}
		catch (MessungException e)
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
	public Response erstelle(@NonNull NeueMessung neueMessung)
	{
		try
		{
			return Response
				.ok(messungService.erstelle(neueMessung))
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
	public Response aktualisiere(@BeanParam @NonNull AktualisierteMessung aktualisierteMessung)
	{
		try
		{
			return Response
				.ok(messungService.aktualisiere(aktualisierteMessung))
				.build();
		}
		catch (MessungException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@DELETE
	@Override
	@Path("/{id}")
	public Response loesche(@BeanParam @NonNull GeloeschteMessung geloeschteMessung)
	{
		try
		{
			return Response
				.ok(messungService.loesche(geloeschteMessung))
				.build();
		}
		catch (MessungException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
