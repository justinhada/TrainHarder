package de.justinharder.powerlifting.model.services;

import de.justinharder.powerlifting.model.domain.Konstanten;

public class Volumenrechner
{
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
		{ (int) Math.round(empfehlungen[0][0]), (int) Math.round(empfehlungen[0][1]),
			(int) Math.round(empfehlungen[0][2]) };
	}

	public int[] getVolumenKraftPhase()
	{
		return new int[]
		{ (int) Math.round(empfehlungen[1][0]), (int) Math.round(empfehlungen[1][1]),
			(int) Math.round(empfehlungen[1][2]) };
	}

	public int[] getVolumenPeakingPhase()
	{
		return new int[]
		{ (int) Math.round(empfehlungen[2][0]), (int) Math.round(empfehlungen[2][1]),
			(int) Math.round(empfehlungen[2][2]) };
	}
}
