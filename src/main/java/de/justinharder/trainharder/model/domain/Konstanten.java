package de.justinharder.trainharder.model.domain;

import com.google.common.collect.ImmutableList;
import de.justinharder.trainharder.model.domain.enums.Kraftlevel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Konstanten
{
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = 0;

	public static final List<Integer> GEWICHTSKLASSEN_FRAUEN = List.of(43, 47, 52, 57, 63, 72, 84, MAX);

	public static final List<Integer> GEWICHTSKLASSEN_MAENNER = List.of(53, 59, 66, 74, 83, 93, 105, 120, MAX);

	public static final Map<Integer, Map<Integer, Kraftlevel>> TOTALS_FRAUEN = Map.of(
		GEWICHTSKLASSEN_FRAUEN.get(0), Map.of(
			156, Kraftlevel.CLASS_5,
			178, Kraftlevel.CLASS_4,
			203, Kraftlevel.CLASS_3,
			228, Kraftlevel.CLASS_2,
			241, Kraftlevel.CLASS_1,
			263, Kraftlevel.MASTER,
			275, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(1), Map.of(
			166, Kraftlevel.CLASS_5,
			193, Kraftlevel.CLASS_4,
			223, Kraftlevel.CLASS_3,
			253, Kraftlevel.CLASS_2,
			269, Kraftlevel.CLASS_1,
			296, Kraftlevel.MASTER,
			310, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(2), Map.of(
			172, Kraftlevel.CLASS_5,
			202, Kraftlevel.CLASS_4,
			236, Kraftlevel.CLASS_3,
			269, Kraftlevel.CLASS_2,
			287, Kraftlevel.CLASS_1,
			317, Kraftlevel.MASTER,
			333, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(3), Map.of(
			179, Kraftlevel.CLASS_5,
			213, Kraftlevel.CLASS_4,
			250, Kraftlevel.CLASS_3,
			287, Kraftlevel.CLASS_2,
			307, Kraftlevel.CLASS_1,
			341, Kraftlevel.MASTER,
			358, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(4), Map.of(
			185, Kraftlevel.CLASS_5,
			222, Kraftlevel.CLASS_4,
			263, Kraftlevel.CLASS_3,
			303, Kraftlevel.CLASS_2,
			325, Kraftlevel.CLASS_1,
			362, Kraftlevel.MASTER,
			381, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(5), Map.of(
			192, Kraftlevel.CLASS_5,
			232, Kraftlevel.CLASS_4,
			277, Kraftlevel.CLASS_3,
			321, Kraftlevel.CLASS_2,
			345, Kraftlevel.CLASS_1,
			385, Kraftlevel.MASTER,
			406, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(6), Map.of(
			202, Kraftlevel.CLASS_5,
			246, Kraftlevel.CLASS_4,
			296, Kraftlevel.CLASS_3,
			345, Kraftlevel.CLASS_2,
			372, Kraftlevel.CLASS_1,
			416, Kraftlevel.MASTER,
			439, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_FRAUEN.get(7), Map.of(
			212, Kraftlevel.CLASS_5,
			262, Kraftlevel.CLASS_4,
			317, Kraftlevel.CLASS_3,
			373, Kraftlevel.CLASS_2,
			402, Kraftlevel.CLASS_1,
			452, Kraftlevel.MASTER,
			478, Kraftlevel.ELITE));

	public static final Map<Integer, Map<Integer, Kraftlevel>> TOTALS_MAENNER = Map.of(
		GEWICHTSKLASSEN_MAENNER.get(0), Map.of(
			204, Kraftlevel.CLASS_5,
			251, Kraftlevel.CLASS_4,
			303, Kraftlevel.CLASS_3,
			355, Kraftlevel.CLASS_2,
			384, Kraftlevel.CLASS_1,
			431, Kraftlevel.MASTER,
			455, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(1), Map.of(
			241, Kraftlevel.CLASS_5,
			293, Kraftlevel.CLASS_4,
			351, Kraftlevel.CLASS_3,
			409, Kraftlevel.CLASS_2,
			440, Kraftlevel.CLASS_1,
			492, Kraftlevel.MASTER,
			519, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(2), Map.of(
			268, Kraftlevel.CLASS_5,
			324, Kraftlevel.CLASS_4,
			386, Kraftlevel.CLASS_3,
			448, Kraftlevel.CLASS_2,
			481, Kraftlevel.CLASS_1,
			537, Kraftlevel.MASTER,
			566, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(3), Map.of(
			296, Kraftlevel.CLASS_5,
			356, Kraftlevel.CLASS_4,
			422, Kraftlevel.CLASS_3,
			488, Kraftlevel.CLASS_2,
			524, Kraftlevel.CLASS_1,
			584, Kraftlevel.MASTER,
			615, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(4), Map.of(
			325, Kraftlevel.CLASS_5,
			389, Kraftlevel.CLASS_4,
			459, Kraftlevel.CLASS_3,
			530, Kraftlevel.CLASS_2,
			568, Kraftlevel.CLASS_1,
			632, Kraftlevel.MASTER,
			665, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(5), Map.of(
			354, Kraftlevel.CLASS_5,
			421, Kraftlevel.CLASS_4,
			496, Kraftlevel.CLASS_3,
			571, Kraftlevel.CLASS_2,
			612, Kraftlevel.CLASS_1,
			679, Kraftlevel.MASTER,
			714, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(6), Map.of(
			382, Kraftlevel.CLASS_5,
			454, Kraftlevel.CLASS_4,
			533, Kraftlevel.CLASS_3,
			613, Kraftlevel.CLASS_2,
			655, Kraftlevel.CLASS_1,
			727, Kraftlevel.MASTER,
			764, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(7), Map.of(
			413, Kraftlevel.CLASS_5,
			489, Kraftlevel.CLASS_4,
			573, Kraftlevel.CLASS_3,
			657, Kraftlevel.CLASS_2,
			702, Kraftlevel.CLASS_1,
			777, Kraftlevel.MASTER,
			817, Kraftlevel.ELITE),
		GEWICHTSKLASSEN_MAENNER.get(8), Map.of(
			447, Kraftlevel.CLASS_5,
			527, Kraftlevel.CLASS_4,
			616, Kraftlevel.CLASS_3,
			705, Kraftlevel.CLASS_2,
			753, Kraftlevel.CLASS_1,
			833, Kraftlevel.MASTER,
			875, Kraftlevel.ELITE));

	// Index entspricht den Wiederholungen (1-12)
	public static final List<Double> ONE_REP_MAX_UMRECHNUNG = ImmutableList.of(
		1.00, 0.95, 0.92, 0.89, 0.86, 0.84, 0.82, 0.79, 0.77, 0.75, 0.72, 0.69);

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
