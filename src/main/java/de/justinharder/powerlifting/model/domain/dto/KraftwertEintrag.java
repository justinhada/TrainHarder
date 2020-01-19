package de.justinharder.powerlifting.model.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KraftwertEintrag
{
	private int id;
	private int maximum;
	private int koerpergewicht;
	private String datum;
	private String wiederholungen;
}
