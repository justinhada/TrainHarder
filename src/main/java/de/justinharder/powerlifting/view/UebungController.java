package de.justinharder.powerlifting.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.dto.BelastungsfaktorEintrag;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.services.UebungService;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;

@Getter
@Named
@SessionScoped
public class UebungController extends Controller
{
	private static final String UEBUNG_ID = "uebungId";
	private final UebungService uebungService;
	private final UebungEintrag uebungEintrag = new UebungEintrag();
	private List<UebungEintrag> uebungEintraege = new ArrayList<>();

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

	public void getUebungenZuUebungsart(final String uebungsart)
	{
		uebungEintraege = uebungService.ermittleZuUebungsart(uebungsart);
	}

	public void getUebungenZuUebungskategorie(final String uebungskategorie)
	{
		uebungEintraege = uebungService.ermittleZuUebungskategorie(uebungskategorie);
	}

	public UebungEintrag getUebungZuId() throws UebungNichtGefundenException
	{
		return uebungService.ermittleZuId(getRequestParameter(UEBUNG_ID));
	}

	public void erstelleUebung(final BelastungsfaktorEintrag belastungsfaktorEintrag)
	{
		uebungService.erstelleUebung(uebungEintrag, belastungsfaktorEintrag);
	}
}
