package de.justinharder.powerlifting.model.domain;

import de.justinharder.powerlifting.model.domain.uebungen.Uebung;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Kraftwert
{
	private Uebung uebung;
	private int maximum;
}
