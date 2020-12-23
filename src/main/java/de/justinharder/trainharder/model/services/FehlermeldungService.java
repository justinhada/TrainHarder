package de.justinharder.trainharder.model.services;

import de.justinharder.trainharder.model.domain.exceptions.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FehlermeldungService
{
	private static final String EXISTIERT_NICHT = "\" existiert nicht!";

	public static Supplier<AuthentifizierungNichtGefundenException> wirfAuthentifizierungNichtGefundenException(
		@NonNull String text, @NonNull String attribut)
	{
		return () -> new AuthentifizierungNichtGefundenException(
			"Die Authentifizierung mit " + text + " \"" + attribut + EXISTIERT_NICHT);
	}

	public static Supplier<BelastungsfaktorNichtGefundenException> wirfBelastungsfaktorNichtGefundenException(
		@NonNull String text, @NonNull String attribut)
	{
		return () -> new BelastungsfaktorNichtGefundenException(
			"Der Belastungsfaktor mit " + text + " \"" + attribut + EXISTIERT_NICHT);
	}

	public static Supplier<BenutzerNichtGefundenException> wirfBenutzerNichtGefundenException(
		@NonNull String text, @NonNull String attribut)
	{
		return () -> new BenutzerNichtGefundenException(
			"Der Benutzer mit " + text + " \"" + attribut + EXISTIERT_NICHT);
	}

	public static Supplier<KoerpermessungNichtGefundenException> wirfKoerpermessungNichtGefundenException(
		@NonNull String text, @NonNull String attribut)
	{
		return () -> new KoerpermessungNichtGefundenException(
			"Die Koerpermessung mit " + text + " \"" + attribut + EXISTIERT_NICHT);
	}

	public static Supplier<KraftwertNichtGefundenException> wirfKraftwertNichtGefundenException(
		@NonNull String text, @NonNull String attribut)
	{
		return () -> new KraftwertNichtGefundenException(
			"Der Kraftwert mit " + text + " \"" + attribut + EXISTIERT_NICHT);
	}

	public static Supplier<UebungNichtGefundenException> wirfUebungNichtGefundenException(
		@NonNull String text, @NonNull String attribut)
	{
		return () -> new UebungNichtGefundenException(
			"Die Uebung mit " + text + " \"" + attribut + EXISTIERT_NICHT);
	}
}
