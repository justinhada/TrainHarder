package de.justinharder.powerlifting.model.domain.dto;

import de.justinharder.powerlifting.model.domain.enums.Uebungsart;
import de.justinharder.powerlifting.model.domain.enums.Uebungskategorie;
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
	private Uebungsart uebungsart;
	private Uebungskategorie uebungskategorie;
}
