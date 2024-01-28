package de.justinharder.dietharder.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.hautfaltendicke.*;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hautfaltendicke extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -5894261490425170965L;

	@Setter
	@NonNull
	@Embedded
	private Datum datum;

	@Setter
	@NonNull
	@Embedded
	private Brustfalte brustfalte;

	@Setter
	@NonNull
	@Embedded
	private Bauchfalte bauchfalte;

	@Setter
	@NonNull
	@Embedded
	private Beinfalte beinfalte;

	@Setter
	@NonNull
	@Embedded
	private Hueftfalte hueftfalte;

	@Setter
	@NonNull
	@Embedded
	private Achselfalte achselfalte;

	@Setter
	@NonNull
	@Embedded
	private Trizepsfalte trizepsfalte;

	@Setter
	@NonNull
	@Embedded
	private Rueckenfalte rueckenfalte;

	@Setter
	@NonNull
	@Embedded
	private KoerperfettAnteil koerperfettAnteil;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	private Benutzer benutzer;

	public Hautfaltendicke(
		ID id,
		@NonNull Datum datum,
		@NonNull Brustfalte brustfalte,
		@NonNull Bauchfalte bauchfalte,
		@NonNull Beinfalte beinfalte,
		@NonNull Hueftfalte hueftfalte,
		@NonNull Achselfalte achselfalte,
		@NonNull Trizepsfalte trizepsfalte,
		@NonNull Rueckenfalte rueckenfalte,
		@NonNull KoerperfettAnteil koerperfettAnteil,
		@NonNull Benutzer benutzer)
	{
		super(id);
		this.datum = datum;
		this.brustfalte = brustfalte;
		this.bauchfalte = bauchfalte;
		this.beinfalte = beinfalte;
		this.hueftfalte = hueftfalte;
		this.achselfalte = achselfalte;
		this.trizepsfalte = trizepsfalte;
		this.rueckenfalte = rueckenfalte;
		this.koerperfettAnteil = koerperfettAnteil;
		this.benutzer = benutzer;
	}
}
