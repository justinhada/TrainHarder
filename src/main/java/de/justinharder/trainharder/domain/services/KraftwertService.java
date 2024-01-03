package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.enums.Wiederholungen;
import de.justinharder.trainharder.domain.model.exceptions.BenutzerException;
import de.justinharder.trainharder.domain.model.exceptions.KraftwertException;
import de.justinharder.trainharder.domain.model.exceptions.UebungException;
import de.justinharder.trainharder.domain.repository.BenutzerRepository;
import de.justinharder.trainharder.domain.repository.KraftwertRepository;
import de.justinharder.trainharder.domain.repository.UebungRepository;
import de.justinharder.trainharder.domain.services.dto.KraftwertDto;
import de.justinharder.trainharder.domain.services.mapper.KraftwertDtoMapper;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Dependent
@RequiredArgsConstructor
public class KraftwertService
{
	@NonNull
	private final KraftwertRepository kraftwertRepository;

	@NonNull
	private final BenutzerRepository benutzerRepository;

	@NonNull
	private final UebungRepository uebungRepository;

	@NonNull
	private final KraftwertDtoMapper kraftwertDtoMapper;

	public List<KraftwertDto> findeAlleMitBenutzer(@NonNull String benutzerId)
	{
		return kraftwertDtoMapper.mappeAlle(
			kraftwertRepository.findeAlleMitBenutzer(new ID(benutzerId)));
	}

	public KraftwertDto finde(@NonNull String id) throws KraftwertException
	{
		return kraftwertRepository.finde(new ID(id))
			.map(kraftwertDtoMapper::mappe)
			.orElseThrow(() -> new KraftwertException("Der Kraftwert mit der ID %s existiert nicht!".formatted(id)));
	}

	public KraftwertDto speichere(
		@NonNull KraftwertDto kraftwertDto,
		@NonNull String uebungId,
		@NonNull String benutzerId)
		throws UebungException, BenutzerException
	{
		var kraftwert = new Kraftwert(
			new ID(),
			LocalDate.parse(kraftwertDto.getDatum(), DateTimeFormatter.ofPattern("dd.MM.yyyy")),
			new BigDecimal(kraftwertDto.getGewicht()),
			Wiederholungen.zuWert(kraftwertDto.getWiederholungen()),
			uebungRepository.finde(new ID(uebungId))
				.orElseThrow(
					() -> new UebungException("Die Uebung mit der ID %s existiert nicht!".formatted(uebungId))),
			benutzerRepository.finde(new ID(benutzerId))
				.orElseThrow(
					() -> new BenutzerException("Der Benutzer mit der ID %s existiert nicht!".formatted(benutzerId))));

		kraftwertRepository.speichere(kraftwert);

		return kraftwertDtoMapper.mappe(kraftwert);
	}
}
