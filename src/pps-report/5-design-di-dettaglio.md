# Design di dettaglio

## Riconoscimento dei comandi testuali

In queste sezioni si descrivono le modalità con le quali Prolog è stato
utilizzato nell'implementazione dei componenti atti ad interpretare i comandi
dell'utente in forma testuale, associoando agli stessi un signigicato
comprensibile dal modello.

### Manipolazione di espressioni Prolog

Un problema evidente che è emerso durante la fase di prototipazione del progetto
è stato quello della manipolazione delle espressioni Prolog, che all'interno del
modulo del `Parser` è pervasivo. È stato fatto un primo tentativo di
rappresentazione delle espressioni tramite l'uso di semplici stringhe, ma questa
modalità si è rivelata presto inadatta e scomoda. Date le caratteristiche di
Scala, si è pensato di implementare una libreria integrata all'interno del
progetto, chiamata **Scalog**. Questa consente agevolmente di creare espressioni
Prolog indipendenti dalla specifica implementazione del linguaggio, utilizzando
un DSL intuitivo, i cui simboli sono ispirati direttamente a quelli del
linguaggio Prolog, cercando di imitare il più possibile la sintassi originale.
Inoltre, la libreria consente di effettuare pattern matching contro espressioni
esistenti, in modo da agevolare il processo dell'interazione con i risultati del
`Parser`.

### Prolog parser

Un ideale che è stato perseguito durante lo sviluppo di tutto il software è
stato quello della realizzazione di componenti riusabili. Per questo motivo si è
cercato di astrarre, quando possibile, dalle specifiche implementazioni e di
descrivere interfacce attraverso le quali rappresentare le realizzazioni
concrete. Per il componente `Parser` sono state realizzate quindi delle
interfacce volte a rappresentare componenti generici. In particolare:

- `Library` rappresenta una generica libreria Prolog;
- `Theory` rappresenta una generica teoria Prolog;
- `Engine` rappresenta un generico motore Prolog, il quale, inizializzato con
  una teoria e un insieme di librerie, è in grado di rispondere a
  interrogazioni.

Per comunicare con `Engine` si è scelto di utilizzare **Scalog**, in quanto
agnostico a specifiche implementazioni e facilmente mappabile a costrutti di
altre librerie Prolog.

A questo punto, ancor prima di realizzare un'implementazione dello specifico
`Engine`, è già stato possibile realizzare il `PrologParser`: un parser che
utilizza un generico motore Prolog per effettuare l'analisi sintattica di una
sequenza di token.

## Application structure

Giunti alla definizione degli esempi, si è rilevata la necessità di un
refactoring della struttura del software. Ogni volta che si è andati ad
instanziare una nuova storia, infatti, era necessario scrivere diverso codice
"boilerplate", comune a tutti gli esempi. Principalmente per quanto concerne la
creazione della pipeline e del dizionario della storia, in mancanza di
particolari necessità, avvengono sempre allo stesso modo. Per questo motivo si è
deciso di effettuare quanto più possibile il refactoring degli elementi comuni,
inserendoli all'interno del package `application`, all'interno del modulo
`core`. Tale package fornisce un insieme di costrutti che consentono con poche
istruzioni aggiuntive di creare una storia. All'interno di questo son confluite
anche delle implementazioni di default, volte ad aumentare maggiormente
l'efficienza nella scrittura della singola storia.

Son stati aggiunti anche metodi di utility, particolarmente importanti per
fornire delle funzionalità adatte ad ogni storia.

In definitiva, tale processo di refactoring ha portato:

- maggiore modularità tra i componenti;
- eliminazione di codice ripetuto in tutti gli esempi;
- minore possibilità di errore per lo storyteller;
- maggiore velocità nel definire nuove storie.

## Generator e GeneratorK

Tra i principali obiettivi preposti, vi è sicuramente quello di definire una
sola volta gli elementi che compongono il dizionario di una storia e, a partire
da questo, generare tutte le strutture dati necessarie ai componenti della
`Pipeline`. In particolare si è reso necessario generare clausole Prolog utili
al parser per svolgere l'analisi sintattica, e una struttura dati in grado di
mappare in maniera biunivoca il nome di un elemento con il riferimento
all'oggetto che lo descrive.

