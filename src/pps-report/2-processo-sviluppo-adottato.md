# Processo di sviluppo adottato

Il processo di sviluppo adottato rispecchia i principi della metodologia
**Scrum**, basata su un approccio di tipo Agile, con integrazioni nella fase
iniziale legate a un approccio di tipo DDD.

La metodologia Scrum richiede l'assegnamento di specifici ruoli, che sono stati
distribuiti tra i componenti come di seguito specificato:

- A Filippo Nardini è stato assegnato il ruolo di **Product Owner**:
  responsabile per la massimizzazione del valore del progetto, in linea teorica
  dovrebbe esprimere i requisiti del cliente. Nel nostro caso, non avendo un
  vero e proprio committente, è stato assegnato lui il ruolo in quanto l'idea di
  fondo è stata proposta e delineata dallo stesso;

- A Riccardo Maldini è stato assegnato il ruolo di **Scrum Master**. Il suo
  ruolo è quello di facilitare il lavoro di Project Owner e team, innalzandosi a
  garante dei principi Scrum, e nel delineare l'organizzazione di Sprint e
  meeting;

- A tutti i componenti è stato assegnato il ruolo di **team di sviluppo**. Scrum
  prevede che i ruoli di Project Owner e Scrum Master non debbano in linea di
  massima sovrapporsi con il team di sviluppo; è stata però di fatto una scelta
  obbligata, visto le dimensioni ridotte del team.

Il lavoro è stato suddiviso in **Sprint settimanali**, ad eccezione delle
primissime iterazioni che hanno richiesto del tempo aggiuntivo.

## Strumenti a supporto di Scrum

### GitHub Projects

