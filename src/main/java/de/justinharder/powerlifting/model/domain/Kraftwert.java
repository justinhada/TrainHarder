package de.justinharder.powerlifting.model.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import de.justinharder.powerlifting.model.Entitaet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kraftwert extends Entitaet
{
	private static final long serialVersionUID = -1203157961547955006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Uebung uebung;
	private int maximum;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Benutzer benutzer;

	public Kraftwert(final Uebung uebung, final int maximum, final Benutzer benutzer)
	{
		this.uebung = uebung;
		this.maximum = maximum;
		this.benutzer = benutzer;
	}
}
