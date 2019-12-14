package de.justinharder.powerlifting.model.domain;

import de.justinharder.powerlifting.model.domain.uebungen.Uebung;
import lombok.Data;

@Data
public class Kraftwert
{
	private Uebung uebung;
	private int maximum;
}
