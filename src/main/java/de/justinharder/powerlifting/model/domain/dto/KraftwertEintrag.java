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
	private String benutzerVorname;
	private String benutzerNachname;
	private String uebungName;
	private int maximum;
}