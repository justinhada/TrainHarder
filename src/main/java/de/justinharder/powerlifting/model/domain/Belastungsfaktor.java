package de.justinharder.powerlifting.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import de.justinharder.powerlifting.model.domain.uebungen.Uebung;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class Belastungsfaktor extends Entitaet
{
	private static final long serialVersionUID = 5251603922467033680L;

	@Id
	@GeneratedValue
	private int id;
	@OneToOne
	@JoinColumn(nullable = false)
	private Uebung uebung;
	private double squat;
	private double benchpress;
	private double deadlift;
	private double triceps;
	private double chest;
	private double core;
	private double back;
	private double biceps;
	private double glutes;
	private double quads;
	private double hamstrings;
	private double shoulder;
}
