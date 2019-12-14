package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class WettkampfKniebeuge extends Uebung
{
	@Id
	private String id;
	@Enumerated(EnumType.STRING)
	private Uebungsart uebungsart;
}
