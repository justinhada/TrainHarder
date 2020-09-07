# TrainHarder
> by Justin Harder

Weboberfläche zur Verwaltung von Trainingsplänen, individuell für jeden Benutzer.

![Build](https://github.com/justinhada/TrainHarder/workflows/Build/badge.svg) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=justinhada_TrainHarder&metric=alert_status)](https://sonarcloud.io/dashboard?id=justinhada_TrainHarder)

## Entwicklung

* Nachdem das Projekt geklont wurde: `.\gradlew clean cleaneclipse eclipse build`

## Passwortrichtlinien

Bei der Registrierung eines neuen Benutzers, muss ein Passwort vergeben werden.
Dieses muss sich an folgende Richtlinien halten:
* Mindestlänge von 12 Zeichen
* Mindestens ein Großbuchstabe, ein Kleinbuchstabe, eine Zahl von 0-9
* und eines der folgenden Zeichen: $, %, &, ?, #, _

## Wie wird das Passwort in der Datenbank gespeichert?

1. Generierung eines 128Bit-Salts.
2. Speicherung des Salts.
3. Hashing mithilfe von **PBKDF2WithHmacSHA1** und generiertem Salt.
4. Speicherung des Hashs.

## Glossar

|JPA-Entity|Beschreibung|
|-|-|
|**Benutzer**|Person mit allerlei Attributen, die Auskunft auf sein aktuelles Kraftlevel und das fahrbare Volumen geben.|
|**Authentifizierung**|Eine Authentifizierung gehört zu einem Benutzer. Hier werden Mail, Benutzername und Passwort gespeichert.|
|**Koerpermessung**|Benutzer können Körpermessungen anlegen. Hier werden viele Werte zum Körper gespeichert.|
|**Uebung**|Übung, die in den eigenen Trainingsplan implementiert werden kann. Besitzt Attribute, wie bspw. Kategorie oder Art.|
|**Belastungsfaktor**|Belastungsfaktor gehört zu einer Übung. Es werden die Belastungswerte auf die Muskelgruppen verteilt.|
|**Kraftwert**|Mit Kraftwerten können Steigerungen im Training protokolliert werden.|
|**Konstanten**|**KLASSIFIKATION_FRAUEN**: Kraftlevel gemessen an *Körpergewicht* und *SBD-Total*. **KLASSIFIKATION_MAENNER**: Kraftlevel gemessen an *Körpergewicht* & *SBD-Total*. **KRAFTLEVEL_EINTEILUNG**: Alle Raw-Kraftlevel nach [USA Powerlifting](https://www.usapowerlifting.com/wp-content/uploads/2014/01/Raw-Classifications-lb.pdf). **MINIMUM_EFFECTIVE_VOLUME**: Minimale Richtwerte für SBD. **MAXIMUM_RECOVERABLE_VOLUME**: Maximale Richtwerte für SBD.|

### USA-Powerlifting Lifter Classification – Raw Women
||G|E|W|I|C|H|T|(in KG)|
|-|-|-|-|-|-|-|-|-|
|**Kraftlevel**|bis 43|bis 47|bis 52|bis 57|bis 63|bis 72|bis 84|über 84|
|**ELITE** (Top 2.5%)|275|310|334|359|382|407|440|479|
|**MASTER** (Top 5%)|264|297|318|342|363|386|417|453|
|**CLASS 1** (Top 15%)|241|270|288|308|326|346|373|403|
|**CLASS 2** (Top 25%)|229|254|270|288|304|322|346|374|
|**CLASS 3** (Top 50%)|204|224|236|250|264|278|297|318|
|**CLASS 4** (Top 75%)|178|193|202|214|222|232|246|263|
|**CLASS 5** (Top 90%)|156|166|172|180|185|192|202|212|

### USA-Powerlifting Lifter Classification – Raw Men
||G|E|W|I|C|H|T|(in|KG)|
|-|-|-|-|-|-|-|-|-|-|
|**Kraftlevel**|bis 53|bis 59|bis 66|bis 74|bis 83|bis 93|bis 105|bis 120|über 120|
|**ELITE** (Top 2.5%)|456|520|567|616|666|715|765|819|877|
|**MASTER** (Top 5%)|432|493|538|585|633|680|729|779|835|
|**CLASS 1** (Top 15%)|385|441|482|525|569|613|656|704|755|
|**CLASS 2** (Top 25%)|356|410|449|489|531|572|614|658|706|
|**CLASS 3** (Top 50%)|304|352|387|423|460|497|534|574|617|
|**CLASS 4** (Top 75%)|251|294|325|357|390|422|455|490|528|
|**CLASS 5** (Top 90%)|205|242|269|297|326|355|383|414|448|
