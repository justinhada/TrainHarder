package de.justinharder.trainharder.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Belastungsfaktor")
public class Belastungsfaktor extends Entitaet
{
	private static final long serialVersionUID = 5251603922467033680L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "Squat", nullable = false)
	private double squat;
	@Column(name = "Benchpress", nullable = false)
	private double benchpress;
	@Column(name = "Deadlift", nullable = false)
	private double deadlift;
	@Column(name = "Triceps", nullable = false)
	private double triceps;
	@Column(name = "Chest", nullable = false)
	private double chest;
	@Column(name = "Core", nullable = false)
	private double core;
	@Column(name = "Back", nullable = false)
	private double back;
	@Column(name = "Biceps", nullable = false)
	private double biceps;
	@Column(name = "Glutes", nullable = false)
	private double glutes;
	@Column(name = "Quads", nullable = false)
	private double quads;
	@Column(name = "Hamstrings", nullable = false)
	private double hamstrings;
	@Column(name = "Shoulder", nullable = false)
	private double shoulder;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "UebungID", nullable = false)
	private Uebung uebung;

	public Belastungsfaktor(
		final double squat,
		final double benchpress,
		final double deadlift,
		final double triceps,
		final double chest,
		final double core,
		final double back,
		final double biceps,
		final double glutes,
		final double quads,
		final double hamstrings,
		final double shoulder)
	{
		this.squat = squat;
		this.benchpress = benchpress;
		this.deadlift = deadlift;
		this.triceps = triceps;
		this.chest = chest;
		this.core = core;
		this.back = back;
		this.biceps = biceps;
		this.glutes = glutes;
		this.quads = quads;
		this.hamstrings = hamstrings;
		this.shoulder = shoulder;
	}
}
