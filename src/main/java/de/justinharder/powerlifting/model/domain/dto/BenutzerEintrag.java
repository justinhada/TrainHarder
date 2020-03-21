package de.justinharder.powerlifting.model.domain.dto;

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
	private int koerpergroesse;
	private double koerpergewicht;
	private int lebensalter;
	private String kraftlevel;
	private String geschlecht;
	private String erfahrung;
	private String ernaehrung;
	private String schlafqualitaet;
	private String stress;
	private String doping;
	private String regenerationsfaehigkeit;
}
