package de.justinharder.powerlifting.model.services;

import de.justinharder.powerlifting.model.domain.Benutzer;
import de.justinharder.powerlifting.model.domain.Kraftwerte;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;

public class KraftlevelBerechner
{
	private static final int MAX = Integer.MAX_VALUE;
	private static final int MIN = 0;
	private static final int[][] KLASSIFIKATION_FRAUEN = new int[][]
	{
		{ 43, 47, 52, 57, 63, 72, 84, MAX },
		{ 275, 310, 334, 359, 382, 407, 440, 479 },
		{ 264, 297, 318, 342, 363, 386, 417, 453 },
		{ 241, 270, 288, 308, 326, 346, 373, 403 },
		{ 229, 254, 270, 288, 304, 322, 346, 374 },
		{ 204, 224, 236, 250, 264, 278, 297, 318 },
		{ 178, 193, 202, 214, 222, 232, 246, 263 },
		{ 156, 166, 172, 180, 185, 192, 202, 212 },
		{ MIN, MIN, MIN, MIN, MIN, MIN, MIN, MIN }
	};
	private static final int[][] KLASSIFIKATION_MAENNER = new int[][]
	{
		{ 53, 59, 66, 74, 83, 93, 105, 120, MAX },
		{ 456, 520, 567, 616, 666, 715, 765, 819, 877 },
		{ 432, 493, 538, 585, 633, 680, 729, 779, 835 },
		{ 385, 441, 482, 525, 569, 613, 656, 704, 755 },
		{ 356, 410, 449, 489, 531, 572, 614, 658, 706 },
		{ 304, 352, 387, 423, 460, 497, 534, 574, 617 },
		{ 251, 294, 325, 357, 390, 422, 455, 490, 528 },
		{ 205, 242, 269, 297, 326, 355, 383, 414, 448 },
		{ MIN, MIN, MIN, MIN, MIN, MIN, MIN, MIN, MIN }
	};
	private static final Kraftlevel[] KRAFTLEVEL_EINTEILUNG = new Kraftlevel[]
	{
		Kraftlevel.ELITE,
		Kraftlevel.MASTER,
		Kraftlevel.CLASS_1,
		Kraftlevel.CLASS_2,
		Kraftlevel.CLASS_3,
		Kraftlevel.CLASS_4,
		Kraftlevel.CLASS_5,
		Kraftlevel.CLASS_5
	};

	private final Benutzer benutzer;
	private final Geschlecht geschlecht;
	private final int koerpergewicht;
	private final Kraftwerte kraftwerte;

	public KraftlevelBerechner(final Benutzer benutzer)
	{
		this.benutzer = benutzer;
		geschlecht = benutzer.getGeschlecht();
		koerpergewicht = benutzer.getKoerpergewicht();
		kraftwerte = benutzer.getKraftwerte();
	}

	public void setzeKraftlevel()
	{
		final var klassifikationen =
			geschlecht.equals(Geschlecht.WEIBLICH) ? KLASSIFIKATION_FRAUEN : KLASSIFIKATION_MAENNER;

		var total = 0;
		for (final var kraftwert : kraftwerte)
		{
			total += kraftwert.getMaximum();
		}

		var gewichtIndex = 0;
		for (var i = 0; i < klassifikationen[0].length; i++)
		{
			if (koerpergewicht > klassifikationen[0][i])
			{
				if (i + 1 == klassifikationen[0].length)
				{
					gewichtIndex = i;
				}
				else
				{
					gewichtIndex = i + 1;
				}
			}
		}

		var totalIndex = 0;
		for (var i = klassifikationen.length - 1; i > 0; i--)
		{
			if (total >= klassifikationen[i][gewichtIndex])
			{
				totalIndex = i;
			}
		}

		final var kraftlevel = KRAFTLEVEL_EINTEILUNG[totalIndex - 1];
		benutzer.setKraftlevel(kraftlevel);
	}
}
