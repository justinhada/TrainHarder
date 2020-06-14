package de.justinharder.trainharder.view.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KraftwertDto implements Serializable
{
	private static final long serialVersionUID = -5177169492291346152L;

	private String id;
	private int maximum;
	private double koerpergewicht;
	private String datum;
	private String wiederholungen;
}
