# Requisiti

## Sessioni di Knowledge Crunching
I requisiti sono stati individuati attraverso sessioni di **knowledge crunching**.
In tali sessioni erano presenti tutti i membri del team, per definire la semantica 
degli elementi di modellazione fondamentali e come essi fossero collegati. In tal senso, il Product Owner ha spiegato 
quale sarebbe stata la terminologia da adottare, ed attraverso confronti con gli altri membri,
si sono apportati raffinamenti iterativi ed incrementali.
Una volta confermati unimamente, tali concetti si sono tradotti nella base di partenza
per la loro modellazione in codice, e si è cercato di mantenerli stabili,
senza ulteriori evoluzioni.

## Requisiti di business


I requisiti di business che sono stati individuati consistono in:

- Permettere ad un utente di creare dei giochi del tipo Interactive Fiction.

- Creare un sistema di Natural Language Processing in grado di comprendere ed
  estrarre significato dalla frase inserita dall'utente.

- Fornire un'interfaccia a linea di comando attraverso la quale un giocatore può
  interagire con il sistema.

- Creare un framework estenbile e ben sviluppato in ogni modulo, in modo da
  fornire un API chiara e facilmente utilizzabile.

## Requisiti utente

Riguardo ad i requisiti utente, sono stati individuati in base ai 2 ruoli che
caratterizzano l'interazione con il framework:

- **Story teller**: viene messa a sua disposizione una interfaccia, minimale ma
completa, la quale fornisce espressività sufficiente per la creazione di giochi
  custom. Questo grazie anche alla disponibilità di una linguaggio apposito, volto
  a facilitare la scrittura del gioco.
  

- **Player**: viene messa a sua disposizione un' interfaccia a linea di comando,
che permette di eseguire azioni all' interno della storia del gioco.
  

## Requisiti funzionali

I requisiti funzionali di questo progetto sono descritti di seguito.

- Creazione di un framework che permetta ad uno story teller di creare un gioco
  personalizzabile.

- Creazione di un'interfaccia di gioco attraverso la quale è possibile giocare,
  nel nostro caso si tratta di un'interfaccia a linea di comando.

- Creazione di due esempi che permettano di mostrare le funzionalità
  implementate.

## Requisiti non funzionali

Sono stati individuati i seguenti requisiti non funzionali.

- Sviluppo di una applicazione seguendo i principi del paradigma Agile con
  elementi di DevOps, in modo da creare un sistema corretto, ma allo stesso
  tempo con qualità e conciso.

- Utilizzo della metodologia Scrum, in modo più puntuale possibile, definendo
  ciascun ruolo e organizzando correttamente il tempo.

## Requisiti di implementazione

I requisiti di implementazione possono essere riassunti nei punti seguenti.

- Uso del paradigma di programazzione **funzionale**.

- Utilizzo del linguaggio di programmazione **Scala**.

- Utilizzo di **Gradle** come sistema di build automation.

- Sistema di testing usando **Scalatest** e **jUnit**.

- Utilizzo del linguaggio **Prolog** per implementare il sistema di elaborazione
  del linguaggio naturale.
