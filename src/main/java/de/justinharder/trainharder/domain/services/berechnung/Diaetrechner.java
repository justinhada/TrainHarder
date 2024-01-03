package de.justinharder.trainharder.domain.services.berechnung;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Diaetrechner
{
	private static final double WOECHENTLICHES_ABNAHMEZIEL = 0.01;
	private static final double ZU_HALTENDES_FETTFREIES_KOERPERGEWICHT = 0.97;

	public int berechneDiaetInWochen(double koerpergewichtIst,double koerperfettAnteilIst,double koerperfettAnteilSoll)
	{
		koerperfettAnteilIst /= 100.0;
		koerperfettAnteilSoll /= 100.0;

		var fettfreiesKoerpergewichtIst = koerpergewichtIst * (1 - koerperfettAnteilIst);
		var fettfreiesKoerpergewichtSoll = fettfreiesKoerpergewichtIst * ZU_HALTENDES_FETTFREIES_KOERPERGEWICHT;
		var koerpergewichtSoll = fettfreiesKoerpergewichtSoll / (1 - koerperfettAnteilSoll);
		var wochen = (koerpergewichtIst - koerpergewichtSoll) / (koerpergewichtIst * WOECHENTLICHES_ABNAHMEZIEL);
		return (int) Math.round(wochen);
	}

	public int berechneDiaetInTagen(double koerpergewichtIst, double koerperfettAnteilIst, double koerperfettAnteilSoll)
	{
		return berechneDiaetInWochen(koerpergewichtIst, koerperfettAnteilIst, koerperfettAnteilSoll) * 7;
	}

	public int berechneGeschaetztenKoerperfettAnteil(double koerpergewichtIst, double koerperfettAnteilIst, int diaetWochen)
	{
		koerperfettAnteilIst /= 100.0;

		var fettfreiesKoerpergewichtIst = koerpergewichtIst * (1 - koerperfettAnteilIst);
		var fettfreiesKoerpergewichtSoll = fettfreiesKoerpergewichtIst * ZU_HALTENDES_FETTFREIES_KOERPERGEWICHT;
		var koerpergewichtSoll = koerpergewichtIst - diaetWochen * koerpergewichtIst * WOECHENTLICHES_ABNAHMEZIEL;
		var koerperfettAnteilSoll = (1 - fettfreiesKoerpergewichtSoll / koerpergewichtSoll) * 100;
		return (int) Math.round(koerperfettAnteilSoll);
	}
}