Questa necessità di generare strutture dati diverse, utilizzando un isomorfismo,
a partire da un elemento `A` che può essere `Item` o `Verb`, si è rivelata
essere un pattern fattorizzabile in un concetto più astratto e riusabile che è
stato chiamato `Generator[A, B]`, realizzato tramite una type class. Si tratta
di un wrapper di una funzione `A => B`.

Nel dizionario, però, gli elementi sono contenuti all'interno di una collezione.
Si è quindi introdotto il concetto di `GeneratorK[F[_], A, B]`, che rappresenta
un wrapper di una funzione `F[A] => B`, quindi una funzione in cui `A` è
all'interno di un contesto `F[_]`. La scelta del nome è stata ispirata dai nomi
utilizzati dalle type class di **Cats**, le quali presentano una lettera _K_
nelle versioni delle type class che operano sugli _higher-kinded types_.

Tramite l'uso di queste due astrazioni combinate è possibile fattorizzare
funzioni come `List[A] => List[B]` o `List[A] => Map[K, V]` in
un'implementazione comune.

## Il modello

Uno dei requisiti centrali alla base del progetto è quello di fornire allo
storyteller un'API che lo aiuti a creare le proprie storie. In quest'ottica, il
package `model` del modulo `core` contiene tutti i componenti utili alla
creazione di una storia. Più precisamente, il **modello** può essere definito
come l'insieme di tutti e soli componenti utilizzabili dallo storyteller per
costruire la propria storia.

Il componente chiave attorno al quale il modello si fonda è lo **stato**. Esso
può essere assimilato a una sorta di "punto di salvataggio": a partire dal
salvataggio iniziale (ciò che indichiamo con il termine **storia**), essa va
evolvendosi ad ogni iterazione, lasciando l'utente proseguire nel gioco.
L'entità che implementa il concetto di stato prende appunto nome di `State`.

A un livello più pratico, i vari componenti dello `State` sono propedeutici alla
messa in atto della pipeline, permettendo la trasformazione di comandi sotto
forma di stringhe testuali in comandi comprensibili dal modello (`Statement`),
applicabili a loro volta sullo `State`, modificandolo. Lo `State` deve contenere
indicazioni riguardo ai seguenti componenti (astraendo dalle strutture dati
utilizzate):

- l'insieme di `Action` e dei `Verb` ad essi associati. Questi permettono di
  mappare i verbi che lo user include all'interno dei comandi testuali, a delle
  entità comprensibili dal modello. Una volta definiti, non dovrebbero essere
  modificabili durante il gioco;

- l'insieme di `Item` disponibili: per `Item` si intende un qualunque componente
  con il quale il player può interagire durante il gioco. Essi devono essere
  definiti nella fase iniziale del gioco, anche se possono non essere
  inizialmente visibili. L'unico vincolo è quindi quello di non poterne generare
  di nuovi a runtime;

- L'insieme di `Room`: una `Room` rappresenta una porzione geografica della
  mappa del gioco. Il player durante il gioco deve avere la possibilità di
  muoversi tra le `Room`. La stanza può concettualmente contenere dinamicamente
  degli `Item` a runtime; deve contenere un'indicazione riguardo alle `Room`
  limitrofe (direttamente raggiungibili dalla `Room` corrente, con un passo in
  direzione di un punto cardinale);

- Il `Ground`: esso rappresenta un'entità in grado di gestire i verbi
  intransitivi nella modifica dello stato;

- Varie altre indicazioni rappresentative dello stato, potenzialmente
  espandibili.

Porre in atto un'implementazione per queste entità non è stato banale. Le
principali problematiche sono legate a:

- **Dipendenze incrociate**: lo `State` contiene concettualmente degli `Item`,
  ma all'atto pratico anche gli `Item` devono venire a conoscenza dello `State`.
  Stesso ragionamento vale per le `Room`;

- **Evoluzione dello stato**: lo `State` è un'entità immutabile; per poterla
  aggiornare, è necessario crearne una copia modificata, e per far ciò si deve
  conoscere il tipo concreto alla base di ogni entità. A causa delle dipendenze
  incrociate, ogni entità deve conoscere il tipo concreto di ognuna.

La miglior soluzione a cui siamo giunti è stata quella di definire le interfacce
base di `State`, `Item` e `Room` all'interno di un trait `Model`. Sulla base di
ciò sono state poi definite delle `Lens` per rendere possibile la modifica delle
singole entità.

### Aggiornamento dello stato e behavior-based model

Un altro importante sfida nella definizione del modello riguarda la messa in
atto di un meccanismo tale da consentire allo stato di "reagire" ai comandi
utente.

