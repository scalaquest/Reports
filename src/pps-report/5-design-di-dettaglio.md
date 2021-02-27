# Design di dettaglio

## Manipolazione di espressioni Prolog

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

## Prolog parser

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
chiamato `Generator[A, B]`, realizzato tramite una type class. Si tratta di un wrapper
di una funzione `A => B`.

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

## Model

## Reactions

## Common

## CLI 


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
