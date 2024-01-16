package de.justinharder.dietharder.view;

import de.justinharder.base.view.Ressource;
import de.justinharder.dietharder.domain.model.exceptions.MessungException;
import de.justinharder.dietharder.domain.services.MessungService;
import de.justinharder.dietharder.domain.services.dto.messung.AktualisierteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.GespeicherteMessung;
import de.justinharder.dietharder.domain.services.dto.messung.NeueMessung;
import de.justinharder.old.domain.model.exceptions.BenutzerException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequestScoped
@Path("/messungen")
@RequiredArgsConstructor
public class MessungenRessource implements Ressource<GespeicherteMessung, NeueMessung, AktualisierteMessung>
{
	@NonNull
	private final MessungService messungService;

	@GET
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response findeAlle()
	{
		return Response
			.ok(messungService.findeAlle())
			.header("Access-Control-Allow-Origin", "*")
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
				.header("Access-Control-Allow-Origin", "*")
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
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (MessungException | BenutzerException e)
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
		@NonNull AktualisierteMessung aktualisierteMessung)
	{
		try
		{
			return Response
				.ok(messungService.aktualisiere(id, aktualisierteMessung))
				.header("Access-Control-Allow-Origin", "*")
				.build();
		}
		catch (MessungException | BenutzerException e)
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
			messungService.loesche(id);

			return Response
				.ok()
				.header("Access-Control-Allow-Origin", "*")
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