package de.justinharder.trainharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.service.BenutzerService;
import de.justinharder.trainharder.domain.service.dto.benutzer.AktualisierterBenutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.GespeicherterBenutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.NeuerBenutzer;
import de.justinharder.trainharder.domain.service.dto.benutzer.pagination.BenutzerPaginationRequest;
import de.justinharder.trainharder.domain.service.dto.benutzer.pagination.BenutzerPaginationResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/benutzer")
@RequiredArgsConstructor
public class BenutzerRessource implements
	Ressource<GespeicherterBenutzer, NeuerBenutzer, AktualisierterBenutzer, BenutzerPaginationRequest, BenutzerPaginationResponse>
{
	@NonNull
	protected final BenutzerService benutzerService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull BenutzerPaginationRequest benutzerPaginationRequest)
	{
		return Response
			.ok(benutzerService.findeAlle(benutzerPaginationRequest))
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
				.ok(benutzerService.finde(id))
				.build();
		}
		catch (BenutzerException e)
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
	public Response erstelle(@NonNull NeuerBenutzer neuerBenutzer)
	{
		return Response
			.ok(benutzerService.erstelle(neuerBenutzer))
			.build();
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
		try
		{
			return Response
				.ok(benutzerService.aktualisiere(id, aktualisierterBenutzer))
				.build();
		}
		catch (BenutzerException e)
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
			benutzerService.loesche(id);

			return Response
				.ok()
				.build();
		}
		catch (BenutzerException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeMitLogin(@QueryParam("loginId") @NonNull String loginId)
	{
		try
		{
			return Response
				.ok(benutzerService.findeMitLogin(loginId))
				.build();
		}
		catch (BenutzerException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
