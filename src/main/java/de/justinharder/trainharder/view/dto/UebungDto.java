package de.justinharder.trainharder.view.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UebungDto implements Serializable
{
	private static final long serialVersionUID = -7055200842034977331L;

	private String primaerschluessel;
	private String name;
	private String uebungsart;
	private String uebungskategorie;
	private BelastungsfaktorDto belastungsfaktor;
}