Si è tenuto traccia del Backlog di progetto grazie allo strumento **GitHub
Projects**. Questo rappresenta di fatto una versione di Trello interna a GitHub,
che ne eredita la maggior parte delle caratteristiche (ad esempio,
l'organizzazione dei task in liste), aggiungendo ad esso però importanti
integrazioni con GitHub. È possibile ad esempio associare Issue e Pull Request
direttamente ai task, automatizzarne e sincronizzarne apertura e chiusura con
gli stessi. È possibile accedere alla backlog di progetto, essendo pubblica, a
[questo indirizzo](https://github.com/orgs/scalaquest/projects/1).

### Scrum Overview Document {#sec:sod}

Si è tenuto traccia dei meeting settimanali e dei progressi grazie alla
redazione di un documento denominato **Scrum Overview**, aggiornato dopo ogni
meeting, accessibile a
[questo indirizzo](https://scalaquest.github.io/Reports/reports/appendix.html).

### GitHub Issues e Pull Request

Per approfondire e delineare l'effettiva interazione e evoluzione del progetto,
sono stati utilizzati estensivamente Issue e Pull Request di GitHub. Consultando
le stesse, è possibile ricostruire interamente il processo di sviluppo. Un
"indice" dal quale recuperare i principali Issue e PR è contenuto all'interno
del sopracitato documento di Scrum Overview (@sec:sod).

### Discord

Si è utilizzato il software **Discord** per effettuare i meeting settimanali e
quotidiani. Si è preferito questo strumento, rispetto ad altri simili quali
Slack, Microsoft Teams, Google Meet o altri, per vari motivi:

- per la buona qualità di video-chiamata;
- per la possibilità di lavorare in stanze differenti in contemporanea, e
  passare agevolmente da una stanza all'altra;
- per la possibilità di implementare hook integrati con GitHub, tali per cui
  ogni modifica alle repository di progetto viene notificata a tutti i
  componenti del gruppo, tramite un apposito canale.

### Miro

Oltre agli strumenti citati, nella fase iniziale si è sfruttato estensivamente
anche un tool denominato **Miro**. Esso consiste di fatto in una board
collaborativa, che ci ha permesso di generare sketch analizzare i casi d'uso,
effettuare sedute di knoledge chrunching. La board, pubblicamente accessibile,
può essere consultata da
[questo link](https://miro.com/app/board/o9J_lfd9ZK0=/).

## Meeting e interazioni pianificate {#sec:meet}

### Meeting settimanali

Ad ogni fine settimana è stato portato avanti il **meeting settimanale**,
durante il quale si sono effettuate le operazioni di Sprint Review,
Retrospective e Planning. Nella pratica, tali meeting hanno seguito la seguente
scaletta:

1. **Retrospettiva**: ogni componente ha aggiornato i colleghi riguardo ciò che
   è stato fatto durante lo Sprint precedente. Questo ha portato spesso a
   importanti discussioni su tematiche emerse dal lavoro di ogni membro del
   team.

2. **Sincronizzazione del lavoro effettuato**: gran parte del lavoro è stato
   effettuato in branch separati (feature branch); grazie a una buona
   organizzazione, difficilmente il lavoro di un sotto-team è entrato in
   conflitto con quello di un altro. Ad ogni modo, tipicamente, al termine dello
   Sprint si è andati a chiudere le varie pull request aperte, gestendone
   eventuali conflitti minori.

3. **Definizione dei task dello Sprint successivo e aggiornamento del backlog**:
   a questo punto del meeting si vanno a definire gli obiettivi della settimana
   a venire, andando ad aggiornare di conseguenza il Backlog, trasferendo
   inoltre i task completati nello Sprint corrente nella colonna dei task
   completati negli Sprint precedenti. In questa fase si vanno inoltre a
   definire gli obiettivi prioritari, spostandoli nella colonna di Backlog dello
   Sprint corrente.

4. **Assegnamento dei task**: i task individuati vengono quindi assegnati ai
   componenti del team, o ad eventuali team interni (@sec:team).

### Meeting di aggiornamento periodici

Ogni due o tre giorni è stato effettuato un **meeting di aggiornamento** tra
tutti i componenti del team. Per ragioni pratiche, non è stato possibile
eseguire Daily Scrum giornalieri, sostituiti però da questi meeting a cadenza
più flessibile. Al pari del Daily Scrum, durante questi meeting ci si è
aggiornati riguardo i progressi attuati nei singoli team interni.

Sulla base di questi meeting, si sono anche prese eventuali misure di revisione
in itinere dei task: è ad esempio successo che in alcuni casi un sotto-team
completasse tutti i task ad esso assegnati in anticipo, o che in alcuni altri
casi ci si rendesse conto che il task sarebbe stato concluso nello Sprint
successivo. In questi casi, si è aggiornato di conseguenza il Backlog.

## Organizzazione in team interni {#sec:team}

Il lavoro è stato sviluppato principalmente in team interni composti da due o
tre componenti, seguendo la metodologia **pair programming**. Questa modalità,
già utilizzata in altri progetti, pur portando iniziali rallentamenti dovuti a
una minore parallelizzazione, porta ad un innalzamento della qualità del
prodotto, e ad un minore debito tecnico, in quanto le scelte vengono discusse e
revisionate in tempo reale dagli stessi membri del team. Essa porta inoltre i
diversi componenti a comprendere meglio la parte sviluppata, e responsabilizza i
singoli, dovendo a rotazione prendere il comando del team.

In particolare:

- Si è individuato un **sotto-team 1**, composto dai componenti Riccardo
  Maldini, Jacopo Corina e Thomas Angelini. Tale team è stato responsabile in
  particolare di aspetti relativi alla definizione del componente model
  (approfondito in seguito);
- Si è individuato un **sotto-team 2**, composto dai componenti Filippo Nardini
  e Francesco Gorini. Il team ha approfondito i concetti legati al parsing del
  testo, e all'interpretazione dello stesso tramite l'engine Prolog;
- Task d'importanza chiave sono stati portati avanti **in comune tra tutti i
  componenti**;
- Task minori sono stati portati avanti **singolarmente** da componenti del
  team.

## Strategie di Version Control

Durante lo sviluppo del progetto, non si è adottato sempre lo stesso modello di
sviluppo. Nelle prime fasi, durante le quali non si aveva del codice abbastanza
stabile da essere "rilasciabile", si è seguito un approccio più flessibile e
prototipale, denominato **GitHubFlow**, per poi evolvere il modello ad un più
strutturato **GitFlow**.

Questi aspetti, congiuntamente con ciò che viene riportato riguardo i flussi di
CI/QA e CD, sono stati approfonditi nel dettaglio nel report di LSS. Si rimanda
quindi a tale report per una panoramica più completa.

### GitHub Flow in fase embrionale

**GitHub Flow** è un modello di sviluppo ispirato a GitFlow, ma con alcune
caratteristiche che lo rendono più flessibile e semplice da porre in atto.

Il modello richiede ad esempio che la versione stabile del software sia
mantenuta su un branch `main` (o `master`), senza però la necessità di un branch
`dev` parallelo. Allo stesso tempo, però, GitHub Flow suggerisce di organizzare
il lavoro in `feature/*` branch, come in GitFlow, i quali confluiscono nel
`main`.

Alla luce di ciò, le prime iterazioni di progetto hanno presentato particolare
flessibilità sulle modalità di modifica del codice. Le varie feature sono state
sviluppate sui rispettivi `feature/*` branch, poi riversati nel `main` tramite
pull request. Si è subordinato la chiusura di queste alla revisione da parte di
un membro del team (solitamente, non appartenente allo stesso sub-team, così da
aggiornare l'altro team sui progressi di progetto) e al passaggio di determitati
workflow di CI e QA.

Unica deroga a questo flusso di lavoro, è stata posta per modifiche minori, tali
da non impattare sul funzionamento generale del codice (es. correzione di typo).
Per queste è stato permesso il push diretto sul branch `main`.

### GitFlow a regime

Una volta predisposta una codebase sufficientemente stabile, e una volta
abilitati i workflow di Continuous Delivery, si è migrato a un più strutturato
modello **GitFlow**. Questo permette di avere nel branch `main` la versione
ufficiale e stabile, sempre associata a una release. A ogni push nel `main` deve
corrispondere un tag annotato, associato a sua volta a un numero di versione. La
versione "di lavoro" del codice, parziale ma potenzialmente rilasciabile,
risiede nel branch `dev`.

I vari `feature/*` branch confluiscono ora tramite pull request in `dev`, con
gli stessi vincoli formulati per modello precedente (controlli di CI obbligatori
e revisione di un utente obbligatoria), e la stessa deroga per le modifiche
minori. In aggiunta, per una maggiore leggibilità e organizzazione del codice,
si è adottata una precisa politica di merge, che prevede che queste pull request
vengano chiuse tramite **squash and merge**, con un breve commento nel commit
che ne identifichi il changelog.

Il `main` viene aggiornato tramite delle pull request sullo stesso originate da
branch `release/X.Y.Z` (o `hotfix/X.Y.Z`), originati a loro volta dal `dev`; con
`X.Y.Z` si intende un numero di versione, formulato secondo le regole del
semantic versioning. Queste pull request presentano, oltre ai vincoli di
validazione visti per le precedenti (controlli di CI e revisione di un membro
del team obbligatoria), anche la necessità di presentare una coverage superiore
al 75% nei moduli _Core_ e _CLI_. Sono poi presenti degli accorgimenti ulteriori
per la delivery automatizzata degli asset, e la gestione dei tag, indicati in
@sec:chap6. Infine, una politica di merge ben precisa è adottata alla chiusura
di queste pull request, le quali richiedono un **merge commit** che riporti,
come commento del commit, un breve changelog[^1].

[^1]:
  È necessario far presente che alcuni problemi sono incorsi tra la release
  0.3.1 e 0.4.0, frangente nel quale, a seguito di un errore nelle politiche di
  commit, si è dovuto agire tramite rebase per preservare la repository. La
  storia tra questi due tag risulta quindi non perfettamente lineare.

## Strumenti di test, build e CI

Il progetto sfrutta **Gradle** come build automation tool. La scelta è dovuta
primo luogo in quanto richiesto per l'integrazione con il corso di LSS. Ciò è
comunque risultato molto utile per organizzare la build in maniera più
strutturata, e per approfondirne gli strumenti di integrazione con Scala. A tal
proposito, il codice è organizzato in più sotto-progetti, individuati a seguito
di un'iniziale sessione di Domain Driven Design.

È stato adottato per il testing del codice Scala il framework **ScalaTest**. Si
è inoltre sperimentato **WordSpec** come stile di test. **ZIO Test** è stata
utilizzata per il testing del framework funzionale ZIO.

### Continuous Integration e Quality Assurance

Particolare attenzione è stata posta nell'individuazione di misure per
assicurare la qualità del codice. Sono stati predisposti dei workflow a garanzia
di Continuous Integration e Quality Assurance, costruiti con il tool **GitHub
Actions**. Sono stati posti criteri di qualità man mano più stringenti e
vincolanti, a seconda del grado di stabilità del branch. In generale, `main` e
`dev` non possono essere modificati senza che il codice passi tutti i controlli
di CI/QA, e senza che la pull request venga prima revisionata da un ulteriore
componente del team. Per il branch `dev` non è necessaria la revisione di un
ulteriore membro, ma rimangono validi i controlli di CI/QA.

In primo luogo, ogni push o pull request genera un controllo tramite il tool
esterno **SonarCloud**, il quale definisce soglie qualitative basate su
coverage, mantenibilità, code smells, presenza di bug conosciuti e molto altro.
Sono presenti inoltre ulteriori controlli basati su **workflow CI/QA custom**,
nei quali viene effettuato il lint-styling del codice tramite il plugin
`spotless`, poste ulteriori soglie di coverage, effettuati test ed effettuata la
build del codice su molteplici piattaforme.

### Automazione della delivery

Sono state inoltre predisposte dei workflow per il deploy e il delivery delle
release, strutturate in maniera tale da rispettare i requisiti imposti da
GitFlow, apportandone importanti caratteristiche di automazione.

Nel momento in cui si desideri generare una release, il nostro flusso di lavoro
GitFlow-based prevede che venga generato un branch `release/X.Y.Z`, e che venga
aperta una pull request su `main` a partire da questa. Quanto detto è l'unica
operazione manuale da effettuare: una volta chiusa la pull request, revisionata
la stessa e passati i controlli di CI, un workflow genera il tag annotato della
versione, inferendolo dal nome del branch. Vengono quindi generati gli asset
collegati alla release, e resi disponibili sia nella
[sezione Release di GitHub](https://github.com/scalaquest/PPS-19-ScalaQuest/releases)
del progetto, che sulla repository pubblica Maven Central (modulo
[core](https://mvnrepository.com/artifact/io.github.scalaquest/core) e modulo
[cli](https://mvnrepository.com/artifact/io.github.scalaquest/cli)). Vengono
inoltre generati ScalaDoc, report di coverage e di test, resi disponibili
all'interno dello
[spazio web GH Pages associato al progetto](https://scalaquest.github.io/PPS-19-ScalaQuest).

Un meccanismo equivalente è stato sviluppato per la repository che ospita le
relazioni di progetto. Al momento della release, vengono generate le relazioni
(a partire da codice Markdown) in formato
[PDF LaTeX](https://github.com/scalaquest/Reports/releases/latest) e `HTML`
([pps report](https://scalaquest.github.io/Reports/pps-report/pps-report.html),
[lss report](https://scalaquest.github.io/Reports/lss-report/lss-report.html),
[appendix](https://scalaquest.github.io/Reports/appendix/appendix.html)),
tramite il tool Pandoc.
