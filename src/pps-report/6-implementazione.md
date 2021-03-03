# Implementazione

<!--
Implementazione
(per ogni studente,
una sotto-sezione descrittiva di cosa fatto/co-fatto e con chi,
descrizione di aspetti implementativi importanti
non già presenti nel design)
-->

In questo progetto sono diversi i temi implementativi salienti riscontrati. Di
seguito sono descritti i punti principali, divisi in base all'autore o agli
autori che li hanno sviluppati.

## Aspetti trattati in comune

Alcune sezioni del software hanno richiesto l'attenzione di tutti i membri del team, in sedute comuni
di programmazione e di scambio di idee. Tali sezioni erano quelle incluse nelle seguenti categorie:

- sezioni strategiche;
- sezioni particolarmente complesse, tali da richiedere competenze possedute da più sotto-team;
- sezioni "cuscinetto" tra moduli differenti, tali da richiedere le conoscenze di strutture
  sviluppate separatamente da sotto-team differenti.

Tra le parti aventi queste caratteristiche vi sono:
- Lo sviluppo degli esempi;
- Il lavoro delle primissime iterazioni di progetto, nelle quali è stata posta la base di progetto,
  basata sull suddivisione in più sotto-progetti Gradle;
- Scelte chiave alla base dello sviluppo del model, come l'adozione dei path dependent types;
- Scalte chiave e strutture alla base dello sviluppo della pipeline.


## Aspetti trattati in sotto-team

Allo scopo di rendere il lavoro parallelo e flessibile, gran parte del lavoro è stato spartito tra
due sotto-team, con responsabilità legate a parti di progetto differenti.

### Team 1

<!-- Anche specializzazioni e aspetti approfonditi più da un solo componente che da
un altro.-->
Il team 1 è composto dai membri Riccardo Maldini, Jacopo Corina, Thomas Angelini.
Sono stati trattati specifici aspetti del `Core`, legati in generale alla definizione del
modello. Nello specifico:

- sviluppo di buona parte del **modello**, e del sotto-modulo `commons`, corrispondente a
  grandi linee al package `model`;

- sviluppo di componenti interni al modulo `pipeline`, quali `resolver`, `interpreter` e `reducer`.

#### Dettagli implementativi

##### Model

Riguardo alla definizione del model, vanno sottolineate le seguenti scelte implementative:
- A causa delle dipendenze circolari tra `Room`, `Item` e `State`, tali entità sono state definite
  all'interno di un abstract class comune, denominato `Model`, e implementati tramite il meccanismo dei path dependent
  types. Tale scelta ha influenzato il resto dell'implementazione, determinando la necessità di espandere
  l'implementazione in una gerarchia che parte da essa.

- L'implementazione del model basata sul meccanismo dei behavior viene integrata tramite l'utilizzo dell'abstract class
  `BehaviorBasedModel`. All'interno di questa vengono definiti i concetti di `BehaviorBasedItem`, `ItemBehavior`,
  `BehaviorBasedGround`, `GroundBehavior`. Le "combinazioni" di `Action` e `Item` in grado di far scattare comportamenti
  vengono definiti in dei `Set` interni agli stessi, contenenti dei `GroundTriggers` e `ItemTriggers`.
  Questi non sono altro che wrapper per `PartialFunction[(Action, State), Reaction]` e
  `PartialFunction[(Action, Option[Item], State), Reaction]`, sulla base
  dei quali vengono implementati i metodi `BehaviorBasedGround::use()` e  `BehaviorBasedItem::use()`, combinando tra
  loro tramite ::lift tutti i triggers propri di un certo behavior.

- Internamente, `Room` e `Item` sono completamente descritti dal proprio riferimento (`RoomRef` e `ItemRef`); comportamento
  implementato effettuando l'override dei metodi `::equals()` e `::hashCode()` degli stessi, abilitando il confronto sulla base
  appunto dei soli ref, anziché sull'hashcode dell'intero oggetto. Ciò permette
  di comparare tra di loro `Room` (o `Item`) che, pur rappresentando lo stesso concetto, hanno delle differenze legate
  all'implementazione, rendendo al contempo più agevoli i test.

- A livello implementativo, le `Room` non contengono al loro interno gli `Item` concreti presenti al loro interno, ma i soli
  riferimenti alle stesse. Per restituire gli `Item` concreti, la `Room` deve risolverli,
  prendendo in input lo `State`, il quale contiene la `Map` che memorizza gli `Item` effettivi. Ciò evità ad esempio inconsistenze tra i dati.

