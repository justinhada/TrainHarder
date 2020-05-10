package de.justinharder.trainharder.model.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KraftwertEintrag implements Serializable
{
	private static final long serialVersionUID = -5177169492291346152L;

	private int id;
	private int maximum;
	private double koerpergewicht;
	private String datum;
	private String wiederholungen;
}
