package de.justinharder.old.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.attribute.GrunduebungBelastung;
import de.justinharder.old.domain.model.attribute.OberkoerperBelastung;
import de.justinharder.old.domain.model.attribute.UnterkoerperBelastung;
import jakarta.persistence.Embedded;
import lombok.*;

import java.io.Serial;

//@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Belastung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = 5251603922467033680L;

	@NonNull
	@Embedded
	private GrunduebungBelastung grunduebungBelastung;

	@NonNull
	@Embedded
	private OberkoerperBelastung oberkoerperBelastung;

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
