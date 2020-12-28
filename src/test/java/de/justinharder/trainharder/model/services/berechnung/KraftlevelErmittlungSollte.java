package de.justinharder.trainharder.model.services.berechnung;

import de.justinharder.trainharder.model.domain.Benutzer;
import de.justinharder.trainharder.model.domain.Koerpermessung;
import de.justinharder.trainharder.model.domain.Kraftwert;
import de.justinharder.trainharder.model.domain.embeddables.Benutzerangabe;
import de.justinharder.trainharder.model.domain.embeddables.Koerpermasse;
import de.justinharder.trainharder.model.domain.embeddables.Name;
import de.justinharder.trainharder.model.domain.embeddables.Primaerschluessel;
import de.justinharder.trainharder.model.domain.enums.*;
import de.justinharder.trainharder.setup.Testdaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KraftlevelErmittlungSollte
{
	private KraftlevelErmittlung sut;

	@BeforeEach
	public void setup()
	{
		sut = new KraftlevelErmittlung();
	}

	private Benutzer erstelleBenutzer(
		BigDecimal koerpergewicht,
		Geschlecht geschlecht,
		BigDecimal kniebeuge,
		BigDecimal bankdruecken,
		BigDecimal kreuzheben)
	{
		var benutzer = new Benutzer();
		return benutzer
			.fuegeKoerpermessungHinzu(new Koerpermessung(
				new Primaerschluessel(),
				LocalDate.now(),
				new Koerpermasse(new BigDecimal(178), koerpergewicht, new BigDecimal(24)),
				2500,
				2900,
				benutzer))
			.setName(new Name("M.", "Muster"))
			.setGeburtsdatum(LocalDate.of(1976, 12, 6))
			.setBenutzerangabe(new Benutzerangabe(
				geschlecht,
				Erfahrung.BEGINNER,
				Ernaehrung.GUT,
				Schlafqualitaet.GUT,
				Stress.MITTELMAESSIG,
				Doping.NEIN,
				Regenerationsfaehigkeit.GUT))
			.fuegeKraftwertHinzu(new Kraftwert(
				new Primaerschluessel(),
				kniebeuge,
				benutzer.getKoerpergewicht(),
				LocalDate.now(),
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_LOWBAR_KNIEBEUGE,
				benutzer))
			.fuegeKraftwertHinzu(new Kraftwert(
				new Primaerschluessel(),
				bankdruecken,
				benutzer.getKoerpergewicht(),
				LocalDate.now(),
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_WETTKAMPFBANKDRUECKEN,
				benutzer))
			.fuegeKraftwertHinzu(new Kraftwert(
				new Primaerschluessel(),
				kreuzheben,
				benutzer.getKoerpergewicht(),
				LocalDate.now(),
				Wiederholungen.ONE_REP_MAX,
				Testdaten.UEBUNG_KONVENTIONELLES_KREUZHEBEN,
				benutzer));
	}

	private Benutzer erstelleMann(BigDecimal koerpergewicht, BigDecimal kniebeuge, BigDecimal bankdruecken,
		BigDecimal kreuzheben)
	{
		return erstelleBenutzer(koerpergewicht, Geschlecht.MAENNLICH, kniebeuge, bankdruecken, kreuzheben);
	}

	private Benutzer erstelleFrau(BigDecimal koerpergewicht, BigDecimal kniebeuge, BigDecimal bankdruecken,
		BigDecimal kreuzheben)
	{
		return erstelleBenutzer(koerpergewicht, Geschlecht.WEIBLICH, kniebeuge, bankdruecken, kreuzheben);
	}

	@Test
	@DisplayName("das Kraftlevel für einen Mann ermitteln")
	void test01()
	{
		assertAll(
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(110), new BigDecimal(95), new BigDecimal(140))))
				.isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(150), new BigDecimal(110), new BigDecimal(170))))
				.isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(170), new BigDecimal(130), new BigDecimal(200))))
				.isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(200), new BigDecimal(145), new BigDecimal(230))))
				.isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(220), new BigDecimal(160), new BigDecimal(250))))
				.isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(240), new BigDecimal(170), new BigDecimal(270))))
				.isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(90), new BigDecimal(260), new BigDecimal(185), new BigDecimal(290))))
				.isEqualTo(Kraftlevel.ELITE),
			() -> assertThat(sut.ermittle(
				erstelleMann(new BigDecimal(130), new BigDecimal(300), new BigDecimal(200), new BigDecimal(350))))
				.isEqualTo(Kraftlevel.MASTER));
	}

	@Test
	@DisplayName("das Kraftlevel für eine Frau ermitteln")
	void test02()
	{
		assertAll(
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(100), new BigDecimal(50), new BigDecimal(110))))
				.isEqualTo(Kraftlevel.CLASS_5),
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(110), new BigDecimal(60), new BigDecimal(120))))
				.isEqualTo(Kraftlevel.CLASS_4),
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(120), new BigDecimal(70), new BigDecimal(130))))
				.isEqualTo(Kraftlevel.CLASS_3),
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(140), new BigDecimal(90), new BigDecimal(150))))
				.isEqualTo(Kraftlevel.CLASS_2),
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(150), new BigDecimal(100), new BigDecimal(160))))
				.isEqualTo(Kraftlevel.CLASS_1),
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(170), new BigDecimal(110), new BigDecimal(180))))
				.isEqualTo(Kraftlevel.MASTER),
			() -> assertThat(sut.ermittle(
				erstelleFrau(new BigDecimal(90), new BigDecimal(200), new BigDecimal(120), new BigDecimal(220))))
				.isEqualTo(Kraftlevel.ELITE));
	}

	@Test
	@DisplayName("null validieren")
	void test03()
	{
		assertThrows(NullPointerException.class, () -> sut.ermittle(null));
	}
}
