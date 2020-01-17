package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.BelastungsfaktorNichtGefundenException;
import de.justinharder.powerlifting.model.services.BelastungsfaktorService;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;

@Named
@RequestScoped
@Getter
public class BelastungsfaktorController extends Controller
{
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
