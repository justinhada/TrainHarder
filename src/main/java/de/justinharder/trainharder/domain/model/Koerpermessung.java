package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.Koerpermasse;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serial;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Koerpermessung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -6355583837778945437L;

	@Setter
	@NonNull
	@Column(name = "Datum")
	private LocalDate datum;

	@Setter
	@NonNull
	@Embedded
	private Koerpermasse koerpermasse;

	@Setter
	@NonNull
	@Column(name = "Kalorieneinnahme")
	private Integer kalorieneinnahme;

	@Setter
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
