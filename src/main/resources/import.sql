INSERT INTO Benutzer(ID, Nachname, Vorname, Geschlecht, Geburtsdatum)
VALUES ('82f4bf94-9f6e-43f7-a6c2-867949e7d4f7', 'Harder', 'Justin', 'MAENNLICH', '1998-12-06'),
       ('e5e0a05a-471f-41ff-986e-e230f814f4e0', 'Harder', 'Nicole', 'WEIBLICH', '2007-02-26'),
       ('b073864a-1067-4d23-a007-7b44ea76e95f', 'Harder', 'Wolfgang', 'MAENNLICH', '1974-04-14'),
       ('2dd931bf-b9f3-441a-8851-e33d1e323827', 'Harder', 'Lilia', 'WEIBLICH', '1978-07-22');

INSERT INTO Login(ID, EMailAdresse, Benutzername, Salt, Passwort, BenutzerID)
VALUES ('4c33559d-2a1d-44ad-a750-a6cb47ed7bbe', 'mail@justinharder.de', 'harder', 'lhwMFKf4DTBEXnWG7tXvhA==',
        'mNMZ8W5m2jf5TtSBnNfB/w==', '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7'),
       ('ad957af6-4907-42ff-a528-f29958903e83', 'mail@nicoleharder.de', 'nicole', 'lhwMFKf4DTBEXnWG7tXvhA==',
        'mNMZ8W5m2jf5TtSBnNfB/w==', 'e5e0a05a-471f-41ff-986e-e230f814f4e0');

INSERT INTO Messung(ID, Datum, Koerpergewicht, KoerperfettAnteil, BenutzerID)
VALUES ('c6ee66cf-bc61-4539-b5da-832a0d57b62e', '2024-01-05', 88.50, 15.60, '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7'),
       ('53ec77cb-849e-497d-9864-8f2c88618326', '2024-01-06', 89.05, 15.80, '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7');

INSERT INTO Ziel(ID, Datum, Koerpergewicht, KoerperfettAnteil, BenutzerID)
VALUES ('84d996cf-cd6e-4519-b440-a9d8f1b63809', '2024-04-01', 80.00, 10.00, '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7'),
       ('a48d295d-447c-4426-9eb1-2f22cbaff770', '2024-06-01', 75.00, 7.00, '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7');

INSERT INTO Hautfaltendicke(ID, Datum, Brustfalte, Bauchfalte, Beinfalte, Hueftfalte, Achselfalte, Trizepsfalte,
                            Rueckenfalte, KoerperfettAnteil, BenutzerID)
VALUES ('497539ef-af2b-452f-a9d8-55fbbfe8b7c0', '2023-07-22', 11.00, 25.00, 19.00, 32.00, 10.00, 13.00, 14.00, 17.00,
        '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7'),
       ('20bbfdb2-6bf4-4a46-9016-cdb2777a8c26', '2024-01-05', 9.00, 15.00, 13.00, 23.00, 6.00, 12.00, 14.00, 14.00,
        '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7');

INSERT INTO Umfaenge(ID, Datum, HalsUmfang, SchulterUmfang, BrustRueckenUmfang, LinkerOberarmUmfang,
                     RechterOberarmUmfang, LinkerUnterarmUmfang, RechterUnterarmUmfang, BauchUmfang, HueftUmfang,
                     LinkerOberschenkelUmfang, RechterOberschenkelUmfang, LinkerUnterschenkelUmfang,
                     RechterUnterschenkelUmfang, BenutzerID)
VALUES ('6d195e7a-c309-4c03-a870-6841ccafbea9', '2022-04-03', 52.00, 120.00, 106.00, 39.00, 39.50, 31.00, 31.00, 93.00,
        98.00, 66.00, 66.00, 40.00, 39.50, '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7'),
       ('bcd1d15d-39b3-4eb1-87a2-c8fa47ed3bd2', '2022-07-15', 50.00, 118.50, 106.50, 37.80, 38.50, 30.00, 30.00, 86.00,
        95.00, 62.00, 62.00, 39.90, 38.00, '82f4bf94-9f6e-43f7-a6c2-867949e7d4f7');
