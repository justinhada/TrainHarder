package de.justinharder.dietharder.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import jakarta.persistence.Embedded;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serial;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Datensatz extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5906970570219300025L;

	@Setter
	@NonNull
	@Embedded
	protected Datum datum;

	@Setter
	@NonNull
	@Embedded
	protected Koerpergewicht koerpergewicht;

	@Setter
	@NonNull
	@Embedded
	protected KoerperfettAnteil koerperfettAnteil;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "BenutzerID", nullable = false)
	protected Benutzer benutzer;

	protected Datensatz(
		ID id,
		@NonNull Datum datum,
		@NonNull Koerpergewicht koerpergewicht,
		@NonNull KoerperfettAnteil koerperfettAnteil,
		@NonNull Benutzer benutzer)
	{
		super(id);
		this.datum = datum;
		this.koerpergewicht = koerpergewicht;
		this.koerperfettAnteil = koerperfettAnteil;
		this.benutzer = benutzer;
	}
}
