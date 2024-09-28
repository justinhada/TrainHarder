package de.justinharder.dietharder.api;

import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.dietharder.domain.services.HautfaltendickeService;
import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.UmfaengeService;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.ziel.GespeichertesZiel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/benutzer")
@RequiredArgsConstructor
public class DietHarderBenutzerRessource
{
	@NonNull
	private final MessungService messungService;

	@NonNull
	private final ZielService zielService;

	@NonNull
	private final HautfaltendickeService hautfaltendickeService;

	@NonNull
	private final UmfaengeService umfaengeService;

	@GET
	@Path("/{id}/messungen")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleMessungen(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull PaginationRequest<GespeicherteMessung> paginationRequest)
	{
		return Response
			.ok(messungService.findeAlle(id, paginationRequest))
			.build();
	}

	@GET
	@Path("/{id}/ziele")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleZiele(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull PaginationRequest<GespeichertesZiel> paginationRequest)
	{
		return Response
			.ok(zielService.findeAlle(id, paginationRequest))
			.build();
	}

	@GET
	@Path("/{id}/hautfaltendicken")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleHautfaltendicken(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull PaginationRequest<GespeicherteHautfaltendicke> paginationRequest)
	{
		return Response
			.ok(hautfaltendickeService.findeAlle(id, paginationRequest))
			.build();
	}

	@GET
	@Path("/{id}/umfaenge")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleUmfaenge(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull PaginationRequest<GespeicherteUmfaenge> paginationRequest)
	{
		return Response
			.ok(umfaengeService.findeAlle(id, paginationRequest))
			.build();
	}
}
