package de.justinharder.powerlifting.model.domain;

import java.util.Iterator;
import java.util.List;

import lombok.Data;

@Data
public class Kraftwerte implements Iterable<Kraftwert>
{
	private List<Kraftwert> kraftwerte;

	@Override
	public Iterator<Kraftwert> iterator()
	{
		return kraftwerte.iterator();
	}
}
