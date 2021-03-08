# Gradle e struttura multi-progetto

Una volta definiti i bounded context, si è proseguito andando a definire
l'architettura di progetto. È stata predisposta la repository di base, dal nome
[PPS-19-ScalaQuest](https://github.com/scalaquest/PPS-19-ScalaQuest), come richiesto da specifiche di PPS. È qui che
risiede il sorgente alla base dei principali moduli.

Si è deciso di utilizzare il tool di build automation **Gradle** per strutturare
il progetto. Pur non essendo pensato primariamente per il linguaggio Scala,
Gradle fornisce supporto per lo stesso.

Si è predisposta una **struttura multi-progetto** il più possibile aderente
all'analisi DDD effettuata. Sono stati definiti i seguenti sotto-progetti:

- **Core**: modulo che va a rappresentare di fatto il bounded context Core. Esso è stato pensato infatti per definire specifiche alla base del
  model, della pipeline di progetto, la definizione dell'API per lo storyteller,
  e l'engine Prolog del gioco per l'interpretazione dei comandi;

- **CLI**: modulo che va a mappare l'elemento CLI del bounded context
  Storyteller Application. Rappresenta una "piattaforma" sulle quali giocare le
  storie, basata su un'implementazione a linea di comando. A livello pratico,
  *CLI* eredita come dipendenza il modulo *Core*, permettendo allo storyteller di
  iniziare a creare storie importando il solo modulo *CLI*.

- **Examples**: sono stati definiti diversi moduli che consistono di fatto in
  delle storie di esempio, giocabili da un'utente finale.

## Strategia basata su convention plugin

Ogni modulo necessita di differenti plugin Gradle per poter funzionare
correttamente. Il dettaglio dei plugin specifici richiesti da ogni modulo viene
trattato nei capitoli successivi.

Di particolare interesse è invece una scelta architetturale che ha permesso di
condividere, tra vari insiemi di sub-projects, plugin e configurazioni comuni. Si
è infatti deciso di sfruttare una strategia fortemente raccomandata da Gradle
nella sua documentazione, basata sui **convention plugin**.

Questa consiste nella definizione di vari plugin custom all'interno della
directory standard `/buildSrc`, ognuno comprendente configurazioni comuni a
più sotto-progetti, inscrivibili in determinate categorie. Una volta definiti, è quindi possibile includere tutte
le configurazioni comuni semplicemente includendo all'interno dei singoli
sotto-progetti i convention plugin richiesti. In particolare:

- **scalaquest.common-scala-conventions**: definisce le configurazioni comuni a
  tutti i sotto-progetti basati su linguaggio Scala (di fatto, tutti i
  sub-project). Questo comprende quindi il plugin `scala` e la sua configurazione,
  `scalatest` e `scoverage` per la gestione dei test, il plugin `spotless` per operazioni di
  lint-formatting del codice, varie opzioni comuni di configurazione del
  compilatore Scala, il plugin `git-sensitive-semantic-versioning` per la gestione dei numeri di versione delle release;

- **scalaquest.libraries-conventions**: definisce le configurazioni comuni a tutti
  i sotto-progetti che vanno a comporre una libreria Scala. Questo a sua
  volta importa il plugin `common-scala-conventions`, rendendo possibile
  configurare i moduli *Core* e *CLI* importando solamente
  `libraries-conventions`. Comprende il plugin `java-library`, la configurazione
  `scoverage` specifica per le librerie, e la configurazione necessaria per la
  pubblicazione su Maven Central.

- **scalaquest.examples-conventions**: definisce le configurazioni comuni a tutti
  i sotto-progetti *Examples*. Questo a sua volta importa il plugin
  `common-scala-conventions`, rendendo possibile configurare gli esempi
  importando solamente `examples-conventions`. Complende il plugin `application`,
  la configurazione di `scoversage` specifica per gli esempi, e la configurazione
  necessaria a rendere gli esempi giocabili da linea di comando.
