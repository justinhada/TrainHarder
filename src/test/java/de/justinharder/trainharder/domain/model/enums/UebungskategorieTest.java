package de.justinharder.trainharder.domain.model.enums;

import de.justinharder.trainharder.domain.model.exceptions.EnumException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.trainharder.domain.model.enums.Uebungskategorie.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Uebungskategorie sollte")
class UebungskategorieTest
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
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_BRUST")).isEqualTo(ASSISTENZ_BRUST),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_CORE")).isEqualTo(ASSISTENZ_CORE),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_GLUTEUS")).isEqualTo(ASSISTENZ_GLUTEUS),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_RUECKEN")).isEqualTo(ASSISTENZ_RUECKEN),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_SCHULTER")).isEqualTo(ASSISTENZ_SCHULTER),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_TRIZEPS")).isEqualTo(ASSISTENZ_TRIZEPS),
			() -> assertThat(Uebungskategorie.zuWert("ASSISTENZ_UNTERARM")).isEqualTo(ASSISTENZ_UNTERARM),
			() -> assertThat(Uebungskategorie.zuWert("BANKDRUECKEN_VARIATION")).isEqualTo(BANKDRUECKEN_VARIATION),
			() -> assertThat(Uebungskategorie.zuWert("KNIEBEUGE_VARIATION")).isEqualTo(KNIEBEUGE_VARIATION),
			() -> assertThat(Uebungskategorie.zuWert("KREUZHEBEN_VARIATION")).isEqualTo(KREUZHEBEN_VARIATION),
			() -> assertThat(Uebungskategorie.zuWert("UEBERKOPFDRUECKEN")).isEqualTo(UEBERKOPFDRUECKEN),
			() -> assertThat(Uebungskategorie.zuWert("WETTKAMPF_BANKDRUECKEN")).isEqualTo(WETTKAMPF_BANKDRUECKEN),
			() -> assertThat(Uebungskategorie.zuWert("WETTKAMPF_KNIEBEUGE")).isEqualTo(WETTKAMPF_KNIEBEUGE),
			() -> assertThat(Uebungskategorie.zuWert("WETTKAMPF_KREUZHEBEN")).isEqualTo(WETTKAMPF_KREUZHEBEN));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> Uebungskategorie.zuWert(null));
	}
}
