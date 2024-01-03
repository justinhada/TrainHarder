package de.justinharder.trainharder.domain.services.berechnung;

import de.justinharder.trainharder.domain.model.Authentifizierung;
import de.justinharder.trainharder.domain.model.Benutzer;
import de.justinharder.trainharder.domain.model.Koerpermessung;
import de.justinharder.trainharder.domain.model.Kraftwert;
import de.justinharder.trainharder.domain.model.embeddables.Benutzerangabe;
import de.justinharder.trainharder.domain.model.embeddables.Koerpermasse;
import de.justinharder.trainharder.domain.model.embeddables.Name;
import de.justinharder.trainharder.domain.model.embeddables.ID;
import de.justinharder.trainharder.domain.model.enums.*;
import de.justinharder.trainharder.domain.repository.KraftwertRepository;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static de.justinharder.trainharder.setup.Testdaten.PASSWORT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KraftlevelErmittlungSollte
{
	private KraftlevelErmittlung sut;

	private KraftwertRepository kraftwertRepository;

	@BeforeEach
	public void setup()
	{
		kraftwertRepository = mock(KraftwertRepository.class);

		sut = new KraftlevelErmittlung(kraftwertRepository);
	}

	private Benutzer erstelleBenutzer(BigDecimal koerpergewicht, Geschlecht geschlecht)
	{
		return new Benutzer(
			new ID(),
			new Name("Max", "Mustermann"),
			LocalDate.of(1976, 12, 6),
			new Benutzerangabe(
				geschlecht,
				Erfahrung.BEGINNER,
				Ernaehrung.GUT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.GUT),
			new Authentifizierung(new ID(), "max@mustermann.de", "mustermann", PASSWORT))
			.fuegeKoerpermessungHinzu(new Koerpermessung(
				new ID(),
				LocalDate.now(),
				new Koerpermasse(new BigDecimal(178), koerpergewicht, new BigDecimal(24)),
				2500,
				2900));
	}

	private void fuegeKraftwerteHinzu(Benutzer benutzer, BigDecimal kniebeuge, BigDecimal bankdruecken,
		BigDecimal kreuzheben)
	{

		when(kraftwertRepository.findeAlleMitBenutzer(benutzer.getId())).thenReturn(List.of(
			new Kraftwert(
				new ID(),
				LocalDate.now(),
				kniebeuge,
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
				benutzer),
			new Kraftwert(
				new ID(),
				LocalDate.now(),
				bankdruecken,
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
				benutzer),
			new Kraftwert(
				new ID(),
				LocalDate.now(),
				kreuzheben,
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN,
				benutzer)));
	}

	private Benutzer erstelleMann(BigDecimal koerpergewicht, BigDecimal kniebeuge, BigDecimal bankdruecken,
		BigDecimal kreuzheben)
	{
		var benutzer = erstelleBenutzer(koerpergewicht, Geschlecht.MAENNLICH);
		fuegeKraftwerteHinzu(benutzer, kniebeuge, bankdruecken, kreuzheben);
		return benutzer;
	}

	private Benutzer erstelleFrau(BigDecimal koerpergewicht, BigDecimal kniebeuge, BigDecimal bankdruecken,
		BigDecimal kreuzheben)
	{
		var benutzer = erstelleBenutzer(koerpergewicht, Geschlecht.WEIBLICH);
		fuegeKraftwerteHinzu(benutzer, kniebeuge, bankdruecken, kreuzheben);
		return benutzer;
	}

	@Test
	@DisplayName("das Kraftlevel für einen Mann ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(110), new BigDecimal(95),
				new BigDecimal(140)))).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(150), new BigDecimal(110),
				new BigDecimal(170)))).isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(170), new BigDecimal(130),
				new BigDecimal(200)))).isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(200), new BigDecimal(145),
				new BigDecimal(230)))).isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(220), new BigDecimal(160),
				new BigDecimal(250)))).isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(240), new BigDecimal(170),
				new BigDecimal(270)))).isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(90), new BigDecimal(260), new BigDecimal(185),
				new BigDecimal(290)))).isEqualTo(Kraftlevel.ELITE),
			() -> assertThat(sut.ermittle(erstelleMann(new BigDecimal(130), new BigDecimal(300), new BigDecimal(200),
				new BigDecimal(350)))).isEqualTo(Kraftlevel.MASTER));
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(100), new BigDecimal(50),
				new BigDecimal(110)))).isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(110), new BigDecimal(60),
				new BigDecimal(120)))).isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(120), new BigDecimal(70),
				new BigDecimal(130)))).isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(140), new BigDecimal(90),
				new BigDecimal(150)))).isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(150), new BigDecimal(100),
				new BigDecimal(160)))).isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(170), new BigDecimal(110),
				new BigDecimal(180)))).isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(sut.ermittle(erstelleFrau(new BigDecimal(90), new BigDecimal(200), new BigDecimal(120),
				new BigDecimal(220)))).isEqualTo(Kraftlevel.ELITE));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> sut.ermittle(null));
	}
}
