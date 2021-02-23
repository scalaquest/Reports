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

I requisiti fondamentali che caratterizzano il cuore del framework sono 
descritti di seguito:

- L'utente deve aver la possibilità di definire giochi di tipo Interactive Fiction,
sia dal punto di vista del player, sia da quello del creatore degli aspetti modellistici.
  
- L'utente esprime i comandi di gioco attraverso frasi, quindi in linguaggio naturale.
  Ciò rende necessaria un' interpretazione lessicale e sintattica.

- Il processo di gioco è strutturato in varie fasi sequenziali, seguendo un' idea finish-to-start,
in quanto la fase successiva necessita dell' output della precedente. Ciò facilita la 
  separazione dei concetti e la verificabilità
<!---I requisiti di business che sono stati individuati consistono in:

- Permettere ad un utente di creare dei giochi del tipo Interactive Fiction.

- Creare un sistema di Natural Language Processing in grado di comprendere ed
  estrarre significato dalla frase inserita dall'utente.

- Fornire un'interfaccia a linea di comando attraverso la quale un giocatore può
  interagire con il sistema.

- Creare un framework estenbile e ben sviluppato in ogni modulo, in modo da
  fornire un API chiara e facilmente utilizzabile. --->

## Requisiti utente

Essi sono stati individuati in base ai 2 ruoli 
che l' utente può assumere nell'interazione con il framework:

- **Story teller (creatore del gioco)**: deve aver la possibilità di creazione giochi
  personalizzati attraverso un' interfaccia, minimale ma completa, con l'agevolazione
  di un linguaggio che semplifichi tale interfacciamento.
  
- **Player**: deve aver la possibilità di eseguire azioni all' interno della storia del gioco
  tramite un' interfaccia a linea di comando.
  

## Requisiti funzionali

I requisiti funzionali sono descritti di seguito.

- Possibilità di definire la terminologia ammessa all' interno del gioco
  
- Interpretazione dei comandi di gioco, in modo tale da riconoscere ed
  elaborare frasi di complessità crescente
  
- Possibilità di modellare gli oggetti che popolano un match del gioco, ed
il loro comportamento con il resto dell' ambiente.
  


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

- Utilizzo del sistema di build automation **Gradle**.

- Sistema di testing usando **Scalatest** e **jUnit**.

- Utilizzo del linguaggio **Prolog** per implementare il sistema di elaborazione
  del linguaggio naturale.
