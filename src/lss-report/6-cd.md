# Continuous Delivery e Deployment {#sec:chap6}

Tra gli obiettivi di progetto è stato posto fin da subito quello di realizzare
dei processi efficaci di Continuous Delivery e Deployment degli artefatti.
Nonostante ciò, a differenza di quanto fatto per i workflow di CI, quelli di CD
non sono stati realizzati immediatamente. Questo in quanto nei primi Sprint di
progetto non si aveva del codice abbastanza stabile da poterne ricavare degli
artefatti concretamente utilizzabili. I workflow di Continuous Delivery e
Deployment sono stati predisposti durante il passaggio da GitHubFlow a GitFlow,
e hanno subìto diverse modifiche lungo lo sviluppo del progetto.

## Il workflow Release

Il primo e più importante workflow per il deploy degli artefatti è denominato
**Release**. Questo viene lanciato ogniqualvolta viene chiusa con successo
(ovvero generando un evento di merge) una pull request in direzione del `main`,
a partire da un branch `release/X.Y.Z`. Tale workflow:

1. Inferisce il nome del tag da associare alla release, a partire del nome del
   branch di provenienza, e crea un annotated tag in corrispondenza del commit
   di merge;

2. Genera una release GitHub nell'apposita sezione Releases, ponendo all'interno
   di questa gli artefatti necessari (jar per le librerie `core` e `cli`, delle
   distribution in formato `zip` e `tar.gz` per gli esempi, il file
   `README.md`);

3. In parallelo al punto 2, effettua una pubblicazione su Maven Central;

4. Una volta completati i punti 2 e 3, viene generata una pull request dal
   `main`, diretta al branch `dev`. Ciò permette di integrare nel branch di
   sviluppo le eventuali modifiche occorse all'interno dei branch
   `release/X.Y.Z`. Su di questi infatti si è delle volte agito con delle
   modifiche minori.

5. Una volta completati i punti 2 e 3, vengono generati ScalaDoc e report di
   coverage per i moduli `core` e `cli`, e resi disponibili nello spazio web del
   progetto.

## Il workflow Prerelease

Oltre al workflow di release principale, si è predisposto un secondo workflow,
che va a creare delle release GitHub ogniqualvolta viene effettuato un push nel
branch `dev`. Tali release vengono marcate come non perfettamente stabili,
appunto con il marcatore prerelease, da cui il nome del workflow. Particolarità
di questa routine è che non necessita di definire manualmente il nome della
release: questa viene generata in automatico, tramite il plugin
[`git-sensitive-semantic-versioning`](https://github.com/DanySK/git-sensitive-semantic-versioning-gradle-plugin/blob/master/src/main/kotlin/org/danilopianini/gradle/gitsemver/GitSemVer.kt),
a partire dall'ultimo tag disponibile.

Ricapitolando, il workflow:

1. Inferisce il nome del tag da associare alla prerelease, in modo del tutto
   automatico, tramite il plugin Gradle
   [`git-sensitive-semantic-versioning`](https://github.com/DanySK/git-sensitive-semantic-versioning-gradle-plugin/blob/master/src/main/kotlin/org/danilopianini/gradle/gitsemver/GitSemVer.kt).
   Un task personalizzato permette di estrre il numero di versione e salvarlo su
   un file; il contenuto viene quindi prelevato, salvato come variabile
   d'ambiente, e viene creato un annotated tag in corrispondenza del commit di
   merge;

2. Genera una release GitHub nell'apposita sezione Releases, con il flag
   prerelease abilitato, ponendo all'interno di questa gli artefatti necessari
   (jar per le librerie `core` e `cli`, delle distribution in formato `zip` e
   `tar.gz` per gli esempi, il file `README.md`);

3. In parallelo al punto 2, effettua una pubblicazione su Maven Central.

## Maven Central

Si è deciso di dedicare una sezione a parte per approfondire il procedimento che
ha portato alla pubblicazione delle librerie di progetto sul repository pubblico
**Maven Central**.

La predisposizione del delivery in una repository pubblica è stata prevista sin
dall'inizio nel progetto. Anche solo la scelta di porre tutte le repository di
progetto sotto una stessa organizzazione è stata eseguita in quest'ottica, così
da poter ottenere il dominio `scalaquest.github.io`, e utilizzare
`io.github.scalaquest` quale groupId per il rilascio.

L'attuazione di questo obiettivo però è stato demandato a fasi più avanzate di
progetto, individuando tale possiblità come requisito da valutare in corso
d'opera.

Ci si è iniziati a muovere in tal senso durante la migrazione da GitHubFlow a
GitFlow. In questo frangente si è aperto un **ticket presso Sonatype**,
richiedendo la possibilità di pubblicare sotto il groupId
`io.github.scalaquest`.

Tale processo è stato completato abbastanza velocemente. Lo sviluppo di questa
soluzione ha però subito a questo punto una fase d'arresto, legata
principalmente alla complessità nella configurazione del plugin Gradle
`maven-publish` e alla firma degli artefatti, in particolare in ambiente CI. I
workflow di release sono quindi stati inizialmente realizzati senza la
possibilità di pubblicare su Maven.

Solo più avanti, con il raggiungimento di una relativa stabilità di progetto, si
è deciso di approfondire e portare a termine questo task, concludendo la
configurazione del plugin e dei workflow collegati.

Nella configurazione, una volta pubblicati gli artefatti, saranno i membri del
team a concludere la release, effettuando il **close** o il **drop** della
release, direttamente dall'interfaccia web **Nexus Repository Manager**, previa
autenticazione.

Il team tiene a sottolineare che pubblicare su una repository pubblica popolare
quale Maven Central è stata una grande fonte di soddisfazione per i propri
membri.
