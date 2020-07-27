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

	public Volumenrechner(final int anpassungsfaktor)
	{
		for (int uebung = 0; uebung < empfehlungen.length; uebung++)
		{
			for (int phase = 0; phase < empfehlungen[uebung].length; phase++)
			{
				final var mev = Konstanten.MINIMUM_EFFECTIVE_VOLUME[uebung][phase] + anpassungsfaktor;
				final var mrv = Konstanten.MAXIMUM_RECOVERABLE_VOLUME[uebung][phase] + anpassungsfaktor;
				empfehlungen[uebung][phase] = (mev + mrv) / 2;
			}
		}
	}

	public int[] getVolumenHypertrophiePhase()
	{
		return new int[]
		{ konvertiereZuInt(empfehlungen[0][0]), konvertiereZuInt(empfehlungen[0][1]),
			konvertiereZuInt(empfehlungen[0][2]) };
	}

	public int[] getVolumenKraftPhase()
	{
		return new int[]
		{ konvertiereZuInt(empfehlungen[1][0]), konvertiereZuInt(empfehlungen[1][1]),
			konvertiereZuInt(empfehlungen[1][2]) };
	}

	public int[] getVolumenPeakingPhase()
	{
		return new int[]
		{ konvertiereZuInt(empfehlungen[2][0]), konvertiereZuInt(empfehlungen[2][1]),
			konvertiereZuInt(empfehlungen[2][2]) };
	}

	private int konvertiereZuInt(final double zahl)
	{
		return (int) Math.round(zahl);
	}
}
