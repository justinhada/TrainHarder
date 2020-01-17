package de.justinharder.powerlifting.model.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UebungEintrag
{
	private int id;
	private String name;
	private String uebungsart;
	private String uebungskategorie;
}