- Le case class che implementano i `BehaviorBasedItem` sono intese come delle categorie di oggetti (ad esempio: la categoria
  di oggetti `Key` denota l'insieme di oggetti che potenzialmente potrebbero aprire un item di tipo `Door`).
  In fase di costruzione degli stessi, gli `ItemBehavior` che caratterizzano gli item vengono passati come delle higher
  order function `I => ItemBehavior`, eseguite una volta costruito l'oggetto. Questo in quanto gli `ItemBehavior`
  necessitano di un subject, ovvero di un riferimento agli item che li hanno generati.


##### Commons

<!-- diagramma delle classi dei trait di commons -->

Tale package è formato da alcuni sotto-package i quali si basano su un pattern comune, ovvero
molto spesso le classi sono interne a un trait il quale estende `BehaviorBasedModel`.
Questa scelta implementativa deriva dal fatto che le classi interne devono avere necessariamente
avere i type `I`, `G` legati al concetto di `Behavior`, da qui la necessità di ereditarli da una classe
che li contenga entrambi, in questo caso `BehaviorBasedModel`.

Altrettanto spesso viene adoperato per ogni sotto-package un trait che sia formato dal mixin di tutti i
trait specifici. Tale trait, quindi, permetterà col suo singolo mix di avere i concetti contenuti dai trait più
specifici. Tale interfaccia deve estendere `BehaviorBasedModel` per le motivazioni descritte precedentemente.

I package da `commons` contenuti sono i seguenti:

- `actioning` contente implementazioni di `Action` e `Verb`. Tutte le `Action` derivano dall'applicazione
  dei `Behavior` sul `Ground` o sugli `Items`;

- `groundBehavior` è un package contente alcune implementazioni di `Behavior` utilizzabili da un `Ground`.
  Tali `Behavior` sono:
  - `GenericGroundBehavior` che rappresenta una comoda opzione anziché creare completamente un `Behavior`
    Tale classe contiene solamente il trigger;
  - `InspectableBag` che fornisce all'utente la possibilità di guardare gli elementi presenti all'interno della `Bag`;
  - `InspectableLocation` che fornisce all'utente la possibilità di vedere gli elementi presenti all'interno di una `Room`;
  - `Navigable` che fornisce all'utente la possibilità di muoversi attraverso le `Room`.

- `grounds` è un package contente un implementazione di un `Ground` tale che abbia i `Behavior` precedenti;

- `itemBehavior` è un package contente alcune implementazioni di `Behavior` utilizzabili per un `Item`.
  Tali `Behavior` sono:
  - `GenericItemBehavior` che rappresenta una comoda opzione anziché creare completamente un `Behavior`
    Tale classe contiene solamente il trigger;
  - `Takeable` che fornisce all'`Item` la possibilità di essere inserito dal `Player` all'interno della `Bag`;
  - `Eatable` che fornisce all'`Item` la possibilità di essere mangiato dal `Player`;
  - `Openable` che fornisce all'`Item` la possibilità di essere aperto dal `Player`;
  - `RoomLink` che fornisce all'`Item` la possibilità di essere da tramite tra due `Room`. Molto spesso questo concetto
    è legato anche al comportamento `Openable`;
  - `Container` che fornisce all'`Item` la possibilità di contenere altri `Item`, i quali saranno nascosti al `Player`
    fin tanto che non viene aperto l'`Item` container.


- `items` è un package contente alcune a di `Item` che possono contenere gli `itemBehavior`
  creati precedentemente;

- `pushing` è un package contente alcune implementazioni di `Message` e un implementazione di `Pusher` che produce
  risposte sotto forma di stringa e che riconosce già tutti i `Message` di `pushing`. Tali messaggi sono considerati base,
  ovvero è fornita una risposta ai messaggi di base, ma tale risposta può anche essere personalizzata dallo storyteller;

- `reactions` è un package contenente alcune implementazioni di `Reaction` sviluppate in base ai `Behavior` dei package
  `itemBehavior` e `groundBehavior`.

Inoltre `commons` contiene anche un trait `CommonsExt` che fornisce tutti gli elementi del package stesso
se adoperato attraverso il meccanismo dei mixin con la classe `BehaviorBasedModel` dalla quale estrae i tipi.
Tale trait permette di evitare di effettuare il mixin di ogni singolo trait specifico.


##### Interpreter, resolver, reducer


#### Responsabilità personali

Personalmente ogni elemento del team ha svolto dei task specifici, legati ai task principali del team, ma non esclusivamente:

- **Thomas Angelini**: Il membro ha gestito lo sviluppo del sotto-modulo `commons` all'interno di `model.behaviorBased`.


- **Jacopo Corina**: Oltre all' esplorazione di varie alternative per predisporre la suddivisione dei workflow della CI ed
  alla successiva predisposizione iniziale della generazione automatica dei report e della build automation, successivamente
  evoluta assieme agli membri del team, il membro ha proposto l' idea iniziale dell' utilizzo di behaviour e come essi
  dovessero essere integrati con gli item, ossia non come estensione di funzionalità a livello sintattico ma come proprietà,
  in quanto la gestione sarebbe risultata semplificata, con i medesimi benefici.
  Le successive ottimizzazioni di tale aspetto sono state svolte in cooperazione con gli altri membri.
  Si è approfondita la realizzazione dei componenti della pipeline citati in precedenza.
  - Resolver
    Una classe astratta `AbstractSyntaxTreeResolver`,  utilizzando il pattern "template method" ,
    fornisce una gestione completa delle possibili casistiche ottenibili dal
    risultato della parte di parsing `ParsingResult`: l' abstract syntax tree contenuto nel risultato viene distinto
    mediante pattern matching in base alla classe `Intransitive`, `Transitive`, `Ditransitive`. In ultimo caso, se la classe
    non fosse di una delle ammesse, viene restituita una stringa, contenente potenzialmente il messaggio di errore, ed
    essa sarà propagata come risultato alternativo del ciclo di pipeline. Nei casi ammessi, vengono estratti gli attributi
    presenti e si verifica se essi sono presenti tra le `actions` e gli `items` ammessi, tornando in caso affermativo
    uno `statement` di tipo corrispondente a quello matched, che sarà wrappato da un oggetto `ResolverResult`.
    Se vi fossero mancate corrispondenze, al pari della casistica di errore precedente, sarebbe restituita una stringa di errore.
    La classe `Resolver` fornisce una possibile implementazione di `AbstractSyntaxTreeResolver`, definendo nel metodo
    `actions` se la action passata è tra quelle definite nello stato, e definendo nel metodo `items` la ricerca di essi
    nello scope di gioco (insieme degli oggetti presenti nella bag o nella location del player) basandosi su un **criterio di confronto**
    tra `ItemDescription` dell' oggetto con quella degli altri nello scope, secondo il quale per poter avere un match, il
    nome deve essere il medesimo ed eventuali aggettivi dell' oggetto ricercato devono essere un sottoinsieme dell' altro oggetto preso in considerazione.
    Per esemplificare, se nello scope fosse presente una sola mela (senza aggettivi) e si cercasse una mela verde, non si otterrebbe
    alcuna corrispondenza. Se fosse presente una mela verde e si cercasse una mela rossa, non si avrebbe alcuna corrispondenza, e così
    pure se fossero presenti entrambe le mele con aggettivi e se ne cercasse una senza alcuno. In quest' ultimo caso
    vi sarebbero corrispondenze multiple quindi si renderebbe necessaria una disambiguazione.

- Interpreter



- **Riccardo Maldini**: Il membro ha curato in particolare lo sviluppo delle entità di base del **model**, e parte della sua
  implementazione principale basata su behavior. Gran parte del lavoro riguardo questo aspetto è stato
  ad ogni modo portato a termine nel contesto del team 1.

  Oltre a ciò, il membro è responsabile dello sviluppo di vari task minori:
  - Ruolo di Scrum Master, e in generale di coordinatore del backlog;
  - Sviluppo di parte dei workflow CI/QA,
  - predisposizione della prima base progettuale Gradle basata su convention plugin e submodule
  - Sviluppo di parte dei workflow di release.


### Team 2 (Nardini, Gorini)

Le parti a cui abbiamo partecipato insieme sono:

- Package `Core`:
  - `dictonary` con tutti i suoi elementi;
  - `pipeline` in particolare gli elementi `Lexer` e `Parser`;
  - `application` con tutti i suoi elementi;
  - `parsing` con tutti i suoi elementi.

- Package `Cli`.

Occorre sottolineare che i concetti son stati svilupatti totalmente in "pair programming".
Tuttavia, successivamente, vengono descritti quali sono le parti di cui personalmente un membro del team è
responsabile.

#### Dettagli implementativi

#### Generators

Le type class Generator e GeneratorK che si trovano all'interno di questo
modulo sono state realizzate utilizzando le type class offerte da Cats.
L'implementazione di Generator[A, B] risulta essere un banale wrapper di una
funzione A => B, mentre l'implementazione di GeneratorK[F[_], A, B] richiede
che siano state definite istanze per le seguenti type class:

- Generator[A, B]: essere in grado di generare da ogni valore a: A un output
  b: B;
- Functor[F] e Foldable[F]: in quanto sono necessarie le funzioni map e
  fold, per trasformare all'interno ed infine estrarre un valore b: B a
  partire dal contesto F[_];
- Monoid[B]: in quanto è necessaria un'operazione binaria associativa e un
  valore empty per effettuare l'operazione di fold.

#### Dictionary

Contiene i costrutti, realizzati tramite algebraic-data types, che consentono la
dichiarazione di verbi, utilizzati in fase di scrittura di una storia da parte
dello storyteller. A partire da un verbo deve essere possibile generare le
seguenti informazioni:
- una regola Prolog, che descrive la grammatica del verbo;
- una tupla (Verb, Preposition) -> Action (o Verb -> Action), che collega
  l'uso del verbo al suo significato.

![Diagramma delle classi che rappresenta la gerarchia realizzata per i verbi.](/home/filo/prova/verbs.png)

Contiene inoltre una funzione in grado di generare, a partire dal dizionario di
una storia e dalla grammatica di base, una teoria Prolog utilizzata per
inizializzare un Engine. Per fare ciò utilizza due istanze di
GeneratorK[List, Verb, Program] e GeneratorK[List, Item, Program], in grado
di generare per ogni classe di elemento del dizionario un programma Prolog
valido. Infine questi programmi vengono concatenati tra di loro e alla
grammatica di base.

##### Scalog

Per quanto concerne il package `scalog`, tra le parti che occorre sottolineare vi è la creazione di
uvarie strutture molto interessanti per la modellazione del codice Prolog. É stata creata una struttura
gerarchica modulare basata su Algebraic Data Type che ha permesso di avere maggiore controllo proprio a
livello di struttura, evitando di lavorare direttamente con le stringhe. Inoltre, è stato implementato
in maniera massiva un DSL in grado di scrivere codice Scala in una sintassi molto simile a quella di Prolog.

##### Modulo `cli`

implemtanzione di zio con game loop

###### Struttura di default dell'applicazione

All'interno del package `application`, tra i dettagli implementativi più interessanti
vi è l'utilizzo del pattern "Template Method" all'intero di `DefaultPipelineProvider`
per creare la pipeline di default. In particolare è interessante notare che passando
come parametro solamente una teoria Prolog, oltre naturalmente al `model`, sia possibile
instanziare una nuova pipeline.

##### Parser

Nell'implementazione di `Parser` viene utilizzato nuovamente il pattern "Template Method", con il
notevole beneficio di poter sviluppare completamente questa parte della pipeline senza dover
necessariamente conoscere quale motore Prolog venisse implementato. Per questo motivo è stato possibile
creare il package `parser` ben prima di sviluppare il modulo `engine`. Inoltre, in questo modo il codice
risulta essere particolarmente scalabile e modulare, in quanto in maniera molto semplice ed in poco tempo,
è possibile implementare un altro motore Prolog, che ad esempio lavora con **SWI-Prolog**.

##### Natural Language Processing in Prolog

frase imperative (verbo senza soggetto)
tutto sulla grammatica

##### Engine Prolog

Questa soluzione utilizza il pattern "Adapter" per wrappare e permettere di utilizzare
la libreria **tuProlog** all'interno del codice. Successivamente questa è stata arraggiata per
modellare correttamente il nostro dominio; ad esempio è stata creata una interfaccia `engine` la quale
prevede di essere definita solamente attraverso `theory` e `library`.

Occorre sottolineare che in alcuni parti del codice, vengono gestite solo parzialmente le eccezioni
che potrebbero essere sollevate nell'utilizzo del Prolog. Questa scelta è stata dettata da un duplice fattore:
il codice altrimenti si sarebbe notevolmente "sporcato" con l'utilizzo di costrutti try/catch o di Option.
Tuttavia, questa parte viene utilizzata e gestita interamente da i membri del team e quindi sappiamo come
rispettare le interfacce, evitando di sollevare eccezion.

#### Responsabilità personali

All'interno del team, le responsabilità di ognuno di noi sono:

- **Filippo Nardini**:
  1. sottoprogetto cli;
  2. package scalog;
  3. package dictonary.

- **Francesco Gorini**:
  1. package lexer;
  2. package parser;
  3. package engine;
  4. package application.


<!-- 
Possibilmente si può fare anche ##### dettaglio_implementativo (membro_responsabile)
-->

