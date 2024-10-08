package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.model.Hautfaltendicke;
import de.justinharder.dietharder.domain.model.attribute.hautfaltendicke.*;
import de.justinharder.dietharder.domain.model.exceptions.HautfaltendickeException;
import de.justinharder.dietharder.domain.repository.HautfaltendickeRepository;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.AktualisierteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GeloeschteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.GespeicherteHautfaltendicke;
import de.justinharder.dietharder.domain.services.dto.hautfaltendicke.NeueHautfaltendicke;
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
	GeloeschteHautfaltendicke>
{
	private static final String ENDPUNKT = "hautfaltendicken";
	private static final String BENUTZER_ENDPUNKT = "hautfaltendicken/%s";

	@NonNull
	private final HautfaltendickeRepository hautfaltendickeRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final HautfaltendickeMapping hautfaltendickeMapping;

	@Override
	public PaginationResponse<GespeicherteHautfaltendicke> findeAlle(
		@NonNull PaginationRequest<GespeicherteHautfaltendicke> paginationRequest)
	{
		return hautfaltendickeMapping.mappe(
			paginationRequest,
			hautfaltendickeRepository.findeAlle(paginationRequest),
			hautfaltendickeRepository.zaehleAlle(),
			ENDPUNKT);
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
	public NeueHautfaltendicke erstelle(@NonNull NeueHautfaltendicke neueHautfaltendicke)
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

		return neueHautfaltendicke;
	}

	@Override
	public AktualisierteHautfaltendicke aktualisiere(@NonNull AktualisierteHautfaltendicke aktualisierteHautfaltendicke)
		throws HautfaltendickeException
	{
		var hautfaltendicke = hautfaltendickeRepository.finde(new ID(aktualisierteHautfaltendicke.getId()))
			.orElseThrow(() -> new HautfaltendickeException(
				"Die Hautfaltendicke mit der ID %s existiert nicht!".formatted(aktualisierteHautfaltendicke.getId())))
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

		return aktualisierteHautfaltendicke;
	}

	@Override
	public GeloeschteHautfaltendicke loesche(@NonNull GeloeschteHautfaltendicke geloeschteHautfaltendicke)
		throws HautfaltendickeException
	{
		hautfaltendickeRepository.loesche(hautfaltendickeRepository.finde(new ID(geloeschteHautfaltendicke.getId()))
			.orElseThrow(() -> new HautfaltendickeException(
				"Die Hautfaltendicke mit der ID %s existiert nicht!".formatted(geloeschteHautfaltendicke.getId()))));

		return geloeschteHautfaltendicke;
	}

	public PaginationResponse<GespeicherteHautfaltendicke> findeAlle(
		@NonNull String benutzerId,
		@NonNull PaginationRequest<GespeicherteHautfaltendicke> paginationRequest)
	{
		return hautfaltendickeMapping.mappe(
			paginationRequest,
			hautfaltendickeRepository.findeAlle(new ID(benutzerId), paginationRequest),
			hautfaltendickeRepository.zaehleAlle(new ID(benutzerId)),
			BENUTZER_ENDPUNKT.formatted(benutzerId));
	}
}
