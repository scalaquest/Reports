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
comune tra tutte gli esempi.

Per questo motivo è stato deciso di raffinare la scrittura degli esempi
importando quanto più possibile elementi comuni su `Core`. All'interno di questa
sezione son confluiti anche delle implementazioni di default, volte ad aumentare
ancora l'efficienza e la modularità del software.

Inoltre son stati aggiunti anche metodi di utility, particolarmente importanti
per fornire delle funzionalità che potrebbero essere adatti ad ogni esempio.

In definitiva, questo processo ha portato notevoli miglioramenti:

- modularità del codice maggiore;

- eliminazione di parti comuni a tutti gli esempi;

- maggiore velocità nel definire nuove storie;

- minore possibilità di errore per lo story teller.

## Generator e GeneratorK

Tra i principali obiettivi preposti, vi è sicuramente quello di evitare che ci
siano fonti di verità diverse. In particolare, questo problema è stato
riscontrato in concomitanza della creazione della grammatica Prolog. Gli
elementi del dominio devono essere utilizzati con due specifiche
implementazioni: da una parte le strutture utili per modellare il dominio, e
dall'altra la creazione di clause e costrutti Prolog.

Per questo motivo, sono stati creati `Generator` e `GeneratorK`; si tratta di
due wrapper componibili tra varie funzioni, particolarmente utili alla creazione
delle strutture sopracitate.

Nello specifico, `Generator` genera l'output come il risultato tra due funzioni,
contrariamente a `GeneratorK`, il quale utilizza funzioni del primo ordine per
produrre risultati più complessi. Ciò significa che:

- `Generator` calcola funzioni da `A` a `B`;

- `GeneratorK` calcolca funzioni da `F[A]` a `B`.

<!--
questo va su implementazione

Occorre sottolineare che in `GeneratorK`, il valore restituito `B`
debba essere un monoide
-->

## Reactions

## Common

## Scelte rilevanti

## Pattern di progettazione

## Organizzazione del codice

organizzazione del codice -- corredato da pochi ma efficaci diagrammi)

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
-->

## Scelte rilevanti

## Pattern di progettazione

## Organizzazione del codice

organizzazione del codice -- corredato da pochi ma efficaci diagrammi)
