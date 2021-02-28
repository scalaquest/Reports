# Design di dettaglio

## Il motore semantico

### Manipolazione di espressioni Prolog

Un problema evidente che è emerso durante la fase di prototipazione del progetto
è stato quello della manipolazione delle espressioni Prolog, che all'interno del
modulo del `Parser` è pervasivo. È stato fatto un primo tentativo di
rappresentazione delle espressioni tramite l'uso di semplici stringhe, ma questa
modalità si è rivelata presto inadatta e scomoda. A questo punto, date le
caratteristiche di Scala, abbiamo pensato di implementare una libreria integrata
all'interno del progetto che abbiamo chiamato **Scalog**. Questa consente
agevolmente di creare espressioni Prolog indipendenti dalla specifica
implementazione del linguaggio, utilizzando un DSL intuitivo, i cui simboli sono
ispirati direttamente a quelli del linguaggio Prolog, cercando di imitare il più
possibile la sintassi originale. Inoltre consente di effettuare pattern matching
contro espressioni esistenti, in modo da agevolare il processo dell'interazione
con i risultati del `Parser`.

### Prolog parser

Un ideale che è stato perseguito durante lo sviluppo di tutto il software è
stato quello della realizzazione di componenti riusabili, per questo abbiamo
cercato di astrarre, quando possibile, dalle specifiche implementazioni e di
descrivere interfacce attraverso le quali rappresentare le realizzazioni
concrete. Per il componente `Parser` questo è riuscito attraverso la
realizzazione di interfacce volte a rappresentare componenti generici, in
particolare:

- `Library`: che rappresenta una generica libreria Prolog;
- `Theory`: che rappresenta una generica teoria Prolog;
- `Engine`: che rappresenta un generico motore Prolog il quale, inizializzato
  con una teoria e un insieme di librerie, è in grado di rispondere a
  interrogazioni;

Per comunicare con `Engine` si è scelto di utilizzare **Scalog**, in quanto
agnostico a specifiche implementazioni e facilmente mappabile a costrutti di
altre librerie Prolog.

A questo punto, ancor prima di realizzare un'implementazione dello specifico
`Engine`, è già stato possibile realizzare il `PrologParser`: un parser che
utilizza qualsiasi un generico motore Prolog per effettuare l'analisi sintattica
di una sequenza di token.

## Application Structure

Giunti alla definizione degli esempi, ci siamo accorti che occorreva strutturare
il software in maniera migliore. Ogni volta che si andava ad instanziare una
nuova storia, infatti, era necessario scrivere del codice che poteva essere
comune tra tutte gli esempi. Questo include principalmente la creazione della
pipeline e del dizionario della storia, che in mancanza di particolari
necessità, avvengono sempre allo stesso modo. Per questo motivo è stato deciso
di rifattorizzare quanto più possibile gli elementi comuni, inserendoli
all'interno del package `application` dentro al modulo _core_. Questo package,
quindi, fornisce un insieme di costrutti che consentono con poche istruzioni
aggiuntive di creare una storia. All'interno di questo package son confluite
anche delle implementazioni di default, volte ad aumentare maggiormente
l'efficienza nella scrittura della singola storia.

Inoltre son stati aggiunti anche metodi di utility, particolarmente importanti
per fornire delle funzionalità adatte ad ogni storia.

In definitiva, questo processo di refactoring ha portato notevoli miglioramenti:

- maggiore modularità tra i componenti;

- eliminazione di codice ripetuto in tutti gli esempi;

- minore possibilità di errore per lo story teller;

- maggiore velocità nel definire nuove storie.

## Generator e GeneratorK

Tra i principali obiettivi preposti, vi è sicuramente quello di definire una
sola volta gli elementi che compongono il dizionario di una storia e, a partire
da questo, generare tutte le strutture dati necessarie ai componenti della
`Pipeline`. In particolare è necessario generare clausole Prolog che serviranno
al parser per svolgere l'analisi sintattica, e una struttura dati in grado di
mappare in maniera biunivoca il nome di un elemento con il riferimento
all'oggetto che lo descrive.

Questa necessità di generare strutture dati diverse, utilizzando un isomorfismo,
a partire da un elemento A che può essere `Item` o `Verb`, si è rivelata essere
un pattern fattorizzabile in un concetto più astratto e riusabile che è stato
chiamato `Generator[A, B]`, realizzato tramite una type class. Si tratta di un
wrapper di una funzione `A => B`.

