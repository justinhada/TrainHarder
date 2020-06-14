package de.justinharder.trainharder.model.services;

import java.io.Serializable;
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
import de.justinharder.trainharder.view.dto.UebungEintrag;

public class UebungService implements Serializable
{
	private static final long serialVersionUID = -8572879657920804422L;

	private final UebungRepository uebungRepository;
	private final BelastungsfaktorRepository belastungsfaktorRepository;

	@Inject
	public UebungService(
		final UebungRepository uebungRepository,
		final BelastungsfaktorRepository belastungsfaktorRepository)
	{
		this.uebungRepository = uebungRepository;
		this.belastungsfaktorRepository = belastungsfaktorRepository;
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
		Preconditions.checkNotNull(id, "Ermittlung der Uebung benötigt eine gültige UebungID!");

		return Konvertierer.konvertiereZuUebungEintrag(uebungRepository
			.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(
				() -> new UebungNichtGefundenException("Die Uebung mit der ID \"" + id + "\" existiert nicht!")));
	}

	public UebungEintrag speichereUebung(final UebungEintrag uebungEintrag, final String belastungsfaktorId)
		throws BelastungsfaktorNichtGefundenException
	{
		final var belastungsfaktor = belastungsfaktorRepository
			.ermittleZuId(new Primaerschluessel(belastungsfaktorId))
			.orElseThrow(() -> new BelastungsfaktorNichtGefundenException(
				"Der Belastungsfaktor mit der ID \"" + belastungsfaktorId + "\" existiert nicht!"));

		return Konvertierer.konvertiereZuUebungEintrag(uebungRepository
			.speichereUebung(new Uebung(
				new Primaerschluessel(),
				uebungEintrag.getName(),
				Uebungsart.fromUebungsartOption(uebungEintrag.getUebungsart()),
				Uebungskategorie.fromUebungskategorieOption(uebungEintrag.getUebungskategorie()),
				belastungsfaktor)));
	}
}
