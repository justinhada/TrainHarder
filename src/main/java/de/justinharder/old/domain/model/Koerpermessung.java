package de.justinharder.old.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.attribute.Koerpermasse;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;

//@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Koerpermessung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -6355583837778945437L;

	@NonNull
	@Column(name = "Datum")
	private LocalDate datum;

	@NonNull
	@Embedded
	private Koerpermasse koerpermasse;

	@NonNull
	@Column(name = "Kalorieneinnahme")
	private Integer kalorieneinnahme;

	@NonNull
	@Column(name = "Kalorienverbrauch")
	private Integer kalorienverbrauch;

	public Koerpermessung(
		ID id,
		@NonNull LocalDate datum,
		@NonNull Koerpermasse koerpermasse,
		@NonNull Integer kalorieneinnahme,
		@NonNull Integer kalorienverbrauch)
	{
		super(id);
		this.datum = datum;
		this.koerpermasse = koerpermasse;
		this.kalorieneinnahme = kalorieneinnahme;
		this.kalorienverbrauch = kalorienverbrauch;
	}
}
