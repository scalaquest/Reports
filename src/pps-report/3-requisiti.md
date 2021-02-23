# Requisiti

I requisiti sono stati individuati a seguito di diverse sessioni di **knowledge
crunching**, nelle primissime iterazioni di progetto. Queste sono state portate
avanti congiuntamente da tutti i componenti del team, con lo scopo di definire
glossario, elementi di modellazione di base e le loro interazioni.

In linea di massima, durante le sessioni i requisiti sono andati delienandosi,
grazie al confronto tra la visione di base proposta dal Product Owner e le
proposte costruttive dei vari membri del team. Sulla base dei concetti
individuati si sono andate a creare le entità alla base dell'implementazione
concreta di progetto.

Si è data molta importanza a tale fase iniziale, tanto da portarla avanti per
quasi due iterazioni complete di Sprint. Ciò allo scopo di creare dei requisiti
il più possibile stabili. A seguire, sono stati elencati i requisiti
individuati.

## Requisiti di business

<!-- Requisiti core, qualunque cosa significhi -->

Per requisiti di business si intende i requisiti tali da delineare l'idea alla
base del progetto:

- Il progetto deve consistere in un framework utilizzabile da sviluppatori terzi
  (_storyteller_, nel nostro glossario) di **creare e giochi di tipo Interactive
  Fiction**;

- L'utilizzatore del gioco (_user_, nel nostro glossario) esprime i comandi di
  gioco attraverso delle **frasi in linguaggio naturale**. Ciò rende necessaria
  un'interpretazione lessicale e sintattica delle stesse.

- Il gioco è strutturato in **iterazioni successive**: ogni comando inserito
  dallo user modifica lo stato del gioco, generando un output che lo descrive;
  sulla base di questo lo user prenderà una decisione sul comando successivo.

<!---I requisiti di business che sono stati individuati consistono in:

- Permettere ad un utente di creare dei giochi del tipo Interactive Fiction.

- Creare un sistema di Natural Language Processing in grado di comprendere ed
  estrarre significato dalla frase inserita dall'utente.

- Fornire un'interfaccia a linea di comando attraverso la quale un giocatore può
  interagire con il sistema.

- Creare un framework estensibile e ben sviluppato in ogni modulo, in modo da
  fornire un API chiara e facilmente utilizzabile. --->

## Requisiti utente

<!-- Cosa si aspetta l'utente dal programma? -->

Per requisiti utente si intende i requisiti che l'utente si aspetta dal sistema.
Sono due le categorie di utenti target del sistema, ognuna con i propri
requisiti caratteristici.

### Storyteller

Il termine storyteller identifica i soggetti che utilizzano il sistema per la
creazione di giochi.

- Al soggetto deve essere reso possibile **realizzare giochi di categoria
  Interactive Fiction**.
- A tale scopo, il sistema deve **esporre un'API minimale ma completa**,
  accessibile tramite un linguaggio di programmazione idoneo.

### User

Il termine user identifica i soggetti che utilizzano giochi creati tramite il
sistema. Tale soggetto va considerato nell'analisi dei requisiti utente al pari
dello storyteller, in quanto esso rappresenta sia un utente indiretto (essendo
il fruitore di storie create dallo storyteller) che diretto (dovendo il sistema
includere degli esempi di utilizzo).

- Al soggetto deve essere data la possibilità d'**interagire con le storie
  generate dagli storyteller**.
- A tale scopo, il sistema deve fornire per rendere possibile l'interazione
  un'**interfaccia grafica a linea di comando**.

## Requisiti funzionali

<!-- Quali funzioni deve fornire il framework all'utente -->

I requisiti funzionali sono descritti di seguito.

- Possibilità di definire la terminologia ammessa all' interno del gioco

- Interpretazione dei comandi di gioco, in modo tale da riconoscere ed elaborare
  frasi di complessità crescente

- Possibilità di modellare gli oggetti che popolano un match del gioco, ed il
  loro comportamento con il resto dell' ambiente.

## Requisiti non funzionali

<!-- Cosa deve fornire il framework, come "side effect" -->

Sono stati individuati i seguenti requisiti non funzionali.

- Sviluppo di una applicazione seguendo i principi del paradigma Agile con
  elementi di DevOps, in modo da creare un sistema corretto, ma allo stesso
  tempo con qualità e conciso.

- Utilizzo della metodologia Scrum, in modo più puntuale possibile, definendo
  ciascun ruolo e organizzando correttamente il tempo.

## Requisiti di implementazione

<!-- Cosa deve essere utilizzato per l'implementazione, punti dai quali non possiamo prescindere -->

I requisiti di implementazione possono essere riassunti nei punti seguenti.

- Uso del paradigma di programazzione **funzionale**.

- Utilizzo del linguaggio di programmazione **Scala**.

- Utilizzo del sistema di build automation **Gradle**.

- Sistema di testing usando **Scalatest** e **jUnit**.

- Utilizzo del linguaggio **Prolog** per implementare il sistema di elaborazione
  del linguaggio naturale.

- todo fasi sequenziali // Tale struttura facilita la separazione dei concetti e
  la testabilità.
