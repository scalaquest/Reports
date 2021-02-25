# Design architetturale

<!--
Design a alto livello, inteso come pattern flux, la pipeline, il concetto
di core e cli un po' approfondito ma non troppo, e la struttura multi progetto per
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

Come specificato in precedenza, il gioco si struttura dal punto di vista dello
user in diverse **iterazioni**, per ognuna delle quali viene intercettato
l'input utente (in linguaggio naturale), e a seguito di varie elaborazioni viene
restituita una risposta (in formato testuale, o in altri formati).

Risulta quindi naturale implementare ogni iterazione come una funzione, che dato
un comando testuale e lo stato del gioco, restituisce lo stato modificato e
l'output per l'utente. Faremo riferimento a questa funzione con il nome di
**pipeline**, essendo di fatto strutturata come una vera e propria "catena di
montaggio", come verrà in seguito osservato.

L'architettura di progetto si fonda tutto attorno a questa funzione. Sarà di
fatto necessario un modulo incaricato di prendere un comando utente e
restituirne l'output, e un modulo incaricato di iterare la stessa, tale da
comporre una sessione di gioco completa.

Parallelamente alla gestione della pipeline, dovrà essere resa disponibile
un'API per lo storyteller, così da permettere lui di creare nuove storie basate
su questo modello.

## Architettura di massima

Si è organizzato quindi il sistema in più macro-componenti, corrispondente
ognuno a un sotto-progetto Gradle separato.

<!-- deployment diagram che mostr le relazioni tra i componenti -->

Sono stati individuati:

- Il modulo **core**, che implementa l'engine di gioco, la pipeline, e il
  necessario per rendere possibile modellare nuove storie;
- Il modulo **cli**, che fornisce un'implementazione in grado di eseguire
  sessioni di gioco basate su Command Line Interface. Il modulo include `core`
  come dipendenza, rappresentando da solo la libreria necessaria per generare
  storie CLI;
- I moduli **examples**, che rappresentano dei giochi di esempio, andando a
  mostrare le modalità consigliate per l'utilizzo di `cli` nell'implementazione
  di storie.

In seguito si vanno ad approfondire le caratteristiche dei singoli componenti.

### Core

Il modulo **Core** rappresenta l'elemento centrale del sistema, tale da
implementare l'engine di gioco, la pipeline, e il necessario per rendere
possibile modellare nuove storie. È strutturato ad alto livello in molteplici
sezioni, corrispondenti a package separati:

- **Model**: contiene tutti i componenti e gli strumenti necessari a modellare
una storia;
<!--
    questo approfondimento di model è un aspetto implementativo

    - **player**: rappresenta la pedina all'interno del gioco. Il player esegui i comandi
      affidatigli dallo User;

    - **item**: rappresentano tutti gli oggetti presenti in un match del gioco;

    - **room**: rappresentano i luoghi presenti navigabili all'interno di una storia creata dallo
    Storyteller. Le stanze possono contenere gli _item_.

    - **bag**: una borsa che il player porta con sè la quale può essere riempita con _item_.
    Non è stato posto un limite di oggetti portabili dalla bag;

    - **state**: questo concetto definisce lo stato del gioco in un determinato istante.
      Include al suo interno preziose informazioni, quali:
        - tutte le _room_ presenti all'interno del match;
        - locazione attuale del _player_;
        - tutti gli _item_ presenti nel match;

    - **message**: la cronologia dei messaggi avvenuti all'interno della partita.
-->

- **Parsing**: contiene le logiche alla base della fase di parsing della
  pipeline, ovvero ciò che concerne l'interpretazione dell'input testuale
  dell'utente tramite l'interprete Prolog.

- **Application e Dictionary**: comprendono delle utility per dare un "template"
  di base alle storie, facilitandone la costruzione, e la generazione delle
  regole grammaticali Prolog utili alla fase di parsing;

<!-- sarebbe meglio metterlo nella parte di implementazione!