Nel capitolo precedente si è utilizzato il termine `Statement` per indicare
l'output della fase di resolving della pipeline. Tale output rappresenta un
**comando interpretabile dal modello**. Ciò significa che al termine della fase
di risoluzione, si ha conoscenza riguardo a quali sono gli `Item` e le `Action`
coinvolti nel comando.

La fase d'interpretazione della pipeline è quella predisposta all'individuazione
delle modifiche da applicare allo stato. L'output della fase è una `Reaction`,
ovvero un'entità comprendente funzioni in grado di applicare allo stato le
modifiche necessarie, e un'insieme di informazioni da mostrare in output
all'utente (concetto approfondito nella sezione @sec:reaction). La fase viene
posta in atto come segue:

- nel caso di **comandi intransitivi** (`Statement` composto da una sola
  `Action`), l'`Action` viene applicata direttamente a un'entità interna allo
  stato, responsabile di gestire comportamenti intransitivi. Tale entità prende
  il nome di `Ground`, e deve esporre un metodo `Ground::use(action)`, con
  output la rispettiva `Reaction`;

- nel caso di **comandi transitivi e ditransitivi** (`Statement` composto da una
  `Action`, un `Item` sottoposto a tale azione, e un eventuale `Item`
  indirettamente coinvolto), l'`Action` viene applicata all'`Item` oggetto
  dell'azione, passandogli un'eventuale indicazione riguardo all'item
  indirettamente coinvolto. Di conseguenza anche gli `Item` devono esporre un
  metodo `Item::use(azione, itemIndiretto)`, e ritornare la rispettiva
  `Reaction`.

Alla luce di ciò, si è reso necessario un meccanismo flessibile, modulare,
facilmente utilizzabile dallo storyteller, che permettesse di definire il
comportamento della funzione `::use`.

L'idea a cui si è giunti si basa sul concetto di **behavior**. Un behavior è
proprietà, caratteristica degli `Item` e dei `Ground`, tale da permettere
l'integrazione modulare, all'interno di un `Item` (o un `Ground`), della logica
per la gestione di determinate combinazioni `Action`-`Item`.

Ad esempio, integrando a un item `apple` il comportamento `Takeable`, diventa
possibile durante il gioco prendere la mela (comando `take the apple`),
restituendo la `Reaction` corrispondente.

La potenza di tale meccanismo risiede nella sua **estendibilità**: ogni behavior
può facilmente essere esteso, integrando ulteriori combinazioni all'interno
degli stessi, o sovrascrivendo eventuali comportamenti predefiniti.

All'atto pratico, ciò è stato reso possibile definendo un ulteriore trait che
estende il `Model` di base:

- estendendo il concetto di `Item` e `Ground`, fornendo ad essi la possibilità
  di integrare loro dei behavior (`BehaviorBasedItem` e `BehaviorBasedGround`);

- fornendo un'implementazione flessibile del concetto di behavior,
  (`GroundBehavior` e `ItemBehavior`);

- fornendo un costrutto in grado di definire combinazioni `Action`-`Item`
  (`GroundTrigger` e `ItemTrigger`).

### Reaction {#sec:reaction}

Nelle sezioni precedenti si è spesso fatto riferimento al termine `Reaction`,
come una funzione in grado di modificare lo stato e di tener traccia dell'output
da mostrare all'utente. Nella pratica, ciò si concretizza in una funzione
`State => (State, Seq[Message])`, che, preso lo stato attuale, ne produce una
nuova istanza e una sequenza di messaggi. Permette quindi sia di rappresentare
un cambiamento nello stato della partita, che la notifica di avvenuto evento.
Possibili implementazioni potrebbero essere `takeTheItem(i: Item)`,
`move(direction: Dir)`, ecc.

È un concetto chiave utilizzato all'interno del `Reducer`, componente che agisce
in coda alla `Pipeline` dopo l'`Interpreter`, e che si occupa di restituire lo
stato aggiornato insieme alle notifiche sugli effetti prodotti dal comando sulla
partita.

Il concetto di `Reaction` è stato ampliato inizialmente con un metodo
`::combine()`, che consente di combinarne una coppia, in modo che lo stato
risultante della prima sia passato come argomento della seconda e che i messaggi
siano concatenati. Successivamente sono stati introdotti altri metodi che
semplificano un approccio funzionale, il più importante dei quali è `flatMap`,
che abilita la creazione e concatenazione di più reazioni utilizzando il
costrutto **for comprehension** di Scala.

