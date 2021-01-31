package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Konstanten;

public class Volumenrechner
{
	/*
	 * 				Kniebeuge	Bankdrücken		Kreuzheben
	 * Hypertrophie	0,0			0,1				0,2
	 * Kraft		1,0			1,1				1,2
	 * Peaking		2,0			2,1				2,2
	 */
	private final double[][] empfehlungen = new double[3][3];

	public Volumenrechner(int anpassungsfaktor)
	{
		for (int uebung = 0; uebung < empfehlungen.length; uebung++)
		{
			for (int phase = 0; phase < empfehlungen[uebung].length; phase++)
			{
				var mev = Konstanten.MINIMUM_EFFECTIVE_VOLUME.get(uebung).get(phase) + anpassungsfaktor;
				var mrv = Konstanten.MAXIMUM_RECOVERABLE_VOLUME.get(uebung).get(phase) + anpassungsfaktor;
				empfehlungen[uebung][phase] = (mev + mrv) / 2;
			}
		}
	}

	public int[] getVolumenHypertrophiePhase()
	{
		return new int[] { mappeZuInt(empfehlungen[0][0]), mappeZuInt(empfehlungen[0][1]), mappeZuInt(empfehlungen[0][2]) };
	}

	public int[] getVolumenKraftPhase()
	{
		return new int[] { mappeZuInt(empfehlungen[1][0]), mappeZuInt(empfehlungen[1][1]), mappeZuInt(empfehlungen[1][2]) };
	}

	public int[] getVolumenPeakingPhase()
	{
		return new int[] { mappeZuInt(empfehlungen[2][0]), mappeZuInt(empfehlungen[2][1]), mappeZuInt(empfehlungen[2][2]) };
	}

	private int mappeZuInt(double zahl)
	{
		return (int) Math.round(zahl);
	}
}
