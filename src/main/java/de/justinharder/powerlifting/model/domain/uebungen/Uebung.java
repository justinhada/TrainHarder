package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.Entitaet;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public abstract class Uebung extends Entitaet
{
	private static final long serialVersionUID = -452069613203642245L;

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
	@OneToOne(mappedBy = "uebung", fetch = FetchType.LAZY)
	private Belastungsfaktor belastungsfaktor;
}