All'interno del dizionario, però, gli elementi sono contenuti all'interno di una
collezione. È stato quindi introdotto il concetto di `GeneratorK[F[_], A, B]`,
che rappresenta un wrapper di una funzione `F[A] => B`, quindi una funzione in
cui la `A` è all'interno di un contesto `F[_]`. La scelta del nome è stata
ispirata dai nomi utilizzati dalle type class di **Cats**, le quali presentano
una lettera _K_ nelle versioni delle type class che operano sugli _higher-kinded
types_.

Tramite l'uso di queste due astrazioni combinate è possibile fattorizzare
funzioni come `List[A] => List[B]` o `List[A] => Map[K, V]` in
un'implementazione comune.

<!--
questo va su implementazione

Occorre sottolineare che in `GeneratorK`, il valore restituito `B`

-->

## Il modello

Uno dei requisiti centrali alla base del progetto è quello di fornire allo
storyteller un'API che lo aiuti a creare le proprie storie. In quest'ottica, il
package `model` del modulo `Core` contiene tutti i componenti utili alla
creazione di una storia. Più precisamente, il **modello** può essere definito
come l'insieme di tutti e soli componenti utilizzabili dallo storyteller per
costruire la propria storia.

Il componente chiave attorno al quale il modello si fonda è lo **stato**. Esso
può essere assimilato a una sorta di "punto di salvataggio": a partire dal
salvataggio iniziale (ciò che indichiamo con il termine **storia**), essa va
evolvendosi ad ogni iterazione, lasciando l'utente proseguire nel gioco.
L'entità che implementa il concetto di stato prende appunto nome di `State`.

A un livello più pratico, i vari componenti dello `State` sono propedeutici alla
messa in atto della pipeline, permettendo la trasformazione i comandi sotto
forma di stringhe testuali in comandi comprensibili dal modello (`Statement`),
applicabili a loro volta sullo `State`, modificandolo. Lo `State` deve contenere
indicazioni riguardo ai seguenti componenti (astraendo dalle strutture dati
utilizzate):

- l'insieme di `Action` e dei `Verb` ad essi associati. Questi permettono di
  associare i verbi che lo user include all'interno dei comandi testuali, a
  delle entità comprensibili dal modello. Una volta definiti, non dovrebbero
  essere modificabili durante il gioco;

- l'insieme di `Item` disponibili: per `Item` si intende un qualunque componente
  con il quale il player può interagire durante il gioco. Essi devono essere
  definiti nella fase iniziale del gioco, anche se possono non essere
  inizialmente visibili. L'unico vincolo è quindi quello di non poterne generare
  di nuovi a runtime;

- L'insieme di `Room`: una `Room` rappresenta una porzione geografica della
  mappa del gioco. Il player durante il gioco deve avere la possibilità di
  muoversi tra le `Room`; può concettualmente contenere dinamicamente degli
  `Item` a runtime; deve contenere un'indicazione riguardo alle `Room` limitrofe
  (direttamente raggiungibili dalla `Room` corrente, con un passo in direzione
  di un punto cardinale);

- Il `Ground`: esso rappresenta un'entità in grado di gestire i verbi
  intransitivi nella modifica dello stato; concetto approfondito nella sezione
  #;
- Varie altre indicazioni rappresentative dello stato, potenzialmente
  espandibili.

Porre in atto un'implementazione per queste entità non è banale. Le principali
problematiche sono legate a:

- **Dipendenze incrociate**: lo `State` contiene concettualmente degli `Item`,
  ma all'atto pratico anche gli `Item` devono venire a conoscenza dello `State`;
  stesso ragionamento vale per le `Room`;
  
- **Evoluzione dello stato**: lo `State` è un'entità immutabile; per poterla
  aggiornare, è necessario crearne una copia modificata, e per far ciò è
  necessario conoscere il tipo concreto alla base di ogni entità. A causa delle
  dipendenze incrociate, ogni entità deve conoscere il tipo concreto di ognuna.

La miglior soluzione a cui siamo giunti è stata quella di definire le interfacce
base di `State`, `Item` e `Room` all'interno di un trait `Model`, e di
implementarle facendo uso dei **path dependent types**. Sulla base di ciò sono
state poi definite delle `Lens` per rendere possibile la modifica delle singole
entità.

