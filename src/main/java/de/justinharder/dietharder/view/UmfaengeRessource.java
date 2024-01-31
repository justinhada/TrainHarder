package de.justinharder.dietharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.dietharder.domain.model.exceptions.UmfaengeException;
import de.justinharder.dietharder.domain.services.UmfaengeService;
import de.justinharder.dietharder.domain.services.dto.umfaenge.AktualisierteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.NeueUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.pagination.UmfaengePaginationRequest;
import de.justinharder.dietharder.domain.services.dto.umfaenge.pagination.UmfaengePaginationResponse;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
@Path("/umfaenge")
public class UmfaengeRessource implements
	Ressource<GespeicherteUmfaenge, NeueUmfaenge, AktualisierteUmfaenge, UmfaengePaginationRequest, UmfaengePaginationResponse>
{
	@NonNull
	private final UmfaengeService umfaengeService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull UmfaengePaginationRequest umfaengePaginationRequest)
	{
		return Response
			.ok(umfaengeService.findeAlle(umfaengePaginationRequest))
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
				.ok(umfaengeService.finde(id))
				.build();
		}
		catch (UmfaengeException e)
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
	public Response erstelle(@BeanParam @NonNull NeueUmfaenge neueUmfaenge)
	{
		try
		{
			return Response
				.ok(umfaengeService.erstelle(neueUmfaenge))
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
	public Response aktualisiere(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull AktualisierteUmfaenge aktualisierteUmfaenge)
	{
		try
		{
			return Response
				.ok(umfaengeService.aktualisiere(id, aktualisierteUmfaenge))
				.build();
		}
		catch (UmfaengeException e)
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
			umfaengeService.loesche(id);

			return Response
				.ok()
				.build();
		}
		catch (UmfaengeException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
