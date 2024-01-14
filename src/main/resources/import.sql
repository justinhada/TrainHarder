INSERT INTO Authentifizierung(ID, Mail, Benutzername, Salt, PasswortHash, Aktiv, ResetUUID)
VALUES ('76cda8bb-09f2-45bb-93c8-f35fc6241d08', 'mail@justinharder.de', 'harder', 'lhwMFKf4DTBEXnWG7tXvhA==',
        'mNMZ8W5m2jf5TtSBnNfB/w==', 0, 'db748cb0-a773-4e99-bbff-46344732bf92');

INSERT INTO Authentifizierung(ID, Mail, Benutzername, Salt, PasswortHash, Aktiv)
VALUES ('1056e84b-e201-4eae-9b18-9bb8ecc638ab', 'mail@eduard.de', 'eduard', 'lhwMFKf4DTBEXnWG7tXvhA==',
        'mNMZ8W5m2jf5TtSBnNfB/w==', 1);

INSERT INTO Benutzer(ID, Vorname, Nachname, Geburtsdatum, Kraftlevel, Geschlecht, Erfahrung, Ernaehrung,
                     Schlafqualitaet, Stress, Doping, Regenerationsfaehigkeit, AuthentifizierungID)
VALUES ('7c4a9562-a165-40b9-a645-4cf2cee9a1e3', 'Justin', 'Harder', '1998-12-06', 'CLASS_5', 'MAENNLICH', 'BEGINNER',
        'GUT', 'GUT', 'MITTELMAESSIG', 'NEIN', 'GUT', '76cda8bb-09f2-45bb-93c8-f35fc6241d08'),
       ('9341a123-6b75-4b89-9454-d096b9ed2bd2', 'Eduard', 'Stremel', '1998-11-09', 'CLASS_4', 'MAENNLICH',
        'FORTGESCHRITTEN', 'SCHLECHT', 'SCHLECHT', 'NIEDRIG', 'JA', 'PERFEKT', '1056e84b-e201-4eae-9b18-9bb8ecc638ab');

INSERT INTO Belastung(ID, Squat, Benchpress, Deadlift, Triceps, Chest, Core, Back, Biceps, Shoulder, Glutes, Quads,
                      Hamstrings)
VALUES ('48bb15b2-0fb5-4f0d-a390-1ed768fedb45', 0.0, 1.0, 0.0, 0.7, 1.0, 0.0, 0.0, 0.0, 0.1, 0.0, 0.0, 0.0),
       ('8f077e9c-a896-4779-af4f-c04d95daa319', 1.0, 0.0, 0.0, 0.0, 0.0, 0.3, 0.2, 0.0, 0.0, 1.0, 1.0, 0.5),
       ('7145b3e7-f3ef-4ab7-a739-587a28c63b90', 0.0, 0.0, 1.0, 0.0, 0.0, 0.3, 0.5, 0.0, 0.0, 0.5, 0.3, 0.5);

INSERT INTO Uebung(ID, Bezeichnung, Uebungsart, Uebungskategorie, BelastungID)
VALUES ('628c016b-4084-4f37-af31-472f421ecbdc', 'Wettkampfbankdrücken (pausiert)', 'GRUNDUEBUNG',
        'WETTKAMPF_BANKDRUECKEN', '48bb15b2-0fb5-4f0d-a390-1ed768fedb45'),
       ('ae57e405-b464-4726-b6da-0169ab04e259', 'Lowbar-Kniebeuge', 'GRUNDUEBUNG', 'WETTKAMPF_KNIEBEUGE',
        '8f077e9c-a896-4779-af4f-c04d95daa319'),
       ('96559ebd-48b0-4eef-970a-4ff263348145', 'Konventionelles Kreuzheben', 'GRUNDUEBUNG', 'WETTKAMPF_KREUZHEBEN',
        '7145b3e7-f3ef-4ab7-a739-587a28c63b90');

INSERT INTO Kraftwert(ID, Datum, Gewicht, Wiederholungen, UebungID, BenutzerID)
VALUES ('37022a20-0225-49a8-a47b-7f4555e75580', '2023-01-01', 100.00, 'ONE_REP_MAX',
        '628c016b-4084-4f37-af31-472f421ecbdc', '7c4a9562-a165-40b9-a645-4cf2cee9a1e3'),
       ('01782719-4ecb-4ebd-bdf1-4d8eb1d22701', '2023-01-01', 150.00, 'ONE_REP_MAX',
        'ae57e405-b464-4726-b6da-0169ab04e259', '7c4a9562-a165-40b9-a645-4cf2cee9a1e3'),
       ('6a6508bd-315d-4c09-b70a-33ad52987781', '2023-01-01', 200.00, 'ONE_REP_MAX',
        '96559ebd-48b0-4eef-970a-4ff263348145', '7c4a9562-a165-40b9-a645-4cf2cee9a1e3');

INSERT INTO Koerpermessung(ID, Datum, Koerpergroesse, Koerpergewicht, KoerperfettAnteil, FettfreiesKoerpergewicht,
                           BodyMassIndex, FatFreeMassIndex, Kalorieneinnahme, Kalorienverbrauch, BenutzerID)
VALUES ('fce7ffb5-7aa3-49db-bfe4-7b52a0e234d0', '2020-07-29', 178.00, 90.00, 25.00, 67.50, 28.40, 21.40, 2500, 2900,
        '7c4a9562-a165-40b9-a645-4cf2cee9a1e3'),
       ('36903e76-d888-4994-bfa2-d1f373d12ed7', '2020-07-29', 182.00, 64.00, 9.00, 58.24, 19.3, 17.50, 2500, 2900,
        '9341a123-6b75-4b89-9454-d096b9ed2bd2');

INSERT INTO Messung(ID, Datum, Koerpergewicht, KoerperfettAnteil, BenutzerID)
VALUES ('c6ee66cf-bc61-4539-b5da-832a0d57b62e', '2024-01-05', 88.50, 15.60, '7c4a9562-a165-40b9-a645-4cf2cee9a1e3'),
       ('53ec77cb-849e-497d-9864-8f2c88618326', '2024-01-06', 89.05, 15.80, '7c4a9562-a165-40b9-a645-4cf2cee9a1e3');
