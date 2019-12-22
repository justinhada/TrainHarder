package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class WettkampfKreuzheben extends Uebung
{
	private static final long serialVersionUID = -5036136566386794940L;
}
