# Modelli di sviluppo

Durante lo sviluppo del progetto, non si è adottato sempre lo stesso modello di
sviluppo. Nelle prime fasi di progetto, durante le quali non si aveva del codice
abbastanza stabile da essere "rilasciabile", si è seguito un approccio più
flessibile e prototipale, denominato GitFlow, per poi evolvere il modello ad un
più strutturato GitFlow.

## GitHub Flow in fase embrionale

**GitHub Flow** è un modello di sviluppo ispirato a GitFlow, ma con alcune
caratteristiche che lo rendono più flessibile e semplice da porre in atto.

Il modello richiede ad esempio che la versione stabile del software sia
mantenuta su un branch `main` (o `master`), senza però la necessità di un branch
`dev` parallelo. Allo stesso tempo, però, GitHub Flow suggerisce di organizzare
il lavoro in `feature/*` branch, come in GitFlow, i quali confluiscono nel main
a seguito della revisione di un secondo utente.

Alla luce di ciò, le prime iterazioni di progetto hanno presentato particolare
flessibilità sulle modalità di modifica del codice. Le varie feature sono state
sviluppate sui rispettivi `feature/*` branch, poi riversati nel `main` tramite
pull request. Si è subordinato la chiusura di queste alla revisione da parte di
un membro del team (solitamente, non appartenente allo stesso sub-team) e al
passaggio di determitati workflow di CI e QA (descritti al {@sec:chap5}).

## GitFlow a regime

Una volta predisposta una codebase sufficientemente stabile, e una volta
abilitati i workflow di Continuous Deploy, si è migrato al più strutturato
modello **GitFlow**. Questo permette di avere nel branch `main` la versione
ufficiale e stabile, sempre associata a una release. A ogni push nel `main` deve
corrispondere un tag, associato a sua volta a un numero di versione. La versione
"di lavoro" del codice, stabile ma potenzialmente parziale, risiede nel branch
`dev`.

I vari `feature/*` branch confluiscono ora tramite pull request in `dev`, con
gli stessi vincoli formulati per modello precedente (controlli di CI obbligatori
e revisione di un utente). In aggiunta, per una maggiore leggibilità e
organizzazione del codice, si è adottata una precisa politica di merge, che
prevede che queste pull request vengano chiuse tramite **squash and merge**.

Il `main` viene aggiornato tramite delle pull request sullo stesso originate da
branch `release/X.Y.Z` (o `hotfix/X.Y.Z`), originati dal `dev`, dove con `X.Y.Z`
si intende un numero di versione formulato secondo semantic versioning. Queste
pull request presentano, oltre ai vincoli di validazione visti per le precedenti
(controlli di CI e revisione di un membro del team), anche la necessità di
presentare una coverage superiore al 75% nei moduli `core` e `cli`. Sono poi
presenti degli accorgimenti di automazione ulteriori per la delivery
automatizzata degli artefatti, e la gestione dei tag, indicati in {@sec:chap6}.
Infine, una politica di merge ben precisa è adottata alla chiusura di queste
pull request, le quali richiedono un **merge commit** che riporti, come commento
del commit, un breve changelog[^1].

[^1]: è necessario far presente che alcuni problemi sono incorsi tra la release

0.3.1 e 0.4.0, frangente nel quale, a seguito di un errore nelle politiche di
commit, si è dovuto agire tramite rebase per preservare la repository. La storia
tra questi due tag risulta quindi non perfettamente lineare.
