# Requisiti

I requisiti sono stati individuati a seguito di diverse sessioni di **knowledge
crunching**, nelle primissime iterazioni di progetto. Queste sono state portate
avanti congiuntamente da tutti i componenti del team, con lo scopo di definire
glossario, elementi di modellazione di base e le loro interazioni.

In linea di massima, durante le sessioni i requisiti sono andati delineandosi,
grazie al confronto tra la visione di base proposta dal Product Owner e le
proposte costruttive dei vari membri del team. Partendo dai concetti individuati
si sono andate a creare le entità alla base per l'implementazione concreta di
progetto.

Si è data molta importanza a tale fase iniziale, tanto da portarla avanti per
quasi due iterazioni complete di Sprint. Ciò allo scopo di creare dei requisiti
il più possibile stabili. A seguire, sono stati elencati i requisiti
individuati.

## Requisiti di business

Per requisiti di business si intendono i requisiti che delineano la base del
progetto:

- Il progetto consiste in un framework utilizzabile da sviluppatori terzi
  (_storyteller_, nel nostro glossario) di **creare e giochi di tipo Interactive
  Fiction**;

- L'utilizzatore del gioco (_user_, nel nostro glossario) esprime i comandi di
  gioco attraverso delle **frasi in linguaggio naturale**. Ciò rende necessaria
  un'interpretazione lessicale e sintattica delle stesse;

- Il gioco è strutturato in **iterazioni successive**: ogni comando inserito
  dallo _user_ modifica lo stato corrente del gioco, generando un output che lo
  descrive; sulla base di questo lo user prende una decisione su quale sarà il
  comando successivo.

## Requisiti utente

Per requisiti utente si intendono i requisiti che l'utente si aspetta dal
sistema. Le categorie di utenti target del sistema sono due, ognuna con i propri
requisiti caratteristici.

### Storyteller

Il termine storyteller identifica i soggetti che utilizzano il sistema per la
creazione di giochi. Sono date lui le seguenti possibilità:

- **Realizzare giochi di categoria Interactive Fiction**. A tale scopo, il
  sistema deve **esporre un'API minimale ma completa**, accessibile tramite un
  linguaggio di programmazione idoneo;

- Modellare la propria storia, e i componenti che la caratterizzano;

- Definire dei comportamenti associati a tali componenti, intesi come delle
  funzioni in grado di modificare lo stato del gioco e l'output mostrato allo
  user;

- Definire le parole chiave (come nomi e verbi) che lo user può utilizzare,
  combinati in frasi anche complesse, per dettare comandi al motore di gioco.

### User

Il termine user identifica i soggetti che utilizzano giochi creati tramite il
framework. Tale soggetto va considerato nell'analisi dei requisiti utente al
pari dello storyteller, in quanto esso rappresenta sia un utente indiretto
(essendo il fruitore di storie create dallo storyteller) che diretto (dovendo il
sistema includere degli esempi di utilizzo). Sono date lui le seguenti
possibilità:

- **Interagire con le storie** generate dagli storyteller, tramite
  un'**interfaccia grafica a linea di comando**; questa deve supportare l'input
  di frasi in linguaggio naturale, e fornirne un output testuale in risposta.

## Requisiti funzionali

Per requisiti funzionali si intendono le funzionalità che caratterizzano il
progetto:

- Fornire un modello sul quale rendere possibile costruire storie di tipo
  Interactive Fiction;

- Fornire una piattaforma sulla quale lanciare le storie, agente da linea di
  comando;

- Fornire la possibilità di creare piattaforme anche differenti da quella
  standard (ad es. da interfaccia web);

- Fornire un engine **Prolog** in grado d'interpretare semplici comandi in
  linguaggio naturale in lingua inglese, mappandoli in azioni applicabili sulla
  storia;

- Fornire dei costrutti comuni per la definizione della storia, già modellati e
  potenzialmente riutilizzabili, al fine di rendere più agevole il compito dello
  storyteller;

- Esporre un set di esempi (almeno due), utili come spunto di partenza per la
  creazione, da parte dello storyteller, della propria storia giocabile.

## Requisiti non funzionali

Per requisiti non funzionali si intendono caratteristiche del progetto utili a
minimizzare le problematiche d'integrazione tra le varie componenti del
framework, verificarne i comportamenti e garantire uno stile di scrittura
conforme alle convenzioni adottate.

- **Effettuare test** per verificare il comportamento del codice, ponendo
  particolare attenzione ai componenti core;

- Introdurre **procedure DevOps**, volte ad automatizzare la verifica del
  codice, porre forti condizioni di QA, compilare il codice ed eseguire i test
  su differenti sistemi operativi, e adottare workflow di Continuous Delivery;

- Velocizzare lo sviluppo di codice attraverso l'adozione del **paradigma
  Agile**;

- Utilizzare la **metodologia Scrum**, cercando di restare più fedeli possibile
  al principio originale, definendo ruoli e Sprint settimanali al fine di
  raggiungere una corretta organizzazione temporale.

## Requisiti d'implementazione

<!-- Cosa deve essere utilizzato per l' implementazione, punti dai quali non possiamo prescindere -->

Per requisiti d'implementazione si intendono tutte le tecnologie e le soluzioni
d'implementazione considerate imprescindibili nella realizzazione del progetto.

- Applicare il paradigma di **programmazione funzionale**;

- Utilizzare il linguaggio di programmazione **Scala**, con garanzia di
  compatibilità sulla versione 2.13 (Java 11);

- Utilizzare il sistema di build automation **Gradle**;

- Utilizzare **GitHub** come servizio di repository per il progetto;

- Utilizzare **GitHub Actions** per CI e CD;

- Utilizzare **ScalaTest**, **jUnit** e **WordSpec** come specifica di test;

- Utilizzare il linguaggio **Prolog** per implementare l'engine.
