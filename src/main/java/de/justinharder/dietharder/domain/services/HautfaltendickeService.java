package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.dietharder.domain.model.Hautfaltendicke;
import de.justinharder.dietharder.domain.model.attribute.hautfaltendicke.*;
import de.justinharder.dietharder.domain.model.exceptions.HautfaltendickeException;
import de.justinharder.dietharder.domain.repository.HautfaltendickeRepository;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.AktualisierteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GeloeschteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.NeueHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.pagination.HautfaltendickePaginationRequest;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.pagination.HautfaltendickePaginationResponse;
import de.justinharder.dietharder.domain.services.mapping.HautfaltendickeMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class HautfaltendickeService implements Service<
	GespeicherteHautfaltendicke,
	NeueHautfaltendicke,
	AktualisierteHautfaltendicke,
	GeloeschteHautfaltendicke,
	HautfaltendickePaginationRequest,
	HautfaltendickePaginationResponse>
{
	@NonNull
	private final HautfaltendickeRepository hautfaltendickeRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final HautfaltendickeMapping hautfaltendickeMapping;

	@Override
	public HautfaltendickePaginationResponse findeAlle(
		@NonNull HautfaltendickePaginationRequest hautfaltendickePaginationRequest)
	{
		return hautfaltendickeMapping.mappe(hautfaltendickeRepository.findeAlle(
			hautfaltendickePaginationRequest.getPage(),
			hautfaltendickePaginationRequest.getPageSize()));
	}

	@Override
	public GespeicherteHautfaltendicke finde(@NonNull String id) throws HautfaltendickeException
	{
		return hautfaltendickeRepository.finde(new ID(id))
			.map(hautfaltendickeMapping::mappe)
			.orElseThrow(() -> new HautfaltendickeException(
				"Die Hautfaltendicke mit der ID %s existiert nicht!".formatted(id)));
	}

	@Override
	public GespeicherteHautfaltendicke erstelle(@NonNull NeueHautfaltendicke neueHautfaltendicke)
		throws BenutzerException
	{
		var hautfaltendicke = new Hautfaltendicke(
			new ID(),
			new Datum(neueHautfaltendicke.getDatum()),
			new Brustfalte(neueHautfaltendicke.getBrustfalte()),
			new Bauchfalte(neueHautfaltendicke.getBauchfalte()),
			new Beinfalte(neueHautfaltendicke.getBeinfalte()),
			new Hueftfalte(neueHautfaltendicke.getHueftfalte()),
			new Achselfalte(neueHautfaltendicke.getAchselfalte()),
			new Trizepsfalte(neueHautfaltendicke.getTrizepsfalte()),
			new Rueckenfalte(neueHautfaltendicke.getRueckenfalte()),
			null, // TODO: Berechnung des KFAs mithilfe aller Falten.
			benutzerRepository.finde(new ID(neueHautfaltendicke.getBenutzerId()))
				.orElseThrow(() -> new BenutzerException(
					"Der Benutzer mit der ID %s existiert nicht!".formatted(neueHautfaltendicke.getBenutzerId()))));

		hautfaltendickeRepository.speichere(hautfaltendicke);

		return hautfaltendickeMapping.mappe(hautfaltendicke);
	}

	@Override
	public GespeicherteHautfaltendicke aktualisiere(
		@NonNull String id,
		@NonNull AktualisierteHautfaltendicke aktualisierteHautfaltendicke) throws HautfaltendickeException
	{
		var hautfaltendicke = hautfaltendickeRepository.finde(new ID(id))
			.orElseThrow(() -> new HautfaltendickeException(
				"Die Hautfaltendicke mit der ID %s existiert nicht!".formatted(id)))
			.setDatum(new Datum(aktualisierteHautfaltendicke.getDatum()))
			.setBrustfalte(new Brustfalte(aktualisierteHautfaltendicke.getBrustfalte()))
			.setBauchfalte(new Bauchfalte(aktualisierteHautfaltendicke.getBauchfalte()))
			.setBeinfalte(new Beinfalte(aktualisierteHautfaltendicke.getBeinfalte()))
			.setHueftfalte(new Hueftfalte(aktualisierteHautfaltendicke.getHueftfalte()))
			.setAchselfalte(new Achselfalte(aktualisierteHautfaltendicke.getAchselfalte()))
			.setTrizepsfalte(new Trizepsfalte(aktualisierteHautfaltendicke.getTrizepsfalte()))
			.setRueckenfalte(new Rueckenfalte(aktualisierteHautfaltendicke.getRueckenfalte()))
			.setKoerperfettAnteil(null); // TODO: Berechnung des KFAs mithilfe aller Falten.

		hautfaltendickeRepository.speichere(hautfaltendicke);

		return hautfaltendickeMapping.mappe(hautfaltendicke);
	}

	@Override
	public GeloeschteHautfaltendicke loesche(@NonNull String id) throws HautfaltendickeException
	{
		hautfaltendickeRepository.loesche(hautfaltendickeRepository.finde(new ID(id))
			.orElseThrow(() -> new HautfaltendickeException(
				"Die Hautfaltendicke mit der ID %s existiert nicht!".formatted(id))));

		return new GeloeschteHautfaltendicke(id);
	}

	public HautfaltendickePaginationResponse findeAlle(
		@NonNull String benutzerId,
		@NonNull HautfaltendickePaginationRequest hautfaltendickePaginationRequest)
	{
		return hautfaltendickeMapping.mappe(hautfaltendickeRepository.findeAlle(
			new ID(benutzerId),
			hautfaltendickePaginationRequest.getPage(),
			hautfaltendickePaginationRequest.getPageSize()));
	}
}
