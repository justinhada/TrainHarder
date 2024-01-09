package de.justinharder.old.domain.model;

import de.justinharder.base.domain.model.Entitaet;
import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.old.domain.model.enums.Uebungsart;
import de.justinharder.old.domain.model.enums.Uebungskategorie;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Uebung extends Entitaet
{
	@Serial
	private static final long serialVersionUID = -452069613203642245L;

	@Setter
	@NonNull
	@Column(name = "Bezeichnung", unique = true)
	private String bezeichnung;

	@Setter
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Uebungsart")
	private Uebungsart uebungsart;

	@Setter
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(name = "Uebungskategorie")
	private Uebungskategorie uebungskategorie;

	@Setter
	@NonNull
	@OneToOne(optional = false)
	@JoinColumn(name = "BelastungID", nullable = false)
	private Belastung belastung;

	public Uebung(
		ID id,
		@NonNull String bezeichnung,
		@NonNull Uebungsart uebungsart,
		@NonNull Uebungskategorie uebungskategorie,
		@NonNull Belastung belastung)
	{
		super(id);
		this.bezeichnung = bezeichnung;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastung = belastung;
	}
}
