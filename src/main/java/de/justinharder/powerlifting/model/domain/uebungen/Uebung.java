package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import lombok.Data;

@Data
public abstract class Uebung
{
	@Id
	private String id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
	private Belastungsfaktor belastungsfaktor;
}
