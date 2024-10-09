package de.justinharder.trainharder.api;

import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.justinharder.Testdaten.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@QuarkusTest
class BenutzerRessourceTest
{
	@Inject
	BenutzerRessource sut;

	@Test
	@DisplayName("GET auf /benutzer verarbeiten")
	void test01()
	{
		var ergebnis = sut.findeAlle(new PaginationRequest<>())
			.readEntity(new GenericType<PaginationResponse<GespeicherterBenutzer>>() {});

		assertAll(
			() -> assertThat(ergebnis.getCount()).isEqualTo(4),
			() -> assertThat(ergebnis.getSelf()).isEqualTo(PAGINATION_RESPONSE.getSelf()),
			() -> assertThat(ergebnis.getFirst()).isEqualTo(PAGINATION_RESPONSE.getFirst()),
			() -> assertThat(ergebnis.getPrev()).isEqualTo(PAGINATION_RESPONSE.getPrev()),
			() -> assertThat(ergebnis.getNext()).isEqualTo(PAGINATION_RESPONSE.getNext()),
			() -> assertThat(ergebnis.getLast()).isEqualTo(PAGINATION_RESPONSE.getLast()),
			() -> assertThat(ergebnis.getResults()).containsExactlyInAnyOrder(
				GESPEICHERTER_BENUTZER_1,
				GESPEICHERTER_BENUTZER_2,
				GESPEICHERTER_BENUTZER_3,
				GESPEICHERTER_BENUTZER_4));
	}
}
