package de.justinharder.trainharder.view;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.trainharder.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.trainharder.model.services.BelastungsfaktorService;
import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Navigator;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class BelastungsfaktorController extends Controller
{
	private static final long serialVersionUID = -2772258673635658073L;

	private final BelastungsfaktorService belastungsfaktorService;
	private final BelastungsfaktorEintrag belastungsfaktorEintrag = new BelastungsfaktorEintrag();

	@Inject
	public BelastungsfaktorController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final BelastungsfaktorService belastungsfaktorService)
	{
		super(externerWebContext, navigator);
		this.belastungsfaktorService = belastungsfaktorService;
	}

	public List<BelastungsfaktorEintrag> getBelastungsfaktoren()
	{
		return belastungsfaktorService.ermittleAlle();
	}

	public BelastungsfaktorEintrag getBelastungsfaktorZuId() throws BelastungsfaktorNichtGefundenException
	{
		return belastungsfaktorService.ermittleZuId(belastungsfaktorEintrag.getId());
	}

	public void erstelleBelastungsfaktor()
	{
		belastungsfaktorService.erstelleBelastungsfaktor(belastungsfaktorEintrag);
	}
}