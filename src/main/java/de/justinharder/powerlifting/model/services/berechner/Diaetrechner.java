package de.justinharder.powerlifting.model.services.berechner;

import java.io.Serializable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Diaetrechner implements Serializable
{
	private static final long serialVersionUID = 2403381832875358393L;

	private static final double WOECHENTLICHES_ABNAHMEZIEL = 0.01;
	private static final double ZU_HALTENDES_FETTFREIES_KOERPERGEWICHT = 0.97;

	public int berechneDiaetInWochen(final double koerpergewichtIst, double koerperfettAnteilIst,
		double koerperfettAnteilSoll)
	{
		koerperfettAnteilIst /= 100.0;
		koerperfettAnteilSoll /= 100.0;

		final var fettfreiesKoerpergewichtIst = koerpergewichtIst * (1 - koerperfettAnteilIst);
		final var fettfreiesKoerpergewichtSoll = fettfreiesKoerpergewichtIst * ZU_HALTENDES_FETTFREIES_KOERPERGEWICHT;
		final var koerpergewichtSoll = fettfreiesKoerpergewichtSoll / (1 - koerperfettAnteilSoll);
		final var wochen = (koerpergewichtIst - koerpergewichtSoll) / (koerpergewichtIst * WOECHENTLICHES_ABNAHMEZIEL);
		return (int) Math.round(wochen);
	}

	public int berechneDiaetInTagen(final double koerpergewichtIst, final double koerperfettAnteilIst,
		final double koerperfettAnteilSoll)
	{
		return berechneDiaetInWochen(koerpergewichtIst, koerperfettAnteilIst, koerperfettAnteilSoll) * 7;
	}

	public int berechneGeschaetztenKoerperfettAnteil(final double koerpergewichtIst, double koerperfettAnteilIst,
		final int diaetWochen)
	{
		koerperfettAnteilIst /= 100.0;

		final var fettfreiesKoerpergewichtIst = koerpergewichtIst * (1 - koerperfettAnteilIst);
		final var fettfreiesKoerpergewichtSoll = fettfreiesKoerpergewichtIst * ZU_HALTENDES_FETTFREIES_KOERPERGEWICHT;
		final var koerpergewichtSoll = koerpergewichtIst - diaetWochen * koerpergewichtIst * WOECHENTLICHES_ABNAHMEZIEL;
		final var koerperfettAnteilSoll = (1 - fettfreiesKoerpergewichtSoll / koerpergewichtSoll) * 100;
		return (int) Math.round(koerperfettAnteilSoll);
	}
}
