# Design architetturale

<!-- Design a alto livello, intesto come pattern flux, la pipeline, il concetto
di core e cli un po' approfondito ma non troppo, e la struttura multiprogetto per
la loro realizzazione, gli esempi,
come ereditano core e cli come dipendenze, qualche diagramma che esplicita le dipendenze
a alto livello (core > cli > examples), i package principali del core, a alto livello
senza approfondirli troppo ma dando un'idea di massima.

Ricordate che una scelta architetturale può ritenersi giustificata o meno solo a fronte
dei requirement che avete indicato; viceversa, ogni requirement "critico" dovrebbe influenzare
qualcuna della scelte architetturali effettuate e descritte.
L'architettura deve spiegare quali sono i sotto-componenti del sistema (da 5 a 15, diciamo),
ognuno cosa fa, chi parla con chi e per dirsi cosa --
i diagrammi aiutano, ma poi la prosa deve chiaramente indicare questi aspetti.
-->

Il progetto ha avuto inizio con una profonda analisi del problema, cercando di
evidenziare da subito eventuali problematiche.

Si è cercato, per quanto più possibile, di mantenere invarianti i requisiti
definiti nel capitolo precedente.

L'analisi iniziale è stata eseguita attraversi svariati incontri di **knowledge
crunching** in cui era presente ogni membro del team. In questo modo è stato
possibile avere una maggiore visione globale e orizzantale sulle tematiche e
sulle scelte importanti.

In un primo momento, infatti, son state prese quante più decisioni possibili per
quanto riguarda il design architetturale, lasciando ad ogni sotto-team/membro le
scelte di dettaglio.

Una sessione di iterazione del nostro progetto può essere definito con queste
macrofasi:

1. Acquisizione dell'input.
2. Elaborazione del comando.
3. Restituzione della risposta.

Questo ciclo viene ripetetuto per ogni comando che l'utente decide di inserire,
fino a quando il gioco non termina per la vittoria (o per la sconfitta) del
giocatore.

## Architettura complessiva

L'architettura complessiva è stata organizzata con i seguenti sotto-componenti
del sistema, i quali dialogano tra di loro in diverso modo e scambiando
informazioni.

Ogni membro del team ha cercato di rispettare quanto più possibile le invarianti
dettate dal paradigma di programmazione funzionale. Ciò significa, ad esempio,
di demandare quanto più possibile i side-effects, preferendo immutabilità in
ciascun componente. In ogni parte del progetto, dunque, la comunicazione tra i
vari componenti avviene passando classi immutabili e non richiamando metodi
delle stesse.

I componenti principali sono definiti di seguito.

### Core

Il componente **core** rappresenta l'elemento centrale del sistema, nella quale
è presente, dunque, la business logic dell'intero progetto. Le sezioni
principali sono:

- **model**: contiene tutte le informazioni che modellano il problema, tra cui:

  - **state**: questo concetto definisce una sorta di snapshot del gioco ad un
    alto livello di astrazione.
  - **???**

- **game**:

- **parser**: come già descritto nelle sezioni precedenti, all'interno di questo
  progetto è necessario creare una parte di Natural Language Processing. Questa
  ha lo scopo di fare consecutivamente una fase di analisi lessicale e,
  successivamente, una di analisi sintattica. La prima risulta essere
  particolarmente banale in quanto il problema è stato semplificato e la
  soluzione si limita a separare ogni sequenza di caratteri divisa da spazio.
  Diversamente la fase di parsing non risulta essere altrettanto immediata.

  Per poter realizzare questa parte in maniera completa è stato deciso di
  utilizzare il linguaggio Prolog in quanto è particolarmente adatto a questo
  scopo. Nello specifico è stata utilizzata la grammatica Prologo chiamata
  **DCG** (Definite Clause Grammar). Con questa grammatica è possibile definire
  delle clausole del primo ordine in maniera alquanto immediata e semplice da
  comprendere. Tutto questo non andando ad intaccare l'espressività e la potenza
  del linguaggio Prolog.

  In questo progetto, dunque, una frase viene definita "corretta" quando ci sarà
  una corrispondenza con gli assiomi definiti da noi come clausole DCG.

  In particolare la gramattica è stata divisa in due parti:

  - una parte **statica**, all'interno della quale vengono definite le regole
    grammaticali che specificano come devono essere formate delle frasi
    corrette. In particolare questa è stata definita all'interno del modulo core
    in maniera statica, ovvero non viene modificata in alcun modo dalla storia
    che viene creata, grazie alla sua natura general purpose;

  - e una parte **dinamica**, che varia in base alla storia definita dallo story
    teller. Ogni oggetto, azione, verbo o aggettivo che caratterizza una storia
    personalizzata viene inserito sotto forma di clausola Prolog all'interno
    della grammatica. Questo permette di interagire con gli elementi del
    sistema.

