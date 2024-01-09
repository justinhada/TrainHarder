package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.Koerpermasse;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.KoerpermessungException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.KoerpermessungRepository;
import de.justinharder.trainharder.domain.services.dto.Koerpermessdaten;
import de.justinharder.trainharder.domain.services.dto.KoerpermessungDto;
import de.justinharder.trainharder.domain.services.mapper.KoerpermessungDtoMapper;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Dependent
@RequiredArgsConstructor
public class KoerpermessungService
{
	@NonNull
	private final KoerpermessungRepository koerpermessungRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final KoerpermessungDtoMapper koerpermessungDtoMapper;

	public List<KoerpermessungDto> findeAlleMitBenutzer(@NonNull String benutzerId)
	{
		return koerpermessungDtoMapper.mappeAlle(koerpermessungRepository.findeAlleMitBenutzer(new ID(benutzerId)));
	}

	public KoerpermessungDto finde(@NonNull String id) throws KoerpermessungException
	{
		return koerpermessungRepository.finde(new ID(id))
			.map(koerpermessungDtoMapper::mappe)
			.orElseThrow(
				() -> new KoerpermessungException("Die Koerpermessung mit der ID %s existiert nicht!".formatted(id)));
	}

	public KoerpermessungDto erstelle(@NonNull Koerpermessdaten koerpermessdaten, @NonNull String benutzerId)
		throws BenutzerException
	{
		var benutzer = benutzerRepository.finde(new ID(benutzerId))
			.orElseThrow(
				() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(benutzerId)));

		var koepermessung = new Koerpermessung(
			new ID(),
			LocalDate.parse(koerpermessdaten.getDatum(), DateTimeFormatter.ISO_DATE),
			new Koerpermasse(
				new BigDecimal(koerpermessdaten.getKoerpergroesse()),
				BigDecimal.valueOf(koerpermessdaten.getKoerpergewicht()),
				BigDecimal.valueOf(koerpermessdaten.getKoerperfettAnteil())),
			koerpermessdaten.getKalorieneinnahme(),
			koerpermessdaten.getKalorienverbrauch());

		koerpermessungRepository.speichere(koepermessung);
		benutzer.fuegeKoerpermessungHinzu(koepermessung);
		benutzerRepository.speichere(benutzer);

		return koerpermessungDtoMapper.mappe(koepermessung);
	}

	public KoerpermessungDto aktualisiere(@NonNull String id, @NonNull Koerpermessdaten koerpermessdaten)
		throws KoerpermessungException
	{
		var koerpermessung = koerpermessungRepository.finde(new ID(id))
			.orElseThrow(
				() -> new KoerpermessungException("Die Koerpermessung mit der ID %s existiert nicht!".formatted(id)))
			.setDatum(LocalDate.parse(koerpermessdaten.getDatum(), DateTimeFormatter.ISO_DATE))
			.setKoerpermasse(new Koerpermasse(
				new BigDecimal(koerpermessdaten.getKoerpergroesse()),
				BigDecimal.valueOf(koerpermessdaten.getKoerpergewicht()),
				BigDecimal.valueOf(koerpermessdaten.getKoerperfettAnteil())))
			.setKalorieneinnahme(koerpermessdaten.getKalorieneinnahme())
			.setKalorienverbrauch(koerpermessdaten.getKalorienverbrauch());

		koerpermessungRepository.speichere(koerpermessung);

		return koerpermessungDtoMapper.mappe(koerpermessung);
	}
}
