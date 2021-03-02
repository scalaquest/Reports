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

Come già ampiamente descritto nei capitoli precedenti, alcune parti del software
hanno richiesto l'attenzione di tutti i membri del team. Le sezioni del codice
che hanno richiesto questa collaborazione presentavano una (o più) di queste
peculiarità:

- parti strategiche e di fondamentale importanza;

- parti complesse;

- parti che richiedevano le conoscenze di strutture sviluppate singolarmente.

Tra le parti che son state trattate in comune tra tutti i membri del team, vi sono dunque
gli esempi. Questi, in particolare, richiedevano l'utilizzo delle conoscenze di ciascuno dei
due sotto-team.

## Aspetti trattati in sub-team

### Team 1 (Maldini, Corina, Angelini)

<!-- Anche specializzazioni e aspetti approfonditi più da un solo componente che da
un altro.-->
La parte del sotto-progetto `Core` a cui hanno contribuito i membri è racchiusa principalmente
nel package `Core`

### Team 2 (Nardini, Gorini)

La parte del sottoprogetto `Core` a cui abbiamo partecipato insieme sono
racchiuse nei seguenti package:

- `dictonary`

- `application`

- `parsing` -> `scalog` e `engine`

In aggiunta son stati sviluppati anche alcuni componenti della `Pipeline`, quali `Lexer` e `Parser`, e il modulo `cli`.
Occorre sottolineare che i concetti son stati svilupatti totalmente in "pair programming". Tuttavia, successivamente,
vengono descritti quali sono le parti di cui personalmente un membro del team è responsabile.

#### Nardini
- Package `dictonary`. Un importante dettaglio implementativo riguardante questa parte è legata al concetto di `GeneratorK`.
    Questo viene definito come `GeneratorK[F[_], A, B]`. In particolare:
        - `F[_]` rappresenta un **Funtore**, condizione necessaria in quanto all'interno del codice viene utilizzato il suo metodo `fold`.
        - `A` rappresenta un generico.
        - `B` deve essere tassativamente un **Monoide** in quanto `F` folda su di esso, e quindi occorre che abbia l'elemento neutro
        e il combine.
        - generator A > B

- Package `scalog`.
   dsl per la creazione di codice simil prolog
   algebraic data type per la def dei termini
   no stringhe > struttura gerarchica modulare > maggiore controllo sulla struttura

- `cli`
    implemtanzione di zio con game loop

#### Gorini

- Package `application`.
    "template method" per creare la pipeline
    pipeline di default

-  `Lexer`

- `Parser`
    template method

- Package `engine`.
    wrapper (pattern adapter) del tuprolog attraverso interfaccia engine (theory e library)
    prende sempre la prima soluzione
    gestione delle eccezioni (scelta di silenziare i warning perché lo gestiamo noi) > no option, no try catch