### I Message e il Pusher

Si è fatto riferimento, nelle sezioni precedenti, alla necessità di avere un
output, da poter mostrare all'utente, al termine dell'esecuzione della pipeline.
Come scelta progettuale, si è deciso di separare l'output vero e proprio, da
delle **notifiche di avvenuto evento**, che fanno scaturire lo stesso. Ciò
permette di avere una separazione più netta dei concetti, supportando
potenzialmente diverse tipologie di output (non soltanto testuale).

La pipeline, oltre a fornire come output lo stato aggiornato, restituisce
infatti una sequenza di notifiche. Esempi di notifiche potrebbero essere
l'apertura di una porta, l'uccisione di un avversario, l'aver mangiato una mela,
ecc. Nella nostra implementazione, tali notifiche prendono il nome di `Message`.

Al di fuori della pipeline è quindi necessario un componente in grado di
associare ad ogni `Message`, il corrispondente output, facilmente
personalizzabile dallo storyteller. A tale scopo, nella nostra implementazione è
presente il componente `Pusher`. Esso viene implementato come un'abstract class
che permette di definire una mappatura tra dei `Message` in input con un output
di tipo generico. `StringPusher` è un'abstract class che estende poi il
`Pusher`, supportando output di tipo `String`.

Il `Pusher` è facilmente personalizzabile dall'utente. Per essere utilizzato,
deve essere esteso, andandone a implementare i `::messageTriggers`. Un
`MessageTriggers` altro non è che una `PartialFunction`, che permette di
definire le varie corrispondenze tra `Message` e output.

### Commons

Il package `model` contiene, tra gli altri, anche delle implementazioni "pronte
all'uso" di vari `Item`, `Action`, `Verb`, `Reaction`, `Ground` e `Pusher`, di
uso comune nell'implementazione di storie. Questi sono contenuti all'interno del
package `commons`. È possibile integrare questi ultimi nel trait
`BehaviorBasedModel` mixandoli all'interno dello stesso: si è infatti
strutturato il package in maniera tale da contenere le implementazioni in
differenti trait. Nel progetto, in generale, i trait marcati con il suffisso
`Ext` possono essere mixati al model principale; quelli che iniziano con il
prefisso `C` sono inoltre dei trait facenti parte di `commons`.

## CLI {#sec:cli_detail}

Questo modulo rappresenta di fatto un'implementazione che fa uso dei concetti
presenti in `ApplicationStructure`. Dentro `cli` viene definito un game loop
utilizzando **ZIO** come strumento per la gestione delle interazioni con la
console, che in questo modo risultano essere type safe.

L'applicazione viene implementata tramite uno schema REPL
(Read-Eval-Print-Loop), che consente ad ogni iterazione di inserire un comando
che viene interpretato dal gioco e a cui corrisponde un output. Nello specifico
i passi principali da eseguire sono i seguenti:

1. **Lettura della frase inserita**: questa parte viene gestita attraverso
   **ZIO**, il quale si occupa della lettura dalla console in maniera type safe.

2. **Messa in azione della pipeline**: la frase letta dalla console viene
   inoltrata alla `Pipeline`. Questa si occupa di elaborare il risultato in
   forma:

   - _messaggio di errore_ (qualora non fosse andato a buon fine);
   - _nuovo stato_ aggiornato;
   - _sequenza di messaggi_ da restituire in uscita.

3. **Creazione del messaggio in output**: in base al risultato restituito dalla
   `Pipeline`, viene creato il messaggio da mostrare sulla console. In
   particolare, se si è verificato un errore, viene ritornato un avviso che lo
   descrive, altrimenti viene restituita la sequenza di messaggi. In
   quest'ultimo caso viene anche aggiornato lo stato.

4. **Stampa del messaggio in uscita**: viene stampato su console il messaggio o
   la sequenza di messaggi calcolati nel punto 3.

5. **Controllo di terminazione**: infine viene controllato se il gioco è
   terminato, e qualora non fosse così, viene richiamato ricorsivamente questo
   schema, ritornando al punto 1 precedente.

Gli errori che vengono emessi dalla `Pipeline` sono stati trasformati in
messaggi, in quanto come nelle shell dei comandi, i refusi (intesi come ad
esempio "input non compreso" o "operazione non possibile") sono considerati come
parte integrante del sistema.
