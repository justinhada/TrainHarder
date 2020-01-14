package de.justinharder.powerlifting.view.navigation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FehlermeldungSollte
{
	@Test
	@DisplayName("RuntimeException werfen, wenn die ID ungültig ist")
	public void test01()
	{
		final var exception = assertThrows(RuntimeException.class, () -> Fehlermeldung.fromId(10000));

		assertThat(exception.getMessage()).isEqualTo("Die Fehler-ID \"10000\" ist ungültig!");
	}

	@Test
	@DisplayName("die Fehlermeldung mithilfe der ID ermitteln")
	public void test02()
	{
		final var erwartet = Fehlermeldung.DER_BENUTZER_KONNTE_NICHT_GEFUNDEN_WERDEN;

		final var ergebnis = Fehlermeldung.fromId(0);

		assertThat(ergebnis).isEqualTo(erwartet);
	}
}
