package de.justinharder.old.domain.services;

import de.justinharder.old.domain.model.Benutzer;
import de.justinharder.old.domain.model.attribute.Benutzerangabe;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.attribute.Name;
import de.justinharder.old.domain.model.enums.*;
import de.justinharder.old.domain.model.exceptions.AuthentifizierungException;
import de.justinharder.old.domain.model.exceptions.BenutzerException;
import de.justinharder.old.domain.repository.AuthentifizierungRepository;
import de.justinharder.old.domain.repository.BenutzerRepository;
import de.justinharder.old.domain.services.dto.BenutzerDto;
import de.justinharder.old.domain.services.dto.Benutzerdaten;
import de.justinharder.old.domain.services.mapper.BenutzerDtoMapper;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Dependent
@RequiredArgsConstructor
public class BenutzerService
{
	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final AuthentifizierungRepository authentifizierungRepository;

	@NonNull
	private final BenutzerDtoMapper benutzerDtoMapper;

	public List<BenutzerDto> findeAlle()
	{
		return benutzerDtoMapper.mappeAlle(benutzerRepository.findeAlle());
	}

	public BenutzerDto finde(@NonNull String id) throws BenutzerException
	{
		return benutzerRepository.finde(new ID(id))
			.map(benutzerDtoMapper::mappe)
			.orElseThrow(() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(id)));
	}

	public BenutzerDto findeMitAuthentifizierung(@NonNull String authentifizierungId)
		throws BenutzerException
	{
		return benutzerRepository.findeMitAuthentifizierung(new ID(authentifizierungId))
			.map(benutzerDtoMapper::mappe)
			.orElseThrow(() -> new BenutzerException(
				"Der Benutzer mit der AuthentifizierungID %s existiert nicht!".formatted(authentifizierungId)));
	}

	public BenutzerDto erstelle(@NonNull Benutzerdaten benutzerdaten, @NonNull String authentifizierungId)
		throws AuthentifizierungException
	{
		var benutzer = new Benutzer(
			new ID(),
			new Name(benutzerdaten.getVorname(), benutzerdaten.getNachname()),
			LocalDate.parse(benutzerdaten.getGeburtsdatum(), DateTimeFormatter.ISO_DATE),
			new Benutzerangabe(
				Geschlecht.zuWert(benutzerdaten.getGeschlecht()),
				Erfahrung.zuWert(benutzerdaten.getErfahrung()),
				Ernaehrung.zuWert(benutzerdaten.getErnaehrung()),
				Schlafqualitaet.zuWert(benutzerdaten.getSchlafqualitaet()),
				Stress.zuWert(benutzerdaten.getStress()),
				Doping.zuWert(benutzerdaten.getDoping()),
				Regenerationsfaehigkeit.zuWert(benutzerdaten.getRegenerationsfaehigkeit())),
			authentifizierungRepository.finde(new ID(authentifizierungId))
				.orElseThrow(() -> new AuthentifizierungException(
					"Die Authentifizierung mit der ID %s existiert nicht!".formatted(authentifizierungId))));

		benutzerRepository.speichere(benutzer);

		return benutzerDtoMapper.mappe(benutzer);
	}

	public BenutzerDto aktualisiere(@NonNull String id, @NonNull Benutzerdaten benutzerdaten)
		throws BenutzerException
	{
		var benutzer = benutzerRepository.finde(new ID(id))
			.orElseThrow(() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(id)))
			.setName(new Name(benutzerdaten.getVorname(), benutzerdaten.getNachname()))
			.setGeburtsdatum(LocalDate.parse(benutzerdaten.getGeburtsdatum(), DateTimeFormatter.ISO_DATE))
			.setBenutzerangabe(new Benutzerangabe(
				Geschlecht.zuWert(benutzerdaten.getGeschlecht()),
				Erfahrung.zuWert(benutzerdaten.getErfahrung()),
				Ernaehrung.zuWert(benutzerdaten.getErnaehrung()),
				Schlafqualitaet.zuWert(benutzerdaten.getSchlafqualitaet()),
				Stress.zuWert(benutzerdaten.getStress()),
				Doping.zuWert(benutzerdaten.getDoping()),
				Regenerationsfaehigkeit.zuWert(benutzerdaten.getRegenerationsfaehigkeit())));

		benutzerRepository.speichere(benutzer);

		return benutzerDtoMapper.mappe(benutzer);
	}
}
