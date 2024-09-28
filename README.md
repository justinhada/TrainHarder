# TrainHarder

Weboberfläche zur Verwaltung von Trainingsplänen, individuell für jeden Benutzer.

Root-Projekt mit verschiedenen Klassen, die in allen Unterprojekten genutzt werden.

| Bezeichnung  | Beschreibung |
|--------------|--------------|
| **Benutzer** |              |
| **Login**    |              |

## DietHarder

Sub-Projekt, das sich um die Planung und Umsetzung einer Diät bzw. Aufbauphase kümmert.

| Entity              | Beschreibung |
|---------------------|--------------|
| **Messung**         |              |
| **Ziel**            |              |
| **Umfänge**         |              |
| **Hautfaltendicke** |              |

### EatHarder

| Entity                     | Beschreibung |
|----------------------------|--------------|
| **Mahlzeit**               |              |
| **MahlzeitLebensmittel**   |              |
| **Lebensmittel**           |              |
| **Hersteller**             |              |
| **Nährstoffe**             |              |
| **MakroNährstoffe**        |              |
| **MikroNährstoffe**        |              |
| **Vitamine**               |              |
| **FettlöslicheVitamine**   |              |
| **WasserlöslicheVitamine** |              |
| **Mineralstoffe**          |              |
| **Mengenelemente**         |              |
| **Spurenelemente**         |              |

#### SuppHarder

| Entity         | Beschreibung |
|----------------|--------------|
| **Supplement** |              |

## PlanHarder

### WorkHarder

Sub-Projekt, das sich um das aktive Training kümmert.

| Entity            | Beschreibung |
|-------------------|--------------|
| **Trainingsplan** |              |
| **Übung**         |              |

### PerformHarder
