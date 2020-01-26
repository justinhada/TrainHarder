# Powerlifting
## by Justin Harder & Eduard Stremel

Weboberfläche zur Verwaltung von Trainingsplänen, individuell für jeden Benutzer.

## Entwicklung

* Nachdem das Projekt geklont wurde: .\gradlew clean cleaneclipse eclipse build

## Glossar

|JPA-Entity|Beschreibung|
|-|-|
|**LFDY**|**L**ift-**F**ast-**D**ie-**Y**oung|
|**Benutzer**|Person mit allerlei Attributen, die Auskunft auf sein aktuelles Kraftlevel und das fahrbare Volumen geben.|
|**Uebung**|Übung, die in den eigenen Trainingsplan implementiert werden kann. Besitzt Attribute, wie bspw. Kategorie oder Art.|
|**Belastungsfaktor**|Jede Übung hat einen Belastungsfaktor. Es werden die Belastungswerte auf die Muskelgruppen verteilt.|
|**Kraftwert**|Mit Kraftwerten können Steigerungen im Training protokolliert werden.|
|**Konstanten**|**KLASSIFIKATION_FRAUEN**: Kraftlevel gemessen an *Körpergewicht* und *SBD-Total*. **KLASSIFIKATION_MAENNER**: Kraftlevel gemessen an *Körpergewicht* & *SBD-Total*. **KRAFTLEVEL_EINTEILUNG**: Alle Raw-Kraftlevel nach [USA Powerlifting](https://www.usapowerlifting.com/wp-content/uploads/2014/01/Raw-Classifications-lb.pdf). **MINIMUM_EFFECTIVE_VOLUME**: Minimale Richtwerte für SBD. **MAXIMUM_RECOVERABLE_VOLUME**: Maximale Richtwerte für SBD. **|