# Requisiti

I requisiti sono stati definiti in fase di progettazione iniziale. Questi son
stati considerati come immutabili nel corso della progettazione.

## Requisiti di business

I requisiti di business sono descritti di seguito.

- Permettere ad un utente di creare dei giochi del tipo Interactive Fiction.

- Fornire un'interfaccia a linea di comando attraverso la quale un giocatore può
  interagire con il sistema.

- Creare un framework estenbile e ben sviluppato in ogni modulo, in modo da
  fornire un API chiara e facilmente utilizzabile.

## Requisiti utente

I requisiti utente sono descritti di seguito.

- **Story teller**: con questo termine viene definito l'utente che utilizzerà il
  Domain Specific Language per creare storie personalizzate. Il framework mette
  a disposizione un API minimale ma completa, in grado di avere un'espressività
  sufficiente per definire giochi custom.

- **Player**: questo utente rappresenta un giocatore che interagisce con il
  framework per giocare. Il progetto prevede la creazione di un'interfaccia a
  linea di comando per questo tipo di interazione. L'utente sarà in grado,
  dunque, in ogni momento di capire come interagire con il sistema e quindi di
  utilizzare lo strumento correttamente.

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
