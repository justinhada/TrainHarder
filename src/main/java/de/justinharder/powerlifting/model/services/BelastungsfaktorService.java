package de.justinharder.powerlifting.model.services;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.powerlifting.model.repository.BelastungsfaktorRepository;

public class BelastungsfaktorService implements Serializable
{
	private static final long serialVersionUID = 1798559648180042690L;

	private final BelastungsfaktorRepository belastungsfaktorRepository;

	@Inject
	public BelastungsfaktorService(final BelastungsfaktorRepository belastungsfaktorRepository)
	{
		this.belastungsfaktorRepository = belastungsfaktorRepository;
	}

	public List<BelastungsfaktorEintrag> ermittleAlle()
	{
		return konvertiereAlle(belastungsfaktorRepository.ermittleAlle());
	}

	public BelastungsfaktorEintrag ermittleZuId(final int id) throws BelastungsfaktorNichtGefundenException
	{
		final var belastungsfaktor = belastungsfaktorRepository.ermittleZuId(id);
		if (belastungsfaktor == null)
		{
			throw new BelastungsfaktorNichtGefundenException(
				"Der Belastungsfaktor mit der ID \"" + id + "\" existiert nicht!");
		}
		return konvertiere(belastungsfaktor);
	}

	public void erstelleBelastungsfaktor(final BelastungsfaktorEintrag belastungsfaktorEintrag)
	{
		final var belastungsfaktor = new Belastungsfaktor(
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
			belastungsfaktorEintrag.getShoulder());
		belastungsfaktorRepository.erstelleBelastungsfaktor(belastungsfaktor);
	}

	private List<BelastungsfaktorEintrag> konvertiereAlle(final List<Belastungsfaktor> belastungsfaktoren)
	{
		return belastungsfaktoren
			.stream()
			.map(this::konvertiere)
			.collect(Collectors.toList());
	}

	private BelastungsfaktorEintrag konvertiere(final Belastungsfaktor belastungsfaktor)
	{
		return new BelastungsfaktorEintrag(
			belastungsfaktor.getId(),
			belastungsfaktor.getSquat(),
			belastungsfaktor.getBenchpress(),
			belastungsfaktor.getDeadlift(),
			belastungsfaktor.getTriceps(),
			belastungsfaktor.getChest(),
			belastungsfaktor.getCore(),
			belastungsfaktor.getBack(),
			belastungsfaktor.getBiceps(),
			belastungsfaktor.getGlutes(),
			belastungsfaktor.getQuads(),
			belastungsfaktor.getHamstrings(),
			belastungsfaktor.getShoulder());
	}
}
