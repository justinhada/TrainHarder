package de.justinharder.powerlifting.model.domain.dto;

import de.justinharder.powerlifting.model.domain.enums.Doping;
import de.justinharder.powerlifting.model.domain.enums.Erfahrung;
import de.justinharder.powerlifting.model.domain.enums.Ernaehrung;
import de.justinharder.powerlifting.model.domain.enums.Geschlecht;
import de.justinharder.powerlifting.model.domain.enums.Kraftlevel;
import de.justinharder.powerlifting.model.domain.enums.Regenerationsfaehigkeit;
import de.justinharder.powerlifting.model.domain.enums.Schlafqualitaet;
import de.justinharder.powerlifting.model.domain.enums.Stress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenutzerEintrag
{
	private int id;
	private String vorname;
	private String nachname;
	private int koerpergewicht;
	private int koerpergroesse;
	private int lebensalter;
	private Kraftlevel kraftlevel;
	private Geschlecht geschlecht;
	private Erfahrung erfahrung;
	private Ernaehrung ernaehrung;
	private Schlafqualitaet schlafqualitaet;
	private Stress stress;
	private Doping doping;
	private Regenerationsfaehigkeit regenerationsfaehigkeit;
}
