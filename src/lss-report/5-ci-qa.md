# Continuous Integration, Quality Assurance {#sec:chap6}

Particolare sforzo è stato posto nel porre in atto workflow efficaci e
automatizzati, in grado di garantire la qualità del codice, e la Contiuous
Integration. Per la realizzazione di questi, si è utilizzato il tool di CI
GitHub Actions, in parte per la prononda integrazione con GitHub, e in parte a
causa delle
[recenti modifiche al piano di pricing in Travis CI](https://blog.travis-ci.com/2020-11-02-travis-ci-new-billing).
Questi a loro volta sfruttano delle funzionalità integrate all'interno del
progetto grazie al tool di build automation Gradle.

## Qualità del codice Gradle

Uno dei primissimi accorgimenti posti in atto nel progetto ha riguardato dei
controlli di qualità posti in atto sul codice Kotlin dei convention plugin
stessi, e nei file `build.gradle.kts` dei vari sub-project. Lo scopo era quello
di innalzare la qualità, prima ancora dell codebase Scala vera e propria, della
stessa struttura Gradle a contorno del progetto.

A tal scopo, è stato abilitato il plugin **detekt**, un linter per Kotlin, posto
in modalità strict: in questo modo, la build gradle fallisce nel caso in cui il
codice Kotlin non rispetti determinati requisiti qualitativi, riportati in
maniera dichiarativa all'interno del file `buildSrc/config/detekt.yml`.

Inoltre, per facilitare l'aggiornamento di dipendenze e plugin, è stato adottato
il plugin **refreshVersions**, che consente di estrapolare le versioni di
dipendenze e plugin Gradle in un file separato `versions.properties`,
permettendo l'aggiornamento automatizzato delle stesse.

A tal proposito, a livello di organizzazione è stato definito un bot, denominato
**[dependabot](https://github.com/scalaquest/Dependabot)** (nome ispirato al
sistema di GitHub per l'aggiornamento delle dipendenze). Questa altro non è che
una semplice repository con un workflow schedulato, eseguito automaticamente
ogni notte, per rilevare all'interno delle varie repository di progetto
eventuali dipendenze non aggiornate, e generando automaticamente una pull
request nella quale si va ad aggiornare tale dipendenza. Tale bot non fa altro
che sfruttare il bot esistente [UpGradle](https://github.com/DanySK/upgradle),
configurandolo appositamente per agire all'interno dell'organizzazione.

## Framework di test e soglie di coverage

I test sono portati avanti tramite il framework **ScalaTest**, seguendo lo stile
di test **WordSpec**. Integrare ScalaTest all'interno del progetto non si è
rivelato banale. A seguito di varie ricerche, si è deciso di integrarli tramite
il plugin **ScalaTest di Maiflai**, un plugin che integra e configura in maniera
pressoché trasparente ScalaTest, basato su jUnit 5.

In aggiunta a questo, è stato utilizzato un secondo framework di test, per
rendere possibile testare il modulo `cli`. Basandosi infatti questo sulla
libreria funzionale **ZIO**, il test dello stesso può essere effettuato solo con
un framework apposito basato sempre su jUnit, denomitato **zio-test-junit**.
L'omonima dipendenza è stata quindi aggiunta al `build.gradle.kts` dello
specifico sub-project.

Infine, si è reso necessario trovare un modo per poter gestire i controlli di
coverage. È infatti noto che Jacoco, uno dei tool più diffusi per i controlli di
coverage su JVM, mal si adatta ai controlli su sorgente Scala. Jacoco opera
infatti a livello bytecode, andando a coprire del codice autogenerato da Scala,
e che può portare a stime di coverage del tutto sballate. Lo stato dell'arte per
la messa in atto di controlli di coverage Scala con Gradle passa per l'utilizzo
di plugin dedicati che tengono conto di queste caratteristiche, come **Scoverage
di Maiflai**. Questo permette di generare, tra gli altri, report di coverage in
formato html, sia in forma aggregata che per i singoli sottoprogetti (task
`:aggregateReportScoverage` e `:reportScoverage`), oltre ad esporre un task
`:scoverageCheck`, che permette di far fallire la build in presenza di coverage
più bassa di una determinata soglia. Si è quindi installato nel progetto questo
plugin, andando anche a configurare una soglia di coverage mandatoria del 75%
per i moduli `core` e `cli`.

## Lint e code styling

Particolare attenzione è stata posta anche alla qualità del codice e allo stile
dello stesso, definendo una serie di constraint atti ad innalzare la coesione
stilistica del codice Scala tra le varie sezioni del progetto.

Sono presenti molteplici alternative in grado di gestire funzionalità di linting
e styling di codice Scala tramite Gradle. Si è deciso a tal scopo di utilizzare
il plugin **spotless**: questo aggiunge al progetto vari task per lo styling
automatico del codice (`:spotlessApply`) e per il check dello stesso
(`:spotlessCheck`), supportando al contempo molteplici linguaggi di
programmazione tramite tecniche differenti. Per Scala, Spotless sfrutta
internamente `scalafmt`, un tool per lo styling del codice Scala. Le regole di
styling applicate sono accessibili in un formato dichiarativo all'interno del
file `.scalafmt.conf`.

## SonarCloud

Un ulteriore strumento posto in atto per innalzare la qualità del codice e
certificarla è **SonarCloud**. Questo tool permette di porre in atto controlli
automatizzati sul codice, estraendo varie metriche qualitative riguardo la
codebase, legate a mantenibilità, coverage, debito tecnico, duplicazione e molto
altro. Lo strumento fornisce inoltre una dashboard pubblica che ne raccoglie le
principali metriche, accessibile
[a questo link](https://sonarcloud.io/dashboard?id=scalaquest_PPS-19-ScalaQuest).

Di particolare rilevanza per il progetto è stata la funzionalità **quality
gate**: SonarCloud integra infatti un bot, che ad ogni push all'interno di una
pull request in direzione di branch stabili, effettua un controllo di CI,
fallendo nel caso in cui le metriche rilevate non superino delle soglie
preimpostate.

Allo scopo di configurare correttamente SonarCloud, si è reso necessario
aggiungere un plugin al progetto, denominato **Sonarqube**. Questo rappresenta
uno strumento cosiddetto di "scanner" per SonarCloud, andando ad estrarre in
maniera più mirata le metriche. Il plugin fornisce il task `:sonarqube`, che va
eseguito in CI nel momento in cui si voglia eseguire un controllo di quality
gate.

## Il workflow CI.yml

Allo scopo di porre in atto la maggior parte dei controlli citati in precedenza,
si è reso necessario definire un apposito workflow GirHub Actions, denominato
semplicemente `ci.yml`. Questo viene eseguito ad ogni push e pull request
effettuata in direzionde dei branch `main` e `dev`. Il workflow è stato
organizzato in differenti job, i quali agiscono in maniera completamente
parallela. Ciò permette di otttenere una logica di tipo fail-fast, desiderabile
nelle routine di CI. I job eseguiti sono i seguenti:

- **Build**: responsabile di verificare che il software venga buildato
  correttamente. Il task `:build` viene in questo caso "sezionato" nei suoi due
  sotto-task `:assemble` e `:check`, permettendo una più facile interpretazione
  del log di GH Actions in caso di fail. Il job viene eseguito su una matrice di
  sistemi operativi, mentre si è ritenuto non di interesse testare la build su
  versioni differenti di Java (avendo posto come necessario il solo supporto a
  Java 11 con Scala 2.13);

- **Lint**: responsabile della correttezza stilistica del codice. Al suo
  interno, viene semplicemente eseguito il task `:spotlessCheck`;

- **Coverage**: controlla che le soglie di coverage impostate vengano
  rispettate, tramite il task `:checkScoverage`.

## Il workflow Opt-in CI

Ulteriore controllo di CI è stato posto tramite il workflow `opt-in-CI.yml`.
Questo permette di attivare determitate funzioni di CI, come lo style check, la
build, o il controllo di coverage, a partire dai branch `feature/*`. Viene
definito "opt-in" in quanto di base questi controlli sono disabilitati. Vengono
di fatto utilizzati solo per dei test, e possono essere invocati includendo, nel
contenuto del commit che ha generato il push nel branch, i tag `[lint]`,
`[build]`, `[coverage]`.
