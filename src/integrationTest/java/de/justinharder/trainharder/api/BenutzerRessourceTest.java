package de.justinharder.trainharder.api;

import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.base.domain.services.dto.pagination.PaginationResponse;
import de.justinharder.trainharder.domain.services.dto.benutzer.GespeicherterBenutzer;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@QuarkusTest
class BenutzerRessourceTest
{
	private static final GespeicherterBenutzer GESPEICHERTER_BENUTZER_1 = new GespeicherterBenutzer(
		"82f4bf94-9f6e-43f7-a6c2-867949e7d4f7",
		"Justin",
		"Harder");

	private static final GespeicherterBenutzer GESPEICHERTER_BENUTZER_2 = new GespeicherterBenutzer(
		"e5e0a05a-471f-41ff-986e-e230f814f4e0",
		"Nicole",
		"Harder");

	private static final GespeicherterBenutzer GESPEICHERTER_BENUTZER_3 = new GespeicherterBenutzer(
		"b073864a-1067-4d23-a007-7b44ea76e95f",
		"Wolfgang",
		"Harder");

	private static final GespeicherterBenutzer GESPEICHERTER_BENUTZER_4 = new GespeicherterBenutzer(
		"2dd931bf-b9f3-441a-8851-e33d1e323827",
		"Lilia",
		"Harder");

	private static final PaginationResponse<GespeicherterBenutzer> PAGINATION_RESPONSE = new PaginationResponse<>(
		4,
		List.of(GESPEICHERTER_BENUTZER_1, GESPEICHERTER_BENUTZER_2, GESPEICHERTER_BENUTZER_3, GESPEICHERTER_BENUTZER_4))
		.setSelf("http://localhost:8080/benutzer?page=1&page_size=10")
		.setFirst("http://localhost:8080/benutzer?page=1&page_size=10")
		.setPrev(null)
		.setNext(null)
		.setLast("http://localhost:8080/benutzer?page=1&page_size=10");

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