come già descritto nelle sezioni precedenti, all'interno di questo
  progetto è necessario creare una parte di Natural Language Processing. Questa
  ha lo scopo di fare consecutivamente una fase di analisi lessicale e,
  successivamente, una di analisi sintattica. La prima, tokenization, risulta essere
  particolarmente banale in quanto il problema è stato semplificato e la
  soluzione si limita a separare ogni sequenza di caratteri divisa da spazio.
  Diversamente la fase di parsing non risulta essere altrettanto immediata.

  Per poter realizzare questa fase in maniera completa è stato deciso di
  utilizzare il linguaggio Prolog in quanto è particolarmente adatto a questo
  scopo. Nello specifico è stata utilizzata la grammatica Prolog chiamata
  **DCG** (Definite Clause Grammar). Con questa grammatica è possibile definire
  delle clausole del primo ordine in maniera alquanto immediata e semplice da
  comprendere. Tutto questo non andando a deteriorare l'espressività e la potenza
  del linguaggio Prolog.

  In questo progetto, dunque, una frase viene definita "corretta" quando ci sarà
  una corrispondenza con gli assiomi definiti da noi come clausole DCG.

  In particolare la grammatica è stata divisa in due parti:

  - una parte **statica**, all'interno della quale vengono definite le regole
    grammaticali che specificano come devono essere formate delle frasi
    corrette. In particolare questa è stata definita all'interno del modulo core
    in maniera statica, ovvero non viene modificata in alcun modo dalla storia
    che viene creata, grazie alla sua natura general purpose;

  - e una parte **dinamica**, che varia in base alla storia definita dallo story
    teller. Ogni oggetto, azione, verbo o aggettivo che caratterizza una storia
    personalizzata viene inserito sotto forma di clausola Prolog all'interno
    della grammatica. Questo permette di interagire con gli elementi del
    sistema.
-->

- **Pipeline**: definisce l'elaborazione di una singola iterazione di gioco,
  dall'input di un comando testuale e dello stato del gioco, all'output dello
  stato di gioco modificato e del contenuto visualizzato dall'utente.
  L'elaborazione si struttura in differenti fasi, a loro volta contenute in
  differenti package:

  1. **Lexer**: dato l'input dello user, lo si sottopone ad un'analisi
     lessicale, volta alla creazione di uno stream di token. Ogni token
     corrisponde ad una parola, distinta in base alla separazione tramite spazi;

  2. **Parser**: dato il risultato del `Lexer`, lo si sottopone ad un'analisi
     sintattica tramite l'interprete Prolog, dalla quale viene generato un
     Abstract Syntax Tree;

  3. **Resolver**: dato il risultato del `Parser`, si associa ad ogni suo
     elemento un significato all'interno del sistema, producendo uno
     **Statement**, ossia un comando comprensibile dal modello;

  4. **Interpreter**: dato il risultato del `Resolver`, si verifica sia
     possibile applicare lo `Statement` sullo stato corrente del gioco. Quando
     possibile, viene generata una **Reaction** ossia una funzione contenente le
     eventuali modifiche da applicare sullo stesso.

  5. **Reducer**: data la `Reaction` ottenuta al termine del passo precedente,
     si provvede ad applicarla allo stato corrente del gioco, aggiornandolo e
     generando eventuali messaggi utili per l'interazione con lo user;

### CLI

Il modulo **CLI** fornisce un'implementazione in grado di eseguire sessioni di
gioco basate su Command Line Interface. Il modulo \ include `core` come
dipendenza, rappresentando da solo la libreria necessaria per generare storie
CLI.

L'implementazione fornita itera di fatto l'esecuzione della pipeline. È
possibile individuare, per ogni iterazione, le seguenti fasi

1. viene letta la frase inserita dallo user;
2. viene messa in azione la pipeline che restituisce un risultato;
3. viene creato il messaggio di risposta in base a ciò che restituisce la
   pipeline;
4. viene stampato a video il messaggio di risposta;
5. se il gioco è terminato, viene chiusa la sessione, altrimenti si ricomincia
   il ciclo.

### Examples

Sono state incluse all'interno del progetto diverse storie di esempio, generate
tramite l'utilizzo del modulo `cli`:

1. **Escape Room**: lo user si trova all'interno di uno scantinato con vari
   oggetti coi quali è concessa l'interazione. La storia si focalizza
   sull'utilizzo di vari builder preimpostati per la generazione della stessa;

