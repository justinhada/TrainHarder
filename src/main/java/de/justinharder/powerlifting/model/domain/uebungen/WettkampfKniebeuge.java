package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class WettkampfKniebeuge extends Uebung
{
	private static final long serialVersionUID = 6714217760606177757L;
}
