package de.justinharder.powerlifting.model.domain.dto;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.justinharder.powerlifting.model.domain.Kraftwert;
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
	private String vorname;
	private String nachname;
	private int koerpergewicht;
	private int koerpergroesse;
	private int lebensalter;
	private final Map<Integer, Kraftwert> kraftwerte = new HashMap<>();
	@Enumerated(EnumType.STRING)
	private Kraftlevel kraftlevel;
	@Enumerated(EnumType.STRING)
	private Geschlecht geschlecht;
	@Enumerated(EnumType.STRING)
	private Erfahrung erfahrung;
	@Enumerated(EnumType.STRING)
	private Ernaehrung ernaehrung;
	@Enumerated(EnumType.STRING)
	private Schlafqualitaet schlafqualitaet;
	@Enumerated(EnumType.STRING)
	private Stress stress;
	@Enumerated(EnumType.STRING)
	private Doping doping;
	@Enumerated(EnumType.STRING)
	private Regenerationsfaehigkeit regenerationsfaehigkeit;
}
