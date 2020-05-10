package de.justinharder.trainharder.view;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.trainharder.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.trainharder.model.domain.exceptions.UngueltigesMaximumException;
import de.justinharder.trainharder.model.services.berechner.RepsInReserveRechner;
import de.justinharder.trainharder.view.navigation.ExternerWebContext;
import de.justinharder.trainharder.view.navigation.Navigator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named
@SessionScoped
public class RepsInReserveController extends Controller
{
	private static final long serialVersionUID = 5979310743260039068L;

	private final RepsInReserveRechner repsInReserveRechner;
	private int maximum;
	private int wiederholungen;
	private int rir;
	private int richtwert;
	private double[][] prozente;

	@Inject
	public RepsInReserveController(
		final ExternerWebContext externerWebContext,
		final Navigator navigator,
		final RepsInReserveRechner repsInReserveRechner)
	{
		super(externerWebContext, navigator);
		this.repsInReserveRechner = repsInReserveRechner;
	}

	public String ermittleRichtwert()
		throws UngueltigesMaximumException, UngueltigeWiederholungenException, UngueltigeRepsInReserveException
	{
		richtwert = repsInReserveRechner.berechneRichtwert(maximum, wiederholungen, rir);
		return "";
	} 

	public double[][] getProzente()
	{
		if (prozente == null)
		{
			prozente = repsInReserveRechner.getProzente();
		}
		return prozente;
	}
}
