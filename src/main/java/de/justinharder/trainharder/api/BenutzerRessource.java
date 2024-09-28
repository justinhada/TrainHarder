package de.justinharder.trainharder.api;

import de.justinharder.base.api.Ressource;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.services.BenutzerService;
import de.justinharder.trainharder.domain.services.dto.benutzer.AktualisierterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GeloeschterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import de.justinharder.trainharder.domain.services.dto.benutzer.NeuerBenutzer;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/benutzer")
@RequiredArgsConstructor
public class BenutzerRessource implements Ressource<
	GespeicherterBenutzer,
	NeuerBenutzer,
	AktualisierterBenutzer,
	GeloeschterBenutzer>
{
	@NonNull
	protected final BenutzerService benutzerService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull PaginationRequest<GespeicherterBenutzer> paginationRequest)
	{
		return Response
			.ok(benutzerService.findeAlle(paginationRequest))
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
	public Response erstelle(@BeanParam @NonNull NeuerBenutzer neuerBenutzer)
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
	public Response aktualisiere(@BeanParam @NonNull AktualisierterBenutzer aktualisierterBenutzer)
	{
		try
		{
			return Response
				.ok(benutzerService.aktualisiere(aktualisierterBenutzer))
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
	public Response loesche(@BeanParam @NonNull GeloeschterBenutzer geloeschterBenutzer)
	{
		try
		{
			return Response
				.ok(benutzerService.loesche(geloeschterBenutzer))
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
	@Path("/login")
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

	@GET
	@RolesAllowed("BENUTZER")
	@Path("/benutzername/{benutzername}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeMitBenutzername(@PathParam("benutzername") @NonNull String benutzername)
	{
		try
		{
			return Response
				.ok(benutzerService.findeMitBenutzername(benutzername))
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
