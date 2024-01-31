package de.justinharder.dietharder.view;

import de.justinharder.dietharder.domain.services.HautfaltendickeService;
import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.UmfaengeService;
import de.justinharder.dietharder.domain.services.ZielService;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.pagination.HautfaltendickePaginationRequest;
import de.justinharder.dietharder.domain.services.dto.messung.pagination.MessungPaginationRequest;
import de.justinharder.dietharder.domain.services.dto.umfaenge.pagination.UmfaengePaginationRequest;
import de.justinharder.dietharder.domain.services.dto.ziel.pagination.ZielPaginationRequest;
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
		@BeanParam @NonNull MessungPaginationRequest messungPaginationRequest)
	{
		return Response
			.ok(messungService.findeAlle(id, messungPaginationRequest))
			.build();
	}

	@GET
	@Path("/{id}/ziele")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleZiele(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull ZielPaginationRequest zielPaginationRequest)
	{
		return Response
			.ok(zielService.findeAlle(id, zielPaginationRequest))
			.build();
	}

	@GET
	@Path("/{id}/hautfaltendicken")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleHautfaltendicken(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull HautfaltendickePaginationRequest hautfaltendickePaginationRequest)
	{
		return Response
			.ok(hautfaltendickeService.findeAlle(id, hautfaltendickePaginationRequest))
			.build();
	}

	@GET
	@Path("/{id}/umfaenge")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlleUmfaenge(
		@PathParam("id") @NonNull String id,
		@BeanParam @NonNull UmfaengePaginationRequest umfaengePaginationRequest)
	{
		return Response
			.ok(umfaengeService.findeAlle(id, umfaengePaginationRequest))
			.build();
	}
}
