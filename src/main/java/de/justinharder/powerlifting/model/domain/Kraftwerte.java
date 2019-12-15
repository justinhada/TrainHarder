package de.justinharder.powerlifting.model.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Kraftwerte implements Iterable<Kraftwert>
{
	private final List<Kraftwert> kraftwerte = new ArrayList<>();

	@Override
	public Iterator<Kraftwert> iterator()
	{
		return kraftwerte.iterator();
	}

	public void fuegeKraftwertHinzu(final Kraftwert kraftwert)
	{
		kraftwerte.add(kraftwert);
	}
}
