package de.justinharder.trainharder.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.trainharder.domain.model.attribute.GrunduebungBelastung;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.trainharder.domain.model.attribute.OberkoerperBelastung;
import de.justinharder.trainharder.domain.model.attribute.UnterkoerperBelastung;
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
