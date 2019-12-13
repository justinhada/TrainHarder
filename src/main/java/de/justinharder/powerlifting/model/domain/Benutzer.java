package de.justinharder.powerlifting.model.domain;

import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import lombok.Data;

@Data
public class Benutzer
{
	private String vorname;
	private String nachname;
	private Geschlecht geschlecht;
	private double koerpergewicht;
	private int koerpergroesse;
	private Erfahrung erfahrung;
	private int alter;
	private Ernaehrung ernaehrung;
	private Schlafqualitaet schlafqualitaet;
	private Stress stress;
	private Doping doping;
	private Regenerationsfaehigkeit regenerationsfaehigkeit;
}
