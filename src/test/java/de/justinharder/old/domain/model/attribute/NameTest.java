package de.justinharder.old.domain.model.attribute;

import de.justinharder.old.domain.model.attribute.Name;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Name sollte")
class NameTest
{
	private static final String VORNAME = "Justin";
	private static final String NACHNAME = "Harder";

	private Name sut;

	@BeforeEach
	void setup()
	{
		sut = new Name(VORNAME, NACHNAME);
	}

	@Test
	@DisplayName("einen AllArgsConstructor und Getter besitzen")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME));
	}

	@Test
	@DisplayName("einen NoArgsConstructor und Setter besitzen")
	void test02()
	{
		sut = new Name()
			.setVorname(VORNAME)
			.setNachname(NACHNAME);

		assertAll(
			() -> assertThat(sut.getVorname()).isEqualTo(VORNAME),
			() -> assertThat(sut.getNachname()).isEqualTo(NACHNAME));
	}

	@Test
	@DisplayName("sich vergleichen")
	void test03()
	{
		EqualsVerifier.forClass(Name.class)
			.suppress(Warning.NULL_FIELDS)
			.verify();
	}

	@Test
	@DisplayName("eine toString()-Methode haben")
	void test04()
	{
		var erwartet = "Name(vorname=Justin, nachname=Harder)";

		assertThat(sut).hasToString(erwartet);
	}

	@Test
	@DisplayName("null validieren")
	void test05()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> new Name(null, NACHNAME)),
			() -> assertThrows(NullPointerException.class, () -> new Name(VORNAME, null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setVorname(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.setNachname(null)));
	}
}
