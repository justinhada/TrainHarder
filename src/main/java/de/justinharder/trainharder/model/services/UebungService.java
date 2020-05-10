package de.justinharder.trainharder.model.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import de.justinharder.trainharder.model.domain.Uebung;
import de.justinharder.trainharder.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.trainharder.model.domain.dto.UebungEintrag;
import de.justinharder.trainharder.model.domain.enums.Uebungsart;
import de.justinharder.trainharder.model.domain.enums.Uebungskategorie;
import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.repository.UebungRepository;

public class UebungService implements Serializable
{
	private static final long serialVersionUID = -8572879657920804422L;

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

	public List<UebungEintrag> ermittleZuUebungsart(final String uebungsart) throws UebungNichtGefundenException
	{
		return Konvertierer.konvertiereAlleZuUebungEintrag(
			uebungRepository.ermittleZuUebungsart(Uebungsart.fromUebungsartOption(uebungsart)));
	}

	public List<UebungEintrag> ermittleZuUebungskategorie(final String uebungskategorie)
		throws UebungNichtGefundenException
	{
		return Konvertierer.konvertiereAlleZuUebungEintrag(
			uebungRepository.ermittleZuUebungskategorie(Uebungskategorie.fromUebungskategorieOption(uebungskategorie)));
	}

	public UebungEintrag ermittleZuId(final String id) throws UebungNichtGefundenException
	{
		final var uebung = uebungRepository.ermittleZuId(Integer.valueOf(id));
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
