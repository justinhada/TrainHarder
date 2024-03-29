package de.justinharder.dietharder.domain.services;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.Service;
import de.justinharder.dietharder.domain.model.Umfaenge;
import de.justinharder.dietharder.domain.model.attribute.umfaenge.*;
import de.justinharder.dietharder.domain.model.exceptions.UmfaengeException;
import de.justinharder.dietharder.domain.repository.UmfaengeRepository;
import de.justinharder.dietharder.domain.services.dto.umfaenge.AktualisierteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GeloeschteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.GespeicherteUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.NeueUmfaenge;
import de.justinharder.dietharder.domain.services.dto.umfaenge.pagination.UmfaengePaginationRequest;
import de.justinharder.dietharder.domain.services.dto.umfaenge.pagination.UmfaengePaginationResponse;
import de.justinharder.dietharder.domain.services.mapping.UmfaengeMapping;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Dependent
@RequiredArgsConstructor
public class UmfaengeService implements
	Service<GespeicherteUmfaenge, NeueUmfaenge, AktualisierteUmfaenge, GeloeschteUmfaenge, UmfaengePaginationRequest, UmfaengePaginationResponse>
{
	@NonNull
	private final UmfaengeRepository umfaengeRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final UmfaengeMapping umfaengeMapping;

	@Override
	public List<GespeicherteUmfaenge> findeAlle()
	{
		return umfaengeRepository.findeAlle().stream()
			.map(umfaengeMapping::mappe)
			.toList();
	}

	@Override
	public UmfaengePaginationResponse findeAlle(@NonNull UmfaengePaginationRequest umfaengePaginationRequest)
	{
		return umfaengeMapping.mappe(umfaengeRepository.findeAlle(
			umfaengePaginationRequest.getPage(),
			umfaengePaginationRequest.getPageSize()));
	}

	@Override
	public GespeicherteUmfaenge finde(@NonNull String id) throws UmfaengeException
	{
		return umfaengeRepository.finde(new ID(id))
			.map(umfaengeMapping::mappe)
			.orElseThrow(() -> new UmfaengeException("Die Umfaenge mit der ID %s existieren nicht!".formatted(id)));
	}

	@Override
	public GespeicherteUmfaenge erstelle(@NonNull NeueUmfaenge neueUmfaenge) throws BenutzerException
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

		return umfaengeMapping.mappe(umfaenge);
	}

	@Override
	public GespeicherteUmfaenge aktualisiere(@NonNull String id, @NonNull AktualisierteUmfaenge aktualisierteUmfaenge)
		throws UmfaengeException
	{
		var umfaenge = umfaengeRepository.finde(new ID(id))
			.orElseThrow(() -> new UmfaengeException("Die Umfaenge mit der ID %s existieren nicht!".formatted(id)))
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

		return umfaengeMapping.mappe(umfaenge);
	}

	@Override
	public GeloeschteUmfaenge loesche(@NonNull String id) throws UmfaengeException
	{
		umfaengeRepository.loesche(umfaengeRepository.finde(new ID(id))
			.orElseThrow(() -> new UmfaengeException("Die Umfaenge mit der ID %s existieren nicht!".formatted(id))));

		return new GeloeschteUmfaenge(id);
	}

	public UmfaengePaginationResponse findeAlle(
		@NonNull String benutzerId,
		@NonNull UmfaengePaginationRequest umfaengePaginationRequest)
	{
		return umfaengeMapping.mappe(umfaengeRepository.findeAlle(
			new ID(benutzerId),
			umfaengePaginationRequest.getPage(),
			umfaengePaginationRequest.getPageSize()));
	}
}