2. **PokeQuest**: lo user viene catapultato nel mondo Pokemon e più precisamente
   nella cittadina di Aranciopoli (Vermilion City). La storia mostra come sia
   possibile generare dei componenti completamente custom, limitando l'utilizzo
   di builder.

## Pattern architetturali

La gestione dello stato all'interno del progetto è stata ideata prendendo spunto
dall'application structure **Flux**.

Il flusso di dati di questa applicazione segue un percorso unidirezionale
ciclico. Quest'ultima soluzione in particolare, risulta essere molto efficace
con il problema definito in questo progetto in quanto ad ogni input generato
dallo user viene aggiornato lo stato. Il prossimo input utente viene valutato a
partire del nuovo stato attuale.

In questo paradigma viene considerato come punto centrale il nodo
**Dispatcher**, attraverso il quale fluiscono tutti i flussi di dati. Nel nostro
caso questo concetto è stato esploso ed è stato implementato attraverso la
creazione della pipeline.

## Scelte tecnologiche

<!-- scelte tecnologiche cruciali ai fini architetturali -- corredato da pochi ma
efficaci diagrammi -->

Al fine di rispettare i requisiti proposti, sono state effettuate delle scelte
su alcune tecnologie che hanno influenzato poi anche in maniera importante
alcune scelte architetturali.

### tuProlog

<!-- TuProlog (vantaggi > Scala) (svantaggi (?) > prestazioni)-->

**TuProlog** rappresenta la libreria scelta per quanto concerne la creazione del
motore Prolog di Natural Language Processing. Si è scelta questa libreria per
avere una solida base per la fase di parsing.

Le motivazioni per cui è stata scelta sono molteplici:

- perfetta integrazione tra `tuProlog` e il mondo JVM e questo ha consentito di
  utilizzare Prolog all'interno del linguaggio Scala senza particolari
  problematiche dovute all'integrazione di API diverse;

- possibilità di utilizzare la grammatica Prolog **DCG** importando una piccola
  parte aggiuntiva alla libreria.

Tra i possibili svantaggi derivanti dall'utilizzo della libreria `tuProlog` vi
potrebbe essere un problema legato alle prestazioni. Essendo sviluppata in Java
e quindi su JVM, potrebbero non essere ottimizzati i tempi attraverso i quali
vengono esplorate le soluzioni Prolog.

Tuttavia nel nostro progetto il Prolog non viene richiamato in maniera
intensiva, ma il suo utilizzo si limita alla parte della pipeline che esegue
l'analisi sintattica della frase inserita dal player. Per questo motivo, non
sussistono problemi di prestazione legati alla libreria utilizzata.

## ZIO

<!-- Perche zio e non cats-effect -->
Per quanto riguarda la gestione di side effect e azioni asincrone si è scelto di
utilizzare **ZIO**, una libreria che fornisce costrutti per la manipolazione di effetti
utilizzando un approccio funzionale, in maniera _type-safe_, quindi facilmente
componibili e testabili.

Il nucleo di ZIO è definito dal tipo `ZIO[R, E, A]`, nel quale: 
- `R` rappresenta l'ambiente necessario affinché l'effetto possa essere eseguito;
- `E` rappresenta il tipo dell'errore che la computazione potrebbe causare;
- `A` rappresenta il tipo di ritorno nel caso in cui l'effetto vada a buon fine.

Il tutto può essero vista come una versione con side-effect di una funzione `R
=> Either[E, A]`.

## Lens

Al fine di leggere e trasformare oggetti immutabili si è scelto di utilizzare la
libreria **Monocle**, in particolare il costrutto `Lens`, le quali metttono a
disposizione un'API semplice e componibile per modificare oggetti anche
innestati, senza dover ricorrere all'uso del metodo `copy`. La libreria fornisce
una macro `GenLens`, che consente la creazione di `Lens` a partire da una *case
class*, rendendo questa fase molto semplice.

L'uso di questi costrutti è risultato molto utile soprattutto nelle modifiche a
strutture quali `State` e `Room`.

<!-- todo riguardare il capitolo, vantaggi/svantaggi -->
