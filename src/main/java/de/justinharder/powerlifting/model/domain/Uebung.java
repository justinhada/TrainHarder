package de.justinharder.powerlifting.model.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Uebung extends Entitaet
{
	private static final long serialVersionUID = -452069613203642245L;

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
	@Enumerated(EnumType.STRING)
	private Uebungskategorie uebungskategorie;
	@OneToOne(fetch = FetchType.LAZY)
	private Belastungsfaktor belastungsfaktor;

	public Uebung(final String name, final Uebungsart uebungsart, final Uebungskategorie uebungskategorie,
		final Belastungsfaktor belastungsfaktor)
	{
		this.name = name;
		this.uebungsart = uebungsart;
		this.uebungskategorie = uebungskategorie;
		this.belastungsfaktor = belastungsfaktor;
	}
}
