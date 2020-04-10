package de.justinharder.powerlifting.model.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenutzerEintrag implements Serializable
{
	private static final long serialVersionUID = 2341943957236551490L;

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
