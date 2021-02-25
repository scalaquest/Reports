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

Un'ideale che è stato perseguito durante lo sviluppo di tutto il software è
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

## Generator e GeneratorK

## Common
## Scelte rilevanti

## Pattern di progettazione

## Organizzazione del codice

organizzazione del codice -- corredato da pochi ma efficaci diagrammi)

<!--
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
