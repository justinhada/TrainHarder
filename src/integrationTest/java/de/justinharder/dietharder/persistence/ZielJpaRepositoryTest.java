package de.justinharder.dietharder.persistence;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
@DisplayName("ZielJpaRepository sollte")
class ZielJpaRepositoryTest
{
	@Inject
	ZielJpaRepository sut;

	@Test
	@DisplayName("null validieren")
	void test01()
	{
		assertAll(
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlle(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlle(null, PAGINATION_REQUEST)),
			() -> assertThrows(NullPointerException.class, () -> sut.findeAlle(BENUTZER_1.getId(), null)),
			() -> assertThrows(NullPointerException.class, () -> sut.zaehleAlle(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.finde(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.speichere(null)),
			() -> assertThrows(NullPointerException.class, () -> sut.loesche(null)));
	}

	@Test
	@DisplayName("alle finden")
	void test02()
	{
		assertThat(sut.findeAlle(PAGINATION_REQUEST)).containsExactlyInAnyOrder(ZIEL_1, ZIEL_2);
	}

	@Test
	@DisplayName("alle zählen")
	void test03()
	{
		assertThat(sut.zaehleAlle()).isEqualTo(2);
	}

	@Test
	@DisplayName("alle mit BenutzerID finden")
	void test04()
	{
		assertAll(
			() -> assertThat(sut.findeAlle(BENUTZER_1.getId(), PAGINATION_REQUEST))
				.containsExactlyInAnyOrder(ZIEL_1, ZIEL_2),
			() -> assertThat(sut.findeAlle(BENUTZER_2.getId(), PAGINATION_REQUEST)).isEmpty(),
			() -> assertThat(sut.findeAlle(BENUTZER_3.getId(), PAGINATION_REQUEST)).isEmpty(),
			() -> assertThat(sut.findeAlle(BENUTZER_4.getId(), PAGINATION_REQUEST)).isEmpty());
	}

	@Test
	@DisplayName("alle mit BenutzerID zählen")
	void test05()
	{
		assertAll(
			() -> assertThat(sut.zaehleAlle(BENUTZER_1.getId())).isEqualTo(2),
			() -> assertThat(sut.zaehleAlle(BENUTZER_2.getId())).isEqualTo(0),
			() -> assertThat(sut.zaehleAlle(BENUTZER_3.getId())).isEqualTo(0),
			() -> assertThat(sut.zaehleAlle(BENUTZER_4.getId())).isEqualTo(0));
	}

}
