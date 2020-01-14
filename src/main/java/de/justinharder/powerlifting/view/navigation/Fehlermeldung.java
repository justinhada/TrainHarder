package de.justinharder.powerlifting.view.navigation;

import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum Fehlermeldung
{
	DER_BENUTZER_KONNTE_NICHT_GEFUNDEN_WERDEN(0, "Leider konnte bei deiner Suche kein Benutzer gefunden werden!"),
	PARAMETER_NICHT_VORHANDEN(1,
		"Ein URL-Parameter konnte nicht ermittelt werden. Das kann z.B. durch einen Fehler beim "
			+ "Kopieren der URL passieren. Bitte versuchen Sie es nochmal "
			+ "oder wenden Sie sich an den Administrator, falls der Fehler erneut auftritt.");

	private final int id;
	private final String nachricht;

	private Fehlermeldung(final int id, final String nachricht)
	{
		this.id = id;
		this.nachricht = nachricht;
	}

	public static Fehlermeldung fromId(final int id)
	{
		return Stream.of(Fehlermeldung.values())
			.filter(fehlermeldung -> fehlermeldung.getId() == id)
			.findAny()
			.orElseThrow(() -> new RuntimeException("Die Fehler-ID \"" + id + "\" ist ungültig!"));
	}
}
