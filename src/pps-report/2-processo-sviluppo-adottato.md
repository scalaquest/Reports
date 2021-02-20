# Processo di sviluppo adottato

<!-- Scriviamo robe relative a Scrum meeting e pianificazione settimanale/scrum
review, retrospettiva, utilizzo di github projects, divisone in due sottogruppi
principali, ma alcune cose anche portate avanti singolarmente, Gradle con
scalatest, wordSpec, QA con scoveragev+ somnarcloud, release automatizzate...
-->

Il processo di sviluppo adottato rispecchia i principi del framework Scrum, nel
quale vengono definite un insieme di regole basate sulla metodologia Agile. In
aggiunta, sono stati seguiti i dogmi dettati dal paradigma DevOps.

In questo modo, l'unione di queste metodologie, ha permesso a ogni componente
del team di poter lavorare in maniera del tutto coordinata; questo ha portato a
un particolare giovamento sia per quanto riguarda l'affiatamento tra i colleghi,
sia per quanto concerne il corretto sviluppo del progetto.

Il lavoro è stato sviluppato principalmente in sottoteam composti da due o tre
persone, seguendo una sorta di "pair programming". Questa modalità, già
utilizzata in altri progetti, inizialmente limita la velocità di lavoro in
quanto diversi componenti sono occupati sulla stessa parte di codice. Tuttavia
questo processo porta ad un innalzamento della qualità di ciò che viene prodotto
e, successivamente, ad un minore debito tecnico in quanto le scelte vengono
discusse in tempo reale tra i membri.

## Modalità di divisione in itinere dei task

Per quanto riguarda la suddivisione dei task, è stato seguito quanto viene
descritto dalla metodologia Scrum. A cadenza settimanale, infatti, sono stati
organizzati degli incontri che portavano alla chiusura dello sprint corrente. A
questi incontri erano sempre presenti tutti i membri del team, in quanto
risultava particolarmente importante sentire tutte le opinioni e le peculiarità
emerse nell'ultima settimana.

## Meeting e interazioni pianificate

Ad ogni meeting è stata seguita la seguente scaletta:

- **Brainstorming generale**: ogni componente ha aggiornato i colleghi di ciò
  che è stato fatto durante lo sprint appena terminato. Questo ha portato spesso
  a importanti discussioni su tematiche emerse dal lavoro di ogni membro del
  team.

- **Merge**: insieme delle parti che sono state aggiunte al branch principale
  del progetto attraverso Pull Request in modo tale attivare la Continous
  Integration.

- **Definizione dei task**: in questa fase sono stati eliminati dalla
  programmazione tutte le funzionalità completate. Inoltre, nel backlog sono
  state aggiunte feature ancora da sviluppare emerse durante l'ultimo meeting.

- **Programmazione del futuro ciclo**: è stato programmato il lavoro per lo
  sprint che si accingeva a cominciare. In questo modo sono state scelte le
  parti più urgenti da sviluppare e, ciascuna di esse è stata assegnata ad una
  parte del team.

## Modalità di revisione in itinere dei task

-Test? -CI?

## Scelta degli strumenti di test, build e CI

Nel progetto è stato adoperato **Gradle** come build automation tool in quanto
il team ha una conoscenza pregressa su di essa, inoltre il tool risulta essere
molto supportato e completo in ogni suo aspetto.

Per quanto concerne i test è stata adoperata **ScalaTest** come testing tool in
quanto è ritenuta funzionale e completa. Nello specifico si è utilizzato lo
stile di _AnyWordSpec_ poichè risulta essere sufficientemente dichiarativo
grazie alla innestabilità.

-CI?