- **pipeline**: con questo costrutto viene definita la sequenza di componenti
  del sistema che permettono di interpretare i comandi e generare il risultato.
  Le strutture che compongono la pipeline sono:

  1. **lexer**: il primo tassello rappresenta il componente che permette di
     iniziare il processo di Natural Language Process. Iniziamente, la stringa
     inserita dall'utente viene sottoposta ad un'analisi lessicale. Nel nostro
     caso consiste nella creazione di uno stream di token, il quale corrisponde
     ad una singola parola della frase inserita dall'utente. Banalmente questa
     divisione avviene in base alla presenza di uno spazio tra le parole;

  2. **parser**: in un secondo momento viene fatta l'analisi sintattica. Viene
     preso in carico lo stream di token, risultato del lexer, e viene restituito
     un Abstract Syntax Tree.

  3. **resolver**

  4. **interpreter**

  5. **reducer**

### CLI

Il modulo definito **CLI** rappresenta un esempio di implementazione di ogni
elemento definito all'interno del modulo core. In particolare questa sezione
descrive come creare una applicazione che usi una sorta di REPL o di linea di
comando.

Prendendo le informazioni legate al concetto di model e di game definiti in
core, in questo modulo viene definito il funzionamento dell'applicazione, che
implementa le fasi di gioco prima definite in questo modo:

1. viene letta la frase inserita da linea di comando da parte dell'utente
   player;
2. viene messa in azione la pipeline che restituisce un risultato;
3. viene creato il messaggio di risposta in base a ciò che restituisce la
   pipeline;
4. viene stampato a video il messaggio di risposta;
5. se il gioco è terminato, viene chiusa la sessione, altrimenti torna al
   punto 1. e ricomincia il ciclo.

All'interno del modulo CLI viene anche inserita una parte di logica allo scopo
di interpretare le modifiche che vengono fatte a state. In particolare occorre
modellare le Reaction , in quanto da queste vengono poi generati i messaggi in
risposta all'utente.

<!--Parlare di ZIO qui ?-->

### Examples

## Pattern architetturali

L'intero progetto è stata organizzato prendendo spunto dall'application
structure **Flux**, framework moderno e molto utilizzato anche da colossi come
Facebook.

Il flusso di dati di questa applicazione può seguire un percorso unidirezionale
oppure avere un comportamento ciclico. Quest'ultima soluzione in particolare,
risulta essere molto efficace con il problema definito in questo progetto.

In questo paradigma viene considerato come punto centrale il nodo
**Dispatcher**, attraverso il quale fluiscono tutti i flussi di dati. Nel nostro
caso questo concetto è stato esploso ed è stato implementato attraverso la
creazione della pipeline.

## Scelte tecnologiche

<!-- scelte tecnologiche cruciali ai fini architetturali -- corredato da pochi ma
efficaci diagrammi -->

Le principali scelte tecnologiche possono essere riassunte nelle seguenti
sezioni.

### tuProlog

<!-- TuProlog (vantaggi  > Scala) (svantaggi (?) > prestazioni)-->

**TuProlog** rappresenta la libreria su cui è ricaduta la scelta per quanto
concerne la creazione del motore Prolog di Natural Language Processing.

Le motivazioni per cui è stata scelta sono molteplici:

- durante le lezioni del corso è stato presentata questa libreria quindi tutti i
  membri del team avevano familiarità con tale libreria;

- perfetta integrazione tra tuProlog e il mondo JVM. Nel nostro progetto quindi
  ha permesso di utilizzare Prolog all'interno del linguaggio Scala senza
  particolari problematiche dovute alla comunicazione tra i due costrutti;

- possibilità di utilizzare la grammatica Prolog **DCG** importando una piccola
  parte aggiuntiva alla libreria.

Tra i possibili svantaggi derivanti dall'utilizzo della libreria tuProlog vi
potrebbe essere un problema legato alle prestazioni. Essendo sviluppata in Java
e quindi su JVM, potrebbero non essere ottimizzati i tempi attraverso i quali
vengono esplorate le soluzioni Prolog.

Tuttavia nel nostro progetto il Prolog non viene richiamato in maniera
intensiva, ma il suo utilizzo si limita alla parte della pipeline che esegue
l'analissi sintattica della frase inserita dal player. Per questo motivo, non è
stato importante fare una analisi degli eventuali problemi di prestazione legati
alla libreria utilizzata.

## ZIO

Per quanto riguarda ...

## Lens
