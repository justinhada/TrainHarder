package de.justinharder.trainharder.model.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.google.common.base.Preconditions;

import de.justinharder.trainharder.model.domain.Belastungsfaktor;
import de.justinharder.trainharder.model.domain.Primaerschluessel;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.repository.BelastungsfaktorRepository;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;

public class BelastungsfaktorService implements Serializable
{
	private static final long serialVersionUID = 1798559648180042690L;

	private final BelastungsfaktorRepository belastungsfaktorRepository;

	@Inject
	public BelastungsfaktorService(final BelastungsfaktorRepository belastungsfaktorRepository)
	{
		this.belastungsfaktorRepository = belastungsfaktorRepository;
	}

	public List<BelastungsfaktorDto> ermittleAlle()
	{
		return Konvertierer.konvertiereAlleZuBelastungsfaktorEintrag(belastungsfaktorRepository.ermittleAlle());
	}

	public BelastungsfaktorDto ermittleZuId(final String id) throws BelastungsfaktorNichtGefundenException
	{
		Preconditions.checkNotNull(id, "Ermittlung des Belastungsfaktors benötigt eine gültige BelastungsfaktorID!");

		return Konvertierer.konvertiereZuBelastungsfaktorEintrag(belastungsfaktorRepository
			.ermittleZuId(new Primaerschluessel(id))
			.orElseThrow(() -> new BelastungsfaktorNichtGefundenException(
				"Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!")));
	}

	public BelastungsfaktorDto speichereBelastungsfaktor(final BelastungsfaktorDto belastungsfaktorEintrag)
	{
		return Konvertierer.konvertiereZuBelastungsfaktorEintrag(belastungsfaktorRepository
			.speichereBelastungsfaktor(new Belastungsfaktor(
				new Primaerschluessel(),
				belastungsfaktorEintrag.getSquat(),
				belastungsfaktorEintrag.getBenchpress(),
				belastungsfaktorEintrag.getDeadlift(),
				belastungsfaktorEintrag.getTriceps(),
				belastungsfaktorEintrag.getChest(),
				belastungsfaktorEintrag.getCore(),
				belastungsfaktorEintrag.getBack(),
				belastungsfaktorEintrag.getBiceps(),
				belastungsfaktorEintrag.getGlutes(),
				belastungsfaktorEintrag.getQuads(),
				belastungsfaktorEintrag.getHamstrings(),
				belastungsfaktorEintrag.getShoulder())));
	}
}
