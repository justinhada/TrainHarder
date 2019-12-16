package de.justinharder.powerlifting.model;

import java.io.Serializable;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Entitaet implements Serializable
{
	private static final long serialVersionUID = 790786817201854580L;

	@Id
	private String id;
}
