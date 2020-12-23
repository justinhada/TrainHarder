package de.justinharder.trainharder.model.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FehlermeldungServiceSollte
{
	@Test
	@DisplayName("null valdidieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfAuthentifizierungNichtGefundenException(null, "attribut")),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfAuthentifizierungNichtGefundenException("text", null)),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfBelastungsfaktorNichtGefundenException(null, "attribut")),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfBelastungsfaktorNichtGefundenException("text", null)),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfBenutzerNichtGefundenException(null, "attribut")),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfBenutzerNichtGefundenException("text", null)),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfKoerpermessungNichtGefundenException(null, "attribut")),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfKoerpermessungNichtGefundenException("text", null)),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfKraftwertNichtGefundenException(null, "attribut")),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfKraftwertNichtGefundenException("text", null)),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfUebungNichtGefundenException(null, "attribut")),
			() -> assertThrows(NullPointerException.class,
				() -> FehlermeldungService.wirfUebungNichtGefundenException("text", null)));
	}
}
