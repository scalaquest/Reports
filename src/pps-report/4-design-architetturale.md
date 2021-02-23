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

L'architettura complessiva è stata organizzata con i seguenti sotto-componenti del sistema, i quali dialogano tra di loro in diverso modo e scambiando informazioni. 

### Core

Il componente **core** rappresenta l'elemento centrale del sistema, nella quale è presente, dunque, la business logic dell'intero progetto.

Questo è stato diviso in diversi sotto-progetti, ciascuno rappresentante una parte autonoma e a sè stante:

- **application**

- **dictonary**

- **model**

- **parsing**

- **pipeline**


### CLI

Un esempio di implementazione delle "strutture" definite in core.

### Examples


## Pattern architetturali

L'intero progetto è stata organizzato prendendo spunto dall'application structure **Flux**, 

L'idea di base è quella di creare una **pipeline**, ovvero
l'utilizzo di vari elementi posti in sequenza in modo da comporre un algoritmo.
Nel nostro caso la pipeline risulta uno strumento particolarmente utile in
quanto abbiamo ipotizzato che in questo modo si potesse definire correttamente
un gioco interattivo.

Nel nostro caso le componenti della pipeline sono:

1. **Lexer**

2. **Parser**

3. **Resolver**

4. **Interpreter**

5. **Reducer**

## Componenti del sistema distribuito

## Scelte tecnologiche

- TuProlog (vantaggi -> Scala) (svantaggi (?) -> prestazioni)


scelte tecnologiche cruciali ai fini architetturali -- corredato da pochi ma
efficaci diagrammi
