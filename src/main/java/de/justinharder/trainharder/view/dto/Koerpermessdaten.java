package de.justinharder.trainharder.view.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Koerpermessdaten
{
	@Size(min = 10, max = 10, message = "Ungültiges Datum!")
	@FormParam(value = "datum")
	private String datum;
	@FormParam(value = "koerpergroesse")
	private int koerpergroesse;
	@FormParam(value = "koerpergewicht")
	private double koerpergewicht;
	@FormParam(value = "koerperfettAnteil")
	private double koerperfettAnteil;
	@FormParam(value = "kalorieneinnahme")
	private int kalorieneinnahme;
	@FormParam(value = "kalorienverbrauch")
	private int kalorienverbrauch;
}
