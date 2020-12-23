package de.justinharder.trainharder.model.domain;

import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import lombok.Getter;
import lombok.NonNull;

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
		@NonNull Primaerschluessel primaerschluessel,
		double squat,
		double benchpress,
		double deadlift,
		double triceps,
		double chest,
		double core,
		double back,
		double biceps,
		double glutes,
		double quads,
		double hamstrings,
		double shoulder)
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

	public Belastungsfaktor setPrimaerschluessel(@NonNull Primaerschluessel primaerschluessel)
	{
		this.primaerschluessel = primaerschluessel;
		return this;
	}

	public Belastungsfaktor setSquat(double squat)
	{
		this.squat = squat;
		return this;
	}

	public Belastungsfaktor setBenchpress(double benchpress)
	{
		this.benchpress = benchpress;
		return this;
	}

	public Belastungsfaktor setDeadlift(double deadlift)
	{
		this.deadlift = deadlift;
		return this;
	}

	public Belastungsfaktor setTriceps(double triceps)
	{
		this.triceps = triceps;
		return this;
	}

	public Belastungsfaktor setChest(double chest)
	{
		this.chest = chest;
		return this;
	}

	public Belastungsfaktor setCore(double core)
	{
		this.core = core;
		return this;
	}

	public Belastungsfaktor setBack(double back)
	{
		this.back = back;
		return this;
	}

	public Belastungsfaktor setBiceps(double biceps)
	{
		this.biceps = biceps;
		return this;
	}

	public Belastungsfaktor setGlutes(double glutes)
	{
		this.glutes = glutes;
		return this;
	}

	public Belastungsfaktor setQuads(double quads)
	{
		this.quads = quads;
		return this;
	}

	public Belastungsfaktor setHamstrings(double hamstrings)
	{
		this.hamstrings = hamstrings;
		return this;
	}

	public Belastungsfaktor setShoulder(double shoulder)
	{
		this.shoulder = shoulder;
		return this;
	}

	public Belastungsfaktor setUebung(@NonNull Uebung uebung)
	{
		this.uebung = uebung;
		return this;
	}

	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
}
