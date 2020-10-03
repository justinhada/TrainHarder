package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "Belastungsfaktor")
public class Belastungsfaktor extends Entitaet
{
	private static final long serialVersionUID = 5251603922467033680L;

	@EmbeddedId
	@Column(name = "ID")
	private Primaerschluessel primaerschluessel;
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

	public Belastungsfaktor()
	{}

	public Belastungsfaktor(
		final Primaerschluessel primaerschluessel,
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
		this.primaerschluessel = primaerschluessel;
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

	public Belastungsfaktor setPrimaerschluessel(final Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Belastungsfaktor setSquat(final double squat)
	{
		this.squat = squat;
		return this;
	}

	public Belastungsfaktor setBenchpress(final double benchpress)
	{
		this.benchpress = benchpress;
		return this;
	}

	public Belastungsfaktor setDeadlift(final double deadlift)
	{
		this.deadlift = deadlift;
		return this;
	}

	public Belastungsfaktor setTriceps(final double triceps)
	{
		this.triceps = triceps;
		return this;
	}

	public Belastungsfaktor setChest(final double chest)
	{
		this.chest = chest;
		return this;
	}

	public Belastungsfaktor setCore(final double core)
	{
		this.core = core;
		return this;
	}

	public Belastungsfaktor setBack(final double back)
	{
		this.back = back;
		return this;
	}

	public Belastungsfaktor setBiceps(final double biceps)
	{
		this.biceps = biceps;
		return this;
	}

	public Belastungsfaktor setGlutes(final double glutes)
	{
		this.glutes = glutes;
		return this;
	}

	public Belastungsfaktor setQuads(final double quads)
	{
		this.quads = quads;
		return this;
	}

	public Belastungsfaktor setHamstrings(final double hamstrings)
	{
		this.hamstrings = hamstrings;
		return this;
	}

	public Belastungsfaktor setShoulder(final double shoulder)
	{
		this.shoulder = shoulder;
		return this;
	}

	public Belastungsfaktor setUebung(final Uebung uebung)
	{
		this.uebung = uebung;
		return this;
	}

	@Override
	public boolean equals(final Object obj)
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
