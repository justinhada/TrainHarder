package de.justinharder;

import de.justinharder.base.domain.model.attribute.ID;
import de.justinharder.base.domain.services.dto.pagination.PaginationRequest;
import de.justinharder.dietharder.domain.model.Ziel;
import de.justinharder.dietharder.domain.model.attribute.KoerperfettAnteil;
import de.justinharder.dietharder.domain.model.attribute.Koerpergewicht;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.attribute.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Testdaten
{
	public static final PaginationRequest<?> PAGINATION_REQUEST = new PaginationRequest<>();

	public static final Benutzer BENUTZER_1 = new Benutzer(
		new ID("82f4bf94-9f6e-43f7-a6c2-867949e7d4f7"),
		new Nachname("Harder"),
		new Vorname("Justin"),
		Geschlecht.MAENNLICH,
		new Geburtsdatum("1998-12-06"));

	public static final Benutzer BENUTZER_2 = new Benutzer(
		new ID("e5e0a05a-471f-41ff-986e-e230f814f4e0"),
		new Nachname("Harder"),
		new Vorname("Nicole"),
		Geschlecht.WEIBLICH,
		new Geburtsdatum("2007-02-26"));

	public static final Benutzer BENUTZER_3 = new Benutzer(
		new ID("b073864a-1067-4d23-a007-7b44ea76e95f"),
		new Nachname("Harder"),
		new Vorname("Wolfgang"),
		Geschlecht.MAENNLICH,
		new Geburtsdatum("1974-04-14"));

	public static final Benutzer BENUTZER_4 = new Benutzer(
		new ID("2dd931bf-b9f3-441a-8851-e33d1e323827"),
		new Nachname("Harder"),
		new Vorname("Lilia"),
		Geschlecht.WEIBLICH,
		new Geburtsdatum("1978-07-22"));

	public static final Ziel ZIEL_1 = new Ziel(
		new ID("84d996cf-cd6e-4519-b440-a9d8f1b63809"),
		new Datum("01.04.2024"),
		new Koerpergewicht("80"),
		new KoerperfettAnteil("10"),
		BENUTZER_1);

	public static final Ziel ZIEL_2 = new Ziel(
		new ID("a48d295d-447c-4426-9eb1-2f22cbaff770"),
		new Datum("01.06.2024"),
		new Koerpergewicht("75"),
		new KoerperfettAnteil("7"),
		BENUTZER_1);
}
