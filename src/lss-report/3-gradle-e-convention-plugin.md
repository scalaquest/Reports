# Gradle multiprogetto e convention plugin

<!-- Come si è strutturata la repo principale di progetto, cosa sono
i convention plugin e come sono stati utilizzati (panoramica generale; il
focus sui plugin contenuti dentro di loro, tipo maiflai scalatest, maven publish
sonarqube, scoverage ecc. va fatta nei rispettivi capitoli successivi) -->

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

Ogni modulo necessita di differenti plugin Gradle per poter funzionare
correttamente. Il dettaglio dei plugin specifici richiesti da ogni modulo viene
trattato nei capitoli successivi.

Di particolare interesse è invece una scelata architetturale che ha permesso di
condividere tra vari insiemi di sub-projects plugin e configurazioni comuni. Si
è infatti deciso di sfruttare una strategia fortemente raccomandata da Gradle
nella sua documentazione, denominata **convention plugins**. Questa consiste ...
