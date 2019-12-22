package de.justinharder.powerlifting.model.domain.uebungen;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class AssistenzBrust extends Uebung
{
	private static final long serialVersionUID = 4259167965184741284L;
}
