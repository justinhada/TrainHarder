package de.justinharder.trainharder.model.domain;

import com.google.common.collect.ImmutableList;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Konstanten
{
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = 0;

	public static final List<List<Integer>> KLASSIFIKATION_FRAUEN = ImmutableList.of(
		List.of(43, 47, 52, 57, 63, 72, 84, MAX),
		List.of(275, 310, 334, 359, 382, 407, 440, 479),
		List.of(264, 297, 318, 342, 363, 386, 417, 453),
		List.of(241, 270, 288, 308, 326, 346, 373, 403),
		List.of(229, 254, 270, 288, 304, 322, 346, 374),
		List.of(204, 224, 236, 250, 264, 278, 297, 318),
		List.of(178, 193, 202, 214, 222, 232, 246, 263),
		List.of(156, 166, 172, 180, 185, 192, 202, 212),
		List.of(MIN, MIN, MIN, MIN, MIN, MIN, MIN, MIN));

	public static final List<List<Integer>> KLASSIFIKATION_MAENNER = ImmutableList.of(
		List.of(53, 59, 66, 74, 83, 93, 105, 120, MAX),
		List.of(456, 520, 567, 616, 666, 715, 765, 819, 877),
		List.of(432, 493, 538, 585, 633, 680, 729, 779, 835),
		List.of(385, 441, 482, 525, 569, 613, 656, 704, 755),
		List.of(356, 410, 449, 489, 531, 572, 614, 658, 706),
		List.of(304, 352, 387, 423, 460, 497, 534, 574, 617),
		List.of(251, 294, 325, 357, 390, 422, 455, 490, 528),
		List.of(205, 242, 269, 297, 326, 355, 383, 414, 448),
		List.of(MIN, MIN, MIN, MIN, MIN, MIN, MIN, MIN));

	public static final List<Kraftlevel> KRAFTLEVEL_EINTEILUNG = List.of(
		Kraftlevel.ELITE,
		Kraftlevel.MASTER,
		Kraftlevel.CLASS_1,
		Kraftlevel.CLASS_2,
		Kraftlevel.CLASS_3,
		Kraftlevel.CLASS_4,
		Kraftlevel.CLASS_5,
		Kraftlevel.CLASS_5);

	// Zeilen entsprechen den RIR (0-4), Spalten den Reps (1-12)
	public static final List<List<Double>> PROZENTE = ImmutableList.of(
		List.of(1.0000, 0.9700, 0.9400, 0.9000, 0.8700, 0.8400, 0.8100, 0.7800, 0.7700, 0.7500, 0.7400, 0.7200),
		List.of(0.9750, 0.9450, 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150),
		List.of(0.9450, 0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150, 0.6950),
		List.of(0.9100, 0.8800, 0.8500, 0.8200, 0.7900, 0.7750, 0.7550, 0.7450, 0.7250, 0.7150, 0.6950, 0.6850),
		List.of(0.8825, 0.8500, 0.8200, 0.7950, 0.7650, 0.7500, 0.7375, 0.7325, 0.7125, 0.7025, 0.6825, 0.6725));

	// Minimale Richtwerte für Kniebeuge, Bankdrücken & Kreuzheben
	public static final List<List<Double>> MINIMUM_EFFECTIVE_VOLUME = ImmutableList.of(
		List.of(7.5, 9.0, 5.5), // Hypertrophie
		List.of(5.5, 8.0, 4.5), // Kraft
		List.of(4.5, 6.5, 2.5)); // Peaking

	// Maximale Richtwerte für Kniebeuge, Bankdrücken & Kreuzheben
	public static final List<List<Double>> MAXIMUM_RECOVERABLE_VOLUME = ImmutableList.of(
		List.of(14.0, 17.0, 11.0), // Hypertrophie
		List.of(9.0, 11.0, 7.0), // Kraft
		List.of(6.0, 8.5, 4.5)); // Peaking
}
