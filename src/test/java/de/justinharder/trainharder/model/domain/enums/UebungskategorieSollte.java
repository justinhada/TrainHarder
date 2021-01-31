package de.justinharder.trainharder.model.domain.enums;

import de.justinharder.trainharder.model.domain.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UebungskategorieSollte
{
	@Test
	@DisplayName("EnumException werfen, wenn der Wert nicht existiert")
	void test01()
	{
		assertThrows(EnumException.class, () -> Uebungskategorie.zuWert("UNGUELTIG"));
	}

	@Test
	@DisplayName("die Uebungskategorie zu Wert ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_BRUST")).isEqualTo(Uebungskategorie.ASSISTENZ_BRUST),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_CORE")).isEqualTo(Uebungskategorie.ASSISTENZ_CORE),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_GLUTEUS")).isEqualTo(Uebungskategorie.ASSISTENZ_GLUTEUS),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_RUECKEN")).isEqualTo(Uebungskategorie.ASSISTENZ_RUECKEN),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_SCHULTER")).isEqualTo(Uebungskategorie.ASSISTENZ_SCHULTER),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_TRIZEPS")).isEqualTo(Uebungskategorie.ASSISTENZ_TRIZEPS),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_UNTERARM")).isEqualTo(Uebungskategorie.ASSISTENZ_UNTERARM),
			() -> assertThat(Uebungskategorie.zuWert("BANKDRUECKEN_VARIATION")).isEqualTo(Uebungskategorie.BANKDRUECKEN_VARIATION),
			() -> assertThat(Uebungskategorie.zuWert("KNIEBEUGE_VARIATION")).isEqualTo(Uebungskategorie.KNIEBEUGE_VARIATION),
			() -> assertThat(Uebungskategorie.zuWert("KREUZHEBEN_VARIATION")).isEqualTo(Uebungskategorie.KREUZHEBEN_VARIATION),
			() -> assertThat(Uebungskategorie.zuWert("UEBERKOPFDRUECKEN")).isEqualTo(Uebungskategorie.UEBERKOPFDRUECKEN),
			() -> assertThat(Uebungskategorie.zuWert("WETTKAMPF_BANKDRUECKEN")).isEqualTo(Uebungskategorie.WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(Uebungskategorie.zuWert("WETTKAMPF_KREUZHEBEN")).isEqualTo(Uebungskategorie.WETTKAMPF_KREUZHEBEN),
			() -> assertThat(Uebungskategorie.zuWert("WETTKAMPF_KREUZHEBEN")).isEqualTo(Uebungskategorie.WETTKAMPF_KREUZHEBEN));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Uebungskategorie.zuWert(null));
	}
}