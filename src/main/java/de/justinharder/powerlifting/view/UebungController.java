package de.justinharder.powerlifting.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.dto.UebungEintrag;
import de.justinharder.powerlifting.model.domain.exceptions.UebungNichtGefundenException;
import de.justinharder.powerlifting.model.services.UebungService;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter
@Setter
public class UebungController extends Controller
{
	private final UebungService uebungService;

	private int id;
	private String name;
	private String uebungsart;
	private String uebungskategorie;
	private Belastungsfaktor belastungsfaktor;

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

	public List<UebungEintrag> getUebungenZuUebungsart()
	{
		return uebungService.ermittleZuUebungsart(uebungsart);
	}

	public List<UebungEintrag> getUebungenZuUebungskategorie()
	{
		return uebungService.ermittleZuUebungskategorie(uebungskategorie);
	}

	public UebungEintrag getUebungZuId() throws UebungNichtGefundenException
	{
		return uebungService.ermittleZuId(id);
	}

	public UebungEintrag erstelleUebung()
	{
		return uebungService.erstelleUebung(name, uebungsart, uebungskategorie, belastungsfaktor);
	}
}
