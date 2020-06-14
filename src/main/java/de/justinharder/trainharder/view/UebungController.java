package de.justinharder.trainharder.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.trainharder.model.services.UebungService;
import de.justinharder.trainharder.view.dto.UebungDto;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class UebungController implements Serializable
{
	private static final long serialVersionUID = -3551181310640925257L;

	private final UebungService uebungService;

	@Inject
	public UebungController(final UebungService uebungService)
	{
		this.uebungService = uebungService;
	}

	public List<UebungDto> getUebungen()
	{
		return uebungService.ermittleAlle();
	}

	public List<UebungDto> getUebungenZuUebungsart(final String uebungsart) throws UebungNichtGefundenException
	{
		return uebungService.ermittleZuUebungsart(uebungsart);
	}

	public List<UebungDto> getUebungenZuUebungskategorie(final String uebungskategorie)
		throws UebungNichtGefundenException
	{
		return uebungService.ermittleZuUebungskategorie(uebungskategorie);
	}

	public UebungDto getUebungZuId(final String id) throws UebungNichtGefundenException
	{
		return uebungService.ermittleZuId(id);
	}
}