### Aggiornamento dello stato e behavior-based model

Un altro importante sfida nella definizione del modello riguarda la messa in
atto di un meccanismo tale da consentire allo stato di "reagire" ai comandi
utente.

Nel capitolo precedente si è utilizzato il `Statement` per indicare l'output
della fase di resolving della pipeline. Tale output rappresenta un
comando **interpretabile dal modello**. Ciò significa che al
termine della fase di risoluzione, si ha conoscenza riguardo a quali sono gli
`Item` e le `Action` coinvolti nel comando. 

La fase di interpretazione della
pipeline è quella predisposta all'individuazione delle modifiche da applicare
allo stato. L'output della fase è una `Reaction`, ovvero un'entità comprendente
funzione in grado di applicare allo stato le modifiche necessarie, e un'insieme
di informazioni da mostrare in output all'utente (concetto approfondito nella
sezione #). La fase viene posta in atto come segue:

- nel caso di **comandi intransitivi** (`Statement` composto da una sola
  `Action`), l'`Action` viene applicata direttamente a un'entità interna allo
  stato, responsabile di gestire comportamenti intransitivi. Tale entità prende 
  il nome di `Ground`, e deve esporre un metodo `Ground::use(action)`, con output la
  rispettiva `Reaction`;
  
- nel caso di **comandi transitivi e ditransitivi** (`Statement` composto da una
  `Action`, un `Item` sottoposto a tale azione, e un eventuale `Item`
  indirettamente coinvolto) l'`Action` viene applicata all'`Item` oggetto
  dell'azione, passandogli un'eventuale indicazione riguardo all'item
  indirettamente coinvolto. Di conseguenza anche gli `Item` devono esporre un
  metodo `Item::use(azione, itemIndiretto)`, e ritornare la rispettiva `Reaction`.

Alla luce di ciò, si è reso necessario un meccanismo flessibile, modulare,
facilmente utilizzabile dallo storyteller, che permettesse di definire il
comportamento della funzione `::use`.

L'idea a cui si è giunti si basa sul concetto di **behavior**. Essi sono
delle proprietà, proprie degli `Item` e del `Ground`, tali da permettere di
aggiungere in maniera modulare a un `Item` (o un `Ground`) la gestione di
determinate combinazioni `Action-Item`.

Ad esempio, aggiungendo all'oggetto `apple` il comportamento `Takeable`, diventa
possibile durante il gioco prendere la mela (comando `take the apple`),
restituendo la `Reaction` corrispondente.

La potenza di tale meccanismo risiede nella sua **estendibilità**: ogni
behavior può facilmente essere esteso, integrando ulteriori combinazioni
all'interno degli stessi, o sovrascrivendo eventuali comportamenti predefiniti.

All'atto pratico, ciò è stato reso possibile definendo un ulteriore trait che
estende il `Model` di base:
- estendendo il concetto di `Item` e `Ground`, integrando ad essi la possibilità di integrare loro
  dei behavior (`BehaviorBasedItem` e `BehaviorBassedGround`);
- fornendo un'implementazione flessibile del concetto di behavior, (`GroundBehavior` e `ItemBehavior`);
- fornendo un costrutto in grado di definire combinazioni action-item (`GroundTrigger` e `ItemTrigger`).

<!--
### Trigger

I Behaviors sfruttano il concetto di `Trigger` i quali all'accadere di una
determinata azione, nello stato corrente, producono una specifica `Reaction`.

Esistono diversi tipi di trigger:

- `GroundTriggers`: sono tutti i trigger riferiti al ground, ovvero tutte le
  azioni intransitive le qualinon prendono in considerazione alcun item (es: go
  north). Nel codice vengono implementati come
  `PartialFunction[(Action, S), Reaction]`.

- `ItemTriggers`: sono tutti i trigger creati appositamente per gestire azioni
  transitive o ditransitive. Le azioni transitive si riferiscono ad uno
  specifico item (es: takeTheItem(item: Item)). Le ditransitive sono azioni che
  coinvolgono due item (es: openTheDoorWithKey(door: Item, key:Item)). Nel
  codice vengono implementati come
  `PartialFunction[(Action, Option[Item], S), Reaction]`.

Con il concetto di Behavior sono state implementate alcune estensioni le quali
-->

### Reaction

