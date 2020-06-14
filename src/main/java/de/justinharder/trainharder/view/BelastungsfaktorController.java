package de.justinharder.trainharder.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.services.BelastungsfaktorService;
import de.justinharder.trainharder.view.dto.BelastungsfaktorDto;

@Named
@SessionScoped
public class BelastungsfaktorController implements Serializable
{
	private static final long serialVersionUID = -2772258673635658073L;

	private final BelastungsfaktorService belastungsfaktorService;

	@Inject
	public BelastungsfaktorController(final BelastungsfaktorService belastungsfaktorService)
	{
		this.belastungsfaktorService = belastungsfaktorService;
	}

	public List<BelastungsfaktorDto> getBelastungsfaktoren()
	{
		return belastungsfaktorService.ermittleAlle();
	}

	public BelastungsfaktorDto getBelastungsfaktorZuId(final String id)
		throws BelastungsfaktorNichtGefundenException
	{
		return belastungsfaktorService.ermittleZuId(id);
	}
}
