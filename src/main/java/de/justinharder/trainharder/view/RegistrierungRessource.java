package de.justinharder.trainharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.trainharder.domain.model.exceptions.RegistrierungException;
import de.justinharder.trainharder.domain.service.RegistrierungService;
import de.justinharder.trainharder.domain.service.dto.registrierung.AktualisierteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GeloeschteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.GespeicherteRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.NeueRegistrierung;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationRequest;
import de.justinharder.trainharder.domain.service.dto.registrierung.pagination.RegistrierungPaginationResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
@Path("/registrierungen")
public class RegistrierungRessource implements
	Ressource<GespeicherteRegistrierung, NeueRegistrierung, AktualisierteRegistrierung, GeloeschteRegistrierung, RegistrierungPaginationRequest, RegistrierungPaginationResponse>
{
	@NonNull
	private final RegistrierungService registrierungService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull RegistrierungPaginationRequest registrierungPaginationRequest)
	{
		return Response
			.ok(registrierungService.findeAlle(registrierungPaginationRequest))
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
				.ok(registrierungService.finde(id))
				.build();
		}
		catch (RegistrierungException e)
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
	public Response erstelle(@BeanParam @NonNull NeueRegistrierung neueRegistrierung)
	{
		return Response
			.ok(registrierungService.erstelle(neueRegistrierung))
			.build();
	}

	@PUT
	@Override
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response aktualisiere(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull AktualisierteRegistrierung aktualisierteRegistrierung)
	{
		try
		{
			return Response
				.ok(registrierungService.aktualisiere(id, aktualisierteRegistrierung))
				.build();
		}
		catch (RegistrierungException e)
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
			return Response
				.ok(registrierungService.loesche(id))
				.build();
		}
		catch (RegistrierungException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
