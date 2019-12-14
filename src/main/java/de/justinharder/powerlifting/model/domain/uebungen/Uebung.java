package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import de.justinharder.powerlifting.model.domain.Belastungsfaktor;
import de.justinharder.powerlifting.model.domain.enums.Uebungsart;

public abstract class Uebung
{
	private String name;
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
	private Belastungsfaktor belastungsfaktor;
}
