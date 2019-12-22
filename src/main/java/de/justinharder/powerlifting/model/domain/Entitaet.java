package de.justinharder.powerlifting.model.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public abstract class Entitaet implements Serializable
{
	private static final long serialVersionUID = 790786817201854580L;

	public abstract int getId();
}