Il concetto di `Reaction` è un wrapper di una funzione
`State => (State, Seq[Message])`, che, preso lo stato attuale, ne produce una
nuova istanza e una sequenza di messaggi. Rappresenta un cambiamento allo stato
della partita e una notifica di avvenuto evento. Possibili implementazioni
potrebbero essere `takeTheItem(i: Item)`, `move(direction: Dir)`, ecc.

È un concetto chiave utilizzato all'interno del `Reducer`, componente che agisce
in coda alla `Pipeline`, dopo l'`Interpreter` e che si occupa di restituire lo
stato aggiornato, insieme alle notifiche sugli effetti prodotti dal comando
sulla partita.

Il concetto di `Reaction` è stato ampliato inizialmente con un metodo `combine`
che consente di combinarne una coppia, in modo che lo stato risultante della
prima sia passato come argomento della seconda e che i messaggi siano
concatenati. Successivamente sono stati introdotti altri metodi che semplificano
un approccio funzionale, il più importante dei quali è `flatMap`, che abilita la
creazione e concatenazione di più reazioni utilizzando le _for comprehension_ di
Scala.

### Commons

Il modulo `Commons` contiene alcuni implementazioni di concetti quali `Items`,
`Action`, `Verb`, `Reaction`, `Ground` e `Pusher` ritenute comuni per molte
possibili storie. Il fine del modulo è quello di agevolare il compito dello
storyteller fornendo elementi pronti all'uso, inoltre tali componenti possono
essere tratti come spunto per nuove funzionalità.

Commons usufruisce spesso del concetto di Behavior introdotto precedentemente.

## CLI

Questo modulo rappresenta di fatto un'implementazione che fa uso dei concetti
presenti in `ApplicationStructure`. Dentro _cli_ viene definito un game loop
utilizzando **ZIO** come strumento per la gestione delle interazioni con la
console, che in questo modo risultano essere type safe.

Il game loop viene implementato attraverso uno schema ricorsivo che svolge le
operazioni descritte nel capitolo precedente.

1. **Lettura della frase inserita**: questa parte viene gestita attraverso
   **ZIO**, il quale si occupa della lettura dalla console in maniera type safe.

2. **Messa in azione della pipeline**: la frase letta dalla console viene
   inoltrata alla `Pipeline`. Questa si occupa di elaborare il risultato che
   risulta essere nella forma:

   - messaggio di errore (qualora non fosse andato a buon fine);
   - nuovo stato aggiornato;
   - sequenza di messaggi da restituire in uscita.

3. **Creazione del messaggio in output**: in base al risultato restituito dalla
   `Pipeline`, viene creato il messaggio da restituire un'uscita sulla console.
   In particolare, se si è verificato un errore, viene ritornato un avviso che
   lo descrive, altrimenti viene restituita la sequenza di messaggi. In
   quest'ultimo caso viene anche aggiornato lo stato.

4. **Stampa del messaggio in uscita**: viene stampato su console il messaggio o
   la sequenza di messaggi calcolati nel punto 3.

5. **Controllo di terminazione**: infine viene controllato se il gioco è
   terminato, e qualora non fosse così, viene richiamato ricorsivamente questo
   schema, ritornando al punto 1. precedente.

I risultati del punto 2. e 3. vengono mappati all'interno di un `UIO`, ovvero
uno **ZIO** particolare in quanto non può fallire. L'utilizzo di tale
implementazione è stata dettata dal fatto che abbiamo gestito gli errori
derivanti dalla `Pipeline` come messaggi, in quanto in questa metodologia di
interazione con il software, i refusi (intesi come ad esempio "input non
capito") sono considerati come parte integrante del sistema.

<!--
Secondo me i dependent types è meglio che stanno nel 4
Approfondimento su model, da path dependent types ->
a dependent types, diagramma trait di commons, l'implementazione
magica di Reaction, dettaglio del parsing/lexer

Suddividerei i capitoli common/parser, ecc.. dentro a organizzazione
del codice

Il design di dettaglio "esplode" (dettaglia) l'architettura, ma viene
concettualmente prima dell'implementazione, quindi non metteteci diagrammi
ultra-dettagliati estratti dal codice, quelli vanno nella parte di
implementazione eventualmente.

##Scelte rilevanti

## Pattern di progettazione

## Organizzazione del codice

organizzazione del codice -- corredato da pochi ma efficaci diagrammi)

-->
