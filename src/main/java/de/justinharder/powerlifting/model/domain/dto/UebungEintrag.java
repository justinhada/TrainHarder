package de.justinharder.powerlifting.model.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UebungEintrag implements Serializable
{
	private static final long serialVersionUID = -7055200842034977331L;

	private int id;
	private String name;
	private String uebungsart;
	private String uebungskategorie;
}
