package de.justinharder.trainharder.model.services;

import java.util.List;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.model.repository.UebungRepository;
import de.justinharder.trainharder.model.services.mapper.UebungDtoMapper;
import de.justinharder.trainharder.view.dto.UebungDto;

public class UebungService
{
	private final UebungRepository uebungRepository;
	private final BelastungsfaktorRepository belastungsfaktorRepository;
	private final UebungDtoMapper uebungDtoMapper;

	@Inject
	public UebungService(
		final UebungRepository uebungRepository,
		final BelastungsfaktorRepository belastungsfaktorRepository,
		final UebungDtoMapper uebungDtoMapper)
	{
		this.uebungRepository = uebungRepository;
		this.belastungsfaktorRepository = belastungsfaktorRepository;
		this.uebungDtoMapper = uebungDtoMapper;
	}

	public List<UebungDto> ermittleAlle()
	{
		return uebungDtoMapper.konvertiereAlle(uebungRepository.ermittleAlle());
	}

	public List<UebungDto> ermittleZuUebungsart(final String uebungsart) throws UebungNichtGefundenException
	{
		Preconditions.checkNotNull(uebungsart, "Die Ermittlung der Uebungen benötigt eine gültige Uebungsart!");

		return uebungDtoMapper.konvertiereAlle(
			uebungRepository.ermittleZuUebungsart(Uebungsart.fromUebungsartOption(uebungsart)));
	}

	public List<UebungDto> ermittleZuUebungskategorie(final String uebungskategorie)
		throws UebungNichtGefundenException
	{
		Preconditions.checkNotNull(uebungskategorie,
			"Die Ermittlung der Uebungen benötigt eine gültige Uebungskategorie!");

		return uebungDtoMapper.konvertiereAlle(
			uebungRepository.ermittleZuUebungskategorie(Uebungskategorie.fromUebungskategorieOption(uebungskategorie)));
	}

	public UebungDto ermittleZuId(final String id) throws UebungNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Die Ermittlung der Uebung benötigt eine gültige UebungID!");

		return uebungRepository.ermittleZuId(new Primaerschluessel(id))
			.map(uebungDtoMapper::konvertiere)
			.orElseThrow(
				() -> new UebungNichtGefundenException("Die Uebung mit der ID \"" + id + "\" existiert nicht!"));
	}

	public UebungDto speichereUebung(final UebungDto uebungDto, final String belastungsfaktorId)
		throws BelastungsfaktorNichtGefundenException
	{
		Preconditions.checkNotNull(uebungDto, "Zur Erstellung der Uebung wird ein gültiges UebungDto benötigt!");
		Preconditions.checkNotNull(belastungsfaktorId,
			"Zur Erstellung der Uebung wird eine gültige BelastungsfaktorID benötigt!");

		final var belastungsfaktor = belastungsfaktorRepository
			.ermittleZuId(new Primaerschluessel(belastungsfaktorId))
			.orElseThrow(() -> new BelastungsfaktorNichtGefundenException(
				"Der Belastungsfaktor mit der ID \"" + belastungsfaktorId + "\" existiert nicht!"));

		return uebungDtoMapper.konvertiere(uebungRepository.speichereUebung(new Uebung(
			new Primaerschluessel(),
			uebungDto.getName(),
			Uebungsart.fromUebungsartOption(uebungDto.getUebungsart()),
			Uebungskategorie.fromUebungskategorieOption(uebungDto.getUebungskategorie()),
			belastungsfaktor)));
	}
}
