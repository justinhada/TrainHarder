package de.justinharder.trainharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.service.LoginService;
import de.justinharder.trainharder.domain.service.dto.login.AktualisierterLogin;
import de.justinharder.trainharder.domain.service.dto.login.GeloeschterLogin;
import de.justinharder.trainharder.domain.service.dto.login.GespeicherterLogin;
import de.justinharder.trainharder.domain.service.dto.login.NeuerLogin;
import de.justinharder.trainharder.domain.service.dto.login.pagination.LoginPaginationRequest;
import de.justinharder.trainharder.domain.service.dto.login.pagination.LoginPaginationResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/logins")
@RequiredArgsConstructor
public class LoginRessource implements
	Ressource<GespeicherterLogin, NeuerLogin, AktualisierterLogin, GeloeschterLogin, LoginPaginationRequest, LoginPaginationResponse>
{
	@NonNull
	private final LoginService loginService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull LoginPaginationRequest loginPaginationRequest)
	{
		return Response
			.ok(loginService.findeAlle(loginPaginationRequest))
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
				.ok(loginService.finde(id))
				.build();
		}
		catch (LoginException e)
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
	public Response erstelle(@BeanParam @NonNull NeuerLogin neuerLogin)
	{
		try
		{
			return Response
				.ok(loginService.erstelle(neuerLogin))
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
		@BeanParam @NonNull AktualisierterLogin aktualisierterLogin)
	{
		try
		{
			return Response
				.ok(loginService.aktualisiere(id, aktualisierterLogin))
				.build();
		}
		catch (LoginException e)
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
				.ok(loginService.loesche(id))
				.build();
		}
		catch (LoginException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}
}
