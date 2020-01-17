package de.justinharder.powerlifting.model.services;

import java.util.List;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Uebung;
import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.repository.UebungRepository;

public class UebungService
{
	private final UebungRepository uebungRepository;

	@Inject
	public UebungService(final UebungRepository uebungRepository)
	{
		this.uebungRepository = uebungRepository;
	}

	public List<UebungEintrag> ermittleAlle()
	{
		return Konvertierer.konvertiereAlleZuUebungEintrag(uebungRepository.ermittleAlle());
	}

	public List<UebungEintrag> ermittleZuUebungsart(final String uebungsart)
	{
		return Konvertierer.konvertiereAlleZuUebungEintrag(
			uebungRepository.ermittleZuUebungsart(Uebungsart.fromUebungsartOption(uebungsart)));
	}

	public List<UebungEintrag> ermittleZuUebungskategorie(final String uebungskategorie)
	{
		return Konvertierer.konvertiereAlleZuUebungEintrag(
			uebungRepository.ermittleZuUebungskategorie(Uebungskategorie.fromUebungskategorieOption(uebungskategorie)));
	}

	public UebungEintrag ermittleZuId(final int id) throws UebungNichtGefundenException
	{
		final var uebung = uebungRepository.ermittleZuId(id);
		if (uebung == null)
		{
			throw new UebungNichtGefundenException("Die Uebung mit der ID \"" + id + "\" existiert nicht!");
		}
		return Konvertierer.konvertiereZuUebungEintrag(uebung);
	}

	public void erstelleUebung(final UebungEintrag uebungEintrag, final BelastungsfaktorEintrag belastungsfaktorEintrag)
	{
		final var uebung = new Uebung(
			uebungEintrag.getName(),
			Uebungsart.fromUebungsartOption(uebungEintrag.getUebungsart()),
			Uebungskategorie.fromUebungskategorieOption(uebungEintrag.getUebungskategorie()),
			Konvertierer.konvertiereZuBelastungsfaktor(belastungsfaktorEintrag));
		uebungRepository.erstelleUebung(uebung);
	}
}
