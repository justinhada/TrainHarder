package de.justinharder.powerlifting.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeRepsInReserveException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigeWiederholungenException;
import de.justinharder.powerlifting.model.domain.exceptions.UngueltigesMaximumException;
import de.justinharder.powerlifting.model.services.RepsInReserveRechner;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter
@Setter
public class RepsInReserveController
{
	private final RepsInReserveRechner repsInReserveRechner;
	private int maximum;
	private int wiederholungen;
	private int rir;
	private int richtwert;
	private double[][] prozente;

	@Inject
	public RepsInReserveController(final RepsInReserveRechner repsInReserveRechner)
	{
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
