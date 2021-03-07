# Gradle e struttura multi-progetto

Una volta definiti i bounded context, si è proseguito andando a definire
l'architettura di progetto. È stata predisposta la repository di base di
progetto, dal nome
[PPS-19-ScalaQuest](https://github.com/scalaquest/PPS-19-ScalaQuest). È qui che
risiede il sorgente alla base dei principali moduli.

Si è deciso di utilizzare il tool di build automation **Gradle** per strutturare
il progetto. Pur non essendo pensato primariamente per il linguaggio Scala,
Gradle fornisce supporto per lo stesso.

Si è predisposta una **struttura multi-progetto** il più possibile aderente
all'analisi DDD effettuata. Sono stati definiti i seguenti sotto-progetti:

- **core**: modulo che va a rappresentare di fatto i bounded context core di
  progetto. Esso è stato pensato infatti per definire specifiche alla base del
  model, della pipeline di progetto, la definizione del'API per lo storyteller,
  e il motore semantico del gioco per l'interpretazione dei comandi;
- **cli**: modulo che va a mappare il bounded context CLI. Rappresenta una
  "piattaforma" sulle quali giocare le storie, basata su un'implementazione a
  linea di comando. A livello pratico, CLI eredita come dipendenza il modulo
  core, permettendo allo storyteller di iniziare a creare storie importando il
  solo modulo cli.
- **examples**: sono stati definiti diversi moduli che consistono di fatto in
  delle storie di esempio, giocabili da un'utente finale.

## Strategia basata su convention plugin

Ogni modulo necessita di differenti plugin Gradle per poter funzionare
correttamente. Il dettaglio dei plugin specifici richiesti da ogni modulo viene
trattato nei capitoli successivi.

Di particolare interesse è invece una scelta architetturale che ha permesso di
condividere tra vari insiemi di sub-projects plugin e configurazioni comuni. Si
è infatti deciso di sfruttare una strategia fortemente raccomandata da Gradle
nella sua documentazione, basata sui **convention plugins**.

Questa consiste nella definizione di vari plugin custom all'interno della
directory standard `buildSrc`, ognuno comprendente configurazioni comuni a
insiemi di sub-project. Una volta definiti, è quindi possibile includere tutte
le configurazioni comuni semplicemente includendo all'interno dei singoli
sub-project i convention plugin richiesti. In particolare:

- `scalaquest.common-scala-conventions.gradle.kts`: definisce le configurazioni
  comuni a tutti i sotto-progetti basati su linguaggio Scala (di fatto, tutti i
  sub-project). Questo comprende quindi i plugin Scala e la sua configurazione,
  scalatest e scoverage per la gestione dei test, un plugin per operazioni di
  lint-formatting del codice, varie opzioni comuni di configurazione del
  compilatore Scala, un plugin per il semantic versioning basato su Git;

- `scalaquest.libraries-conventions.gradle.kts`: definisce le configurazioni
  comuni a tutti i sotto-progetti che vanno a comporre una libreria Scala. Il
  plugin a sua volta importa il plugin `common-scala-conventions`, rendendo
  possibile configurare i moduli `core` e `cli` importando solamente
  `libraries-conventions`. Comprende il plugin java-library, la configurazione
  Scoverage specifica per le librerie, e la configurazione necessaria per la
  pubblicazione su Maven Central.

- `scalaquest.examples-conventions.gradle.kts`: definisce le configurazioni
  comuni a tutti i sotto-progetti esempio. Il plugin a sua volta importa il
  plugin `common-scala-conventions`, rendendo possibile configurare gli esempi
  importando solamente `examples-conventions`. Complende il plugin application,
  la configurazione di Scoversage specifica per gli esempi, e la configurazione
  necessaria a rendere gli esempi giocabili da linea di comando.
