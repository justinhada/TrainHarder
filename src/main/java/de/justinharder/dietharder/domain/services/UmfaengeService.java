package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.dietharder.domain.model.Umfaenge;
import de.justinharder.dietharder.domain.model.attribute.umfaenge.*;
import de.justinharder.dietharder.domain.model.exceptions.UmfaengeException;
import de.justinharder.dietharder.domain.repository.UmfaengeRepository;
import de.justinharder.dietharder.domain.services.dto.umfaenge.AktualisierteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GeloeschteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.NeueUmfaenge;
import de.justinharder.dietharder.domain.services.mapping.UmfaengeMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Dependent
@RequiredArgsConstructor
public class UmfaengeService implements Service<
	GespeicherteUmfaenge,
	NeueUmfaenge,
	AktualisierteUmfaenge,
	GeloeschteUmfaenge>
{
	private static final String ENDPUNKT = "umfaenge";
	private static final String BENUTZER_ENDPUNKT = "umfaenge/%s";

	@NonNull
	private final UmfaengeRepository umfaengeRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final UmfaengeMapping umfaengeMapping;

	@Override
	public PaginationResponse<GespeicherteUmfaenge> findeAlle(
		@NonNull PaginationRequest<GespeicherteUmfaenge> paginationRequest)
	{
		return umfaengeMapping.mappe(
			paginationRequest,
			umfaengeRepository.findeAlle(paginationRequest),
			umfaengeRepository.zaehleAlle(),
			ENDPUNKT);
	}

	@Override
	public GespeicherteUmfaenge finde(@NonNull String id) throws UmfaengeException
	{
		return umfaengeRepository.finde(new ID(id))
			.map(umfaengeMapping::mappe)
			.orElseThrow(() -> new UmfaengeException("Die Umfaenge mit der ID %s existieren nicht!".formatted(id)));
	}

	@Override
	public NeueUmfaenge erstelle(@NonNull NeueUmfaenge neueUmfaenge) throws BenutzerException
	{
		var umfaenge = new Umfaenge(
			new ID(),
			new Datum(neueUmfaenge.getDatum()),
			new HalsUmfang(neueUmfaenge.getHalsUmfang()),
			new SchulterUmfang(neueUmfaenge.getSchulterUmfang()),
			new BrustRueckenUmfang(neueUmfaenge.getBrustRueckenUmfang()),
			new LinkerOberarmUmfang(neueUmfaenge.getLinkerOberarmUmfang()),
			new RechterOberarmUmfang(neueUmfaenge.getRechterOberarmUmfang()),
			new LinkerUnterarmUmfang(neueUmfaenge.getLinkerUnterarmUmfang()),
			new RechterUnterarmUmfang(neueUmfaenge.getRechterUnterarmUmfang()),
			new BauchUmfang(neueUmfaenge.getBauchUmfang()),
			new HueftUmfang(neueUmfaenge.getHueftUmfang()),
			new LinkerOberschenkelUmfang(neueUmfaenge.getLinkerOberschenkelUmfang()),
			new RechterOberschenkelUmfang(neueUmfaenge.getLinkerOberschenkelUmfang()),
			new LinkerUnterschenkelUmfang(neueUmfaenge.getLinkerUnterschenkelUmfang()),
			new RechterUnterschenkelUmfang(neueUmfaenge.getRechterUnterschenkelUmfang()),
			benutzerRepository.finde(new ID(neueUmfaenge.getBenutzerId()))
				.orElseThrow(() -> new BenutzerException(
					"Der Benutzer mit der ID %s existiert nicht!".formatted(neueUmfaenge.getBenutzerId()))));

		umfaengeRepository.speichere(umfaenge);

		return neueUmfaenge;
	}

	@Override
	public AktualisierteUmfaenge aktualisiere(@NonNull AktualisierteUmfaenge aktualisierteUmfaenge)
		throws UmfaengeException
	{
		var umfaenge = umfaengeRepository.finde(new ID(aktualisierteUmfaenge.getId()))
			.orElseThrow(() -> new UmfaengeException(
				"Die Umfaenge mit der ID %s existieren nicht!".formatted(aktualisierteUmfaenge.getId())))
			.setHalsUmfang(new HalsUmfang(aktualisierteUmfaenge.getHalsUmfang()))
			.setSchulterUmfang(new SchulterUmfang(aktualisierteUmfaenge.getSchulterUmfang()))
			.setBrustRueckenUmfang(new BrustRueckenUmfang(aktualisierteUmfaenge.getBrustRueckenUmfang()))
			.setLinkerOberarmUmfang(new LinkerOberarmUmfang(aktualisierteUmfaenge.getLinkerOberarmUmfang()))
			.setRechterOberarmUmfang(new RechterOberarmUmfang(aktualisierteUmfaenge.getRechterOberarmUmfang()))
			.setLinkerUnterarmUmfang(new LinkerUnterarmUmfang(aktualisierteUmfaenge.getLinkerUnterarmUmfang()))
			.setRechterUnterarmUmfang(new RechterUnterarmUmfang(aktualisierteUmfaenge.getRechterUnterarmUmfang()))
			.setBauchUmfang(new BauchUmfang(aktualisierteUmfaenge.getBauchUmfang()))
			.setHueftUmfang(new HueftUmfang(aktualisierteUmfaenge.getHueftUmfang()))
			.setLinkerOberschenkelUmfang(
				new LinkerOberschenkelUmfang(aktualisierteUmfaenge.getLinkerOberschenkelUmfang()))
			.setRechterOberschenkelUmfang(
				new RechterOberschenkelUmfang(aktualisierteUmfaenge.getRechterOberschenkelUmfang()))
			.setLinkerUnterschenkelUmfang(
				new LinkerUnterschenkelUmfang(aktualisierteUmfaenge.getLinkerUnterschenkelUmfang()))
			.setRechterUnterschenkelUmfang(
				new RechterUnterschenkelUmfang(aktualisierteUmfaenge.getRechterUnterschenkelUmfang()));

		umfaengeRepository.speichere(umfaenge);

		return aktualisierteUmfaenge;
	}

	@Override
	public GeloeschteUmfaenge loesche(@NonNull GeloeschteUmfaenge geloeschteUmfaenge) throws UmfaengeException
	{
		umfaengeRepository.loesche(umfaengeRepository.finde(new ID(geloeschteUmfaenge.getId()))
			.orElseThrow(() -> new UmfaengeException(
				"Die Umfaenge mit der ID %s existieren nicht!".formatted(geloeschteUmfaenge.getId()))));

		return geloeschteUmfaenge;
	}

	public PaginationResponse<GespeicherteUmfaenge> findeAlle(
		@NonNull String benutzerId,
		@NonNull PaginationRequest<GespeicherteUmfaenge> paginationRequest)
	{
		return umfaengeMapping.mappe(
			paginationRequest,
			umfaengeRepository.findeAlle(new ID(benutzerId), paginationRequest),
			umfaengeRepository.zaehleAlle(new ID(benutzerId)),
			BENUTZER_ENDPUNKT.formatted(benutzerId));
	}
}
