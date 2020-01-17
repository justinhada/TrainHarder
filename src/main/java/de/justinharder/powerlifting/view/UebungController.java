package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.services.UebungService;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;

@Named
@RequestScoped
@Getter
public class UebungController extends Controller
{
	private final UebungService uebungService;
	private final UebungEintrag uebungEintrag = new UebungEintrag();

	@Inject
	public UebungController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final UebungService uebungService)
	{
		super(externerWebContext, navigator);
		this.uebungService = uebungService;
	}

	public List<UebungEintrag> getUebungen()
	{
		return uebungService.ermittleAlle();
	}

	public List<UebungEintrag> getUebungenZuUebungsart(final String uebungsart)
	{
		return uebungService.ermittleZuUebungsart(uebungsart);
	}

	public List<UebungEintrag> getUebungenZuUebungskategorie(final String uebungskategorie)
	{
		return uebungService.ermittleZuUebungskategorie(uebungskategorie);
	}

	public UebungEintrag getUebungZuId() throws UebungNichtGefundenException
	{
		return uebungService.ermittleZuId(uebungEintrag.getId());
	}

	public void erstelleUebung(final BelastungsfaktorEintrag belastungsfaktorEintrag)
	{
		uebungService.erstelleUebung(uebungEintrag, belastungsfaktorEintrag);
	}
}
