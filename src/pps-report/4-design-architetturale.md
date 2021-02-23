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

L'architettura complessiva è stata organizzata con i seguenti sotto-componenti
del sistema, i quali dialogano tra di loro in diverso modo e scambiando
informazioni.

Ogni membro del team ha cercato di rispettare quanto più possibile le invarianti
dettate dal paradigma di programmazione funzionale. Ciò significa, ad esempio,
di demandare quanto più possibile i side-effects, preferendo immutabilità di
ciascun componente. In ogni parte del progetto, dunque, la comunicazione tra i
vari componenti avviene passando classi immutabili e non richiamando metodi
delle stesse.

I componenti principali sono definiti di seguito.

### Core

Il componente **core** rappresenta l'elemento centrale del sistema, nella quale
è presente, dunque, la business logic dell'intero progetto. Le sezioni più
interessanti sono:

- **modello**: contiene tutte le informazioni che modellano il problema;

- **pipeline**: con questo costrutto viene definita la sequenza di componenti
  del sistema che permettono di interpretare i comandi e generare il risultato.
  Le strutture che compongono la pipeline sono:

  1. **lexer**: il primo tassello rappresenta il componente che permette di
     iniziare il processo di Natural Language Process. Iniziamente, la stringa
     inserita dall'utente viene sottoposta ad un'analisi lessicale. Nel nostro
     caso consiste nella creazione di uno stream di token, il quale corrisponde
     ad una singola parola della frase inserita dall'utente. Banalmente questa
     divisione avviene in base alla presenza di uno spazio tra le parole;

  2. **parser**: in un secondo momento viene fatta l'analisi sintattica. Viene
     preso in carico lo stream di token, risultato del lexer, e viene restituito
     un Abstract Syntax Tree.

  3. **resolver**

  4. **interpreter**

  5. **reducer**

### CLI

Un esempio di implementazione delle "strutture" definite in core.

### Examples

## Pattern architetturali

L'intero progetto è stata organizzato prendendo spunto dall'application
structure **Flux**,

L'idea di base è quella di creare una **pipeline**, ovvero l'utilizzo di vari
elementi posti in sequenza in modo da comporre un algoritmo. Nel nostro caso la
pipeline risulta uno strumento particolarmente utile in quanto abbiamo
ipotizzato che in questo modo si potesse definire correttamente un gioco
interattivo.

## Componenti del sistema distribuito

## Scelte tecnologiche

- TuProlog (vantaggi -> Scala) (svantaggi (?) -> prestazioni)

scelte tecnologiche cruciali ai fini architetturali -- corredato da pochi ma
efficaci diagrammi
