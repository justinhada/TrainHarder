package de.justinharder.trainharder.domain.model;

import de.justinharder.trainharder.domain.model.embeddables.GrunduebungBelastung;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.embeddables.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.embeddables.UnterkoerperBelastung;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Belastung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5251603922467033680L;

	@Setter
	@NonNull
	@Embedded
	private GrunduebungBelastung grunduebungBelastung;

	@Setter
	@NonNull
	@Embedded
	private OberkoerperBelastung oberkoerperBelastung;

	@Setter
	@NonNull
	@Embedded
	private UnterkoerperBelastung unterkoerperBelastung;

	public Belastung(
		ID id,
		@NonNull GrunduebungBelastung grunduebungBelastung,
		@NonNull OberkoerperBelastung oberkoerperBelastung,
		@NonNull UnterkoerperBelastung unterkoerperBelastung)
	{
		super(id);
		this.grunduebungBelastung = grunduebungBelastung;
		this.oberkoerperBelastung = oberkoerperBelastung;
		this.unterkoerperBelastung = unterkoerperBelastung;
	}
}
