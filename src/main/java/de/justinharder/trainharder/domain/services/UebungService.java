package de.justinharder.trainharder.domain.services;

import de.justinharder.trainharder.domain.model.Uebung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.enums.Uebungsart;
import de.justinharder.trainharder.domain.model.enums.Uebungskategorie;
import de.justinharder.trainharder.domain.model.exceptions.BelastungException;
import de.justinharder.trainharder.domain.model.exceptions.UebungException;
import de.justinharder.trainharder.domain.repository.BelastungRepository;
import de.justinharder.trainharder.domain.repository.UebungRepository;
import de.justinharder.trainharder.domain.services.dto.UebungDto;
import de.justinharder.trainharder.domain.services.mapper.UebungDtoMapper;
import jakarta.enterprise.context.Dependent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Dependent
@RequiredArgsConstructor
public class UebungService
{
	@NonNull
	private final UebungRepository uebungRepository;

	@NonNull
	private final BelastungRepository belastungRepository;

	@NonNull
	private final UebungDtoMapper uebungDtoMapper;

	public List<UebungDto> findeAlle()
	{
		return uebungDtoMapper.mappeAlle(uebungRepository.findeAlle());
	}

	public List<UebungDto> findeAlleMitUebungsart(@NonNull String uebungsart)
	{
		return uebungDtoMapper.mappeAlle(uebungRepository.findeAlleMitUebungsart(Uebungsart.zuWert(uebungsart)));
	}

	public List<UebungDto> findeAlleMitUebungskategorie(@NonNull String uebungskategorie)
	{
		return uebungDtoMapper.mappeAlle(
			uebungRepository.findeAlleMitUebungskategorie(Uebungskategorie.zuWert(uebungskategorie)));
	}

	public UebungDto finde(@NonNull String id) throws UebungException
	{
		return uebungRepository.finde(new ID(id))
			.map(uebungDtoMapper::mappe)
			.orElseThrow(() -> new UebungException("Die Uebung mit der ID %s existiert nicht!".formatted(id)));
	}

	public UebungDto erstelle(@NonNull UebungDto uebungDto, @NonNull String belastungId) throws
		BelastungException
	{
		var uebung = new Uebung(
			new ID(),
			uebungDto.getName(),
			Uebungsart.zuWert(uebungDto.getUebungsart()),
			Uebungskategorie.zuWert(uebungDto.getUebungskategorie()),
			belastungRepository.finde(new ID(belastungId))
				.orElseThrow(() -> new BelastungException(
					"Die Belastung mit der ID %s existiert nicht!".formatted(belastungId))));

		uebungRepository.speichere(uebung);

		return uebungDtoMapper.mappe(uebung);
	}
}
