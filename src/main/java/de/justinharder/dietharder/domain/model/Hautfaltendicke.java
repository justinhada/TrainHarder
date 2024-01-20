package de.justinharder.dietharder.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.hautfaltendicke.*;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.Datum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@RequiredArgsConstructor
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
	protected KoerperfettAnteil koerperfettAnteil;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BenutzerID", nullable = false)
	protected Benutzer benutzer;
}
