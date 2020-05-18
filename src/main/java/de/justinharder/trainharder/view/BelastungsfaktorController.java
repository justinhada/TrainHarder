package de.justinharder.trainharder.view;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.services.BelastungsfaktorService;

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

	public List<BelastungsfaktorEintrag> getBelastungsfaktoren()
	{
		return belastungsfaktorService.ermittleAlle();
	}

	public BelastungsfaktorEintrag getBelastungsfaktorZuId(final String id)
		throws BelastungsfaktorNichtGefundenException
	{
		return belastungsfaktorService.ermittleZuId(id);
	}
}
