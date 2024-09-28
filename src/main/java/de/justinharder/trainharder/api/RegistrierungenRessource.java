package de.justinharder.trainharder.api;

import de.justinharder.base.api.Ressource;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.RegistrierungException;
import de.justinharder.trainharder.domain.services.LoginService;
import de.justinharder.trainharder.domain.services.RegistrierungService;
import de.justinharder.trainharder.domain.services.dto.registrierung.AktualisierteRegistrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.GeloeschteRegistrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.GespeicherteRegistrierung;
import de.justinharder.trainharder.domain.services.dto.registrierung.NeueRegistrierung;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@RequiredArgsConstructor
@Path("/registrierungen")
public class RegistrierungenRessource implements Ressource<
	GespeicherteRegistrierung,
	NeueRegistrierung,
	AktualisierteRegistrierung,
	GeloeschteRegistrierung>
{
	@NonNull
	private final RegistrierungService registrierungService;

	@NonNull
	private final LoginService loginService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle(@BeanParam @NonNull PaginationRequest<GespeicherteRegistrierung> paginationRequest)
	{
		return Response
			.ok(registrierungService.findeAlle(paginationRequest))
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
	public Response erstelle(@NonNull NeueRegistrierung neueRegistrierung)
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
	public Response aktualisiere(@NonNull AktualisierteRegistrierung aktualisierteRegistrierung)
	{
		try
		{
			return Response
				.ok(registrierungService.aktualisiere(aktualisierteRegistrierung))
				.build();
		}
		catch (RegistrierungException | BenutzerException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@DELETE
	@Override
	@Path("/{id}")
	public Response loesche(@BeanParam @NonNull GeloeschteRegistrierung geloeschteRegistrierung)
	{
		try
		{
			return Response
				.ok(registrierungService.loesche(geloeschteRegistrierung))
				.build();
		}
		catch (RegistrierungException e)
		{
			return Response
				.status(Response.Status.NOT_FOUND)
				.build();
		}
	}

	@GET
	@Path("/check-email/{eMailAdresse}")
	public boolean isEMailAdresseVergeben(@PathParam("eMailAdresse") @NonNull String eMailAdresse)
	{
		return loginService.isEMailAdresseVergeben(eMailAdresse)
			|| registrierungService.isEMailAdresseVergeben(eMailAdresse);
	}

	@GET
	@Path("/check-benutzername/{benutzername}")
	public boolean isBenutzernameVergeben(@PathParam("benutzername") @NonNull String benutzername)
	{
		return loginService.isBenutzernameVergeben(benutzername);
	}
}
