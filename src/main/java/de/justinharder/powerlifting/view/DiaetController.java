package de.justinharder.powerlifting.view;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.services.berechner.Diaetrechner;
import de.justinharder.powerlifting.view.navigation.ExternerWebContext;
import de.justinharder.powerlifting.view.navigation.Navigator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@SessionScoped
public class DiaetController extends Controller
{
	private static final long serialVersionUID = -6873111824684874634L;

	private final Diaetrechner diaetrechner;
	private double koerpergewichtIst =90.0;
	private double koerperfettAnteilIst;
	private double koerperfettAnteilSoll;
	private int diaetWochen;

	@Inject
	public DiaetController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final Diaetrechner diaetrechner)
	{
		super(externerWebContext, navigator);
		this.diaetrechner = diaetrechner;
	}

	public int getDiaetInWochen()
	{
		return diaetrechner.berechneDiaetInWochen(koerpergewichtIst, koerperfettAnteilIst, koerperfettAnteilSoll);
	}

	public int getDiaetInTagen()
	{
		return diaetrechner.berechneDiaetInTagen(koerpergewichtIst, koerperfettAnteilIst, koerperfettAnteilSoll);
	}

	public int getGeschaetztenKoerperfettAnteil()
	{
		return diaetrechner.berechneGeschaetztenKoerperfettAnteil(koerpergewichtIst, koerperfettAnteilIst, diaetWochen);
	}
}
