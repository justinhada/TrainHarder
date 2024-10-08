package de.justinharder.trainharder.api;

import de.justinharder.base.api.Ressource;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.LoginException;
import de.justinharder.trainharder.domain.services.LoginService;
import de.justinharder.trainharder.domain.services.dto.login.AktualisierterLogin;
import de.justinharder.trainharder.domain.services.dto.login.GeloeschterLogin;
import de.justinharder.trainharder.domain.services.dto.login.GespeicherterLogin;
import de.justinharder.trainharder.domain.services.dto.login.NeuerLogin;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/logins")
@RequiredArgsConstructor
public class LoginsRessource implements Ressource<
	GespeicherterLogin,
	NeuerLogin,
	AktualisierterLogin,
	GeloeschterLogin>
{
	@NonNull
	private final LoginService loginService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull PaginationRequest<GespeicherterLogin> paginationRequest)
	{
		return Response
			.ok(loginService.findeAlle(paginationRequest))
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
	public Response erstelle(@NonNull NeuerLogin neuerLogin)
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
	public Response aktualisiere(@NonNull AktualisierterLogin aktualisierterLogin)
	{
		try
		{
			return Response
				.ok(loginService.aktualisiere(aktualisierterLogin))
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
	public Response loesche(@BeanParam @NonNull GeloeschterLogin geloeschterLogin)
	{
		try
		{
			return Response
				.ok(loginService.loesche(geloeschterLogin))
				.build();
		}
		catch (LoginException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	//	@GET
	//	@Produces(MediaType.APPLICATION_JSON)
	//	public Response findeMitEMailAdresse(@QueryParam("eMailAdresse") @NonNull String eMailAdresse)
	//	{
	//		try
	//		{
	//			return Response
	//				.ok(loginService.findeMitEMailAdresse(eMailAdresse))
	//				.build();
	//		}
	//		catch (LoginException e)
	//		{
	//			return Response
	//				.status(Response.Status.NOT_FOUND)
	//				.build();
	//		}
	//	}
}
