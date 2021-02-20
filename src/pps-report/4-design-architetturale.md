# Design architetturale

<!-- Design a alto livello, intesto come pattern flux, la pipeline, il concetto
di core e cli un po' approfondito ma non troppo, e la struttura multiprogetto per
la loro realizzazione, gli esempi,
come ereditano core e cli come dipendenze, qualche diagramma che esplicita le dipendenze
a alto livello (core > cli > examples), i package principali del core, a alto livello
senza approfondirli troppo ma dando un'idea di massima.
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

## Pattern architetturali

L'architettura prevede l'utilizzo del pattern **pipeline**, il quale prevede
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

scelte tecnologiche cruciali ai fini architetturali -- corredato da pochi ma
efficaci diagrammi
