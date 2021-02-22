# Processo di sviluppo adottato

Il processo di sviluppo adottato rispecchia i principi della metodologia
**Scrum**, nel quale vengono definite un insieme di regole basate su principi
Agile.

La metodologia richiede l'assegnamento di specifici ruoli, che sono stati
distribuiti tra i componenti come di seguito specificato:

- A Filippo Nardini è stato assegnato il ruolo di **Product Owner**:
  responsabile per la massimizzazione del valore del progetto, in linea teorica
  dovrebbe esprimere i requisiti del cliente. Nel nostro caso, non avendo un
  vero e proprio committente, è stato assegnato lui il ruolo in quanto l'idea di
  fondo è stata proposta e delineata da esso;
- A Riccardo Maldini è stato assegnato il ruolo di **Scrum Master**. Il suo
  ruolo è quello di facilitare il lavoro di PO e team, innalzandosi a garante
  dei principi Scrum, e del delineare l'organizzazione di Sprint e meeting;
- Tutti i componenti sono stati assegnati al ruolo di **team di sviluppo**.
  Scrum prevede che i ruoli di PO e SM non debbano sovrapporsi con il team di
  sviluppo; è stata però di fatto una scelta obbligata, visto le dimensioni
  ridotte del team.

Il lavoro è stato suddiviso in **Sprint settimanali**, ad eccezione delle
primissime iterazioni che hanno richiesto del tempo aggiuntivo. La metodologia
ha portato il team a lavorare in maniera del tutto coordinata; questo ha portato
a un particolare giovamento sia per quanto riguarda l'affiatamento tra i
colleghi, sia per quanto concerne il corretto sviluppo del progetto.

## Strumenti a supporto di Scrum

### GitHub Projects

Si è tenuto traccia del Backlog di progetto grazie allo strumento **GitHub
Projects**. Questo rappresenta di fatto una versione di Trello interna a GitHub,
che ne eredita la maggior parte delle caratteristiche (ad esempio,
l'organizzazione dei task in liste), aggiungendo a esso però importanti
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
Issue e PR chiuse all'interno delle singole repository, e possibile ricostruire
interamente il processo di sviluppo. Un "indice" dei principali Issue e PR sono
contenuti all'interno del sopracitato documento di Scrum Overview (§ @sec:sod).

### Discord

Si è utilizzato il software **Discord** per effettuare i meeting settimanali e
quotidiani. Si è preferito questo strumento, rispetto ad altri simili quali
Slack, Microsot Teams, Google Meet ecc. sia per la buona qualità di
videochiamata che per la possibilità di implementare hook integrati con GitHub,
tali per cui ogni modifica alle repository di progetto viene notificata a tutti
i componenti del gruppo, tramite un apposito canale.

## Meeting e interazioni pianificate {#sec:meet}

### Meeting settimanali

Ad ogni fine settimana (di sabato, tipicamente) è stato portato avanti il
**meeting settimanale**, durante il quale si sono effettuate le operazioni di
Sprint Review, Retrospective e Planning. Nella pratica, tali meeting hanno
seguito la seguente scaletta:

1. **Brainstorming generale**: ogni componente ha aggiornato i colleghi riguardo
   ciò che è stato fatto durante lo Sprint precedente. Questo ha portato spesso
   a importanti discussioni su tematiche emerse dal lavoro di ogni membro del
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
   componenti del team, o ad eventuali team interni (§ @sec:team).

### Meeting di aggiornamento periodici

Inoltre, ogni due o tre giorni è stato effettuato un **meeting di
aggiornamento** tra tutti i componenti del team. Per ragioni pratiche, non è
stato possibile eseguire Daily Scrum giornalieri, sostituiti però da questi
meeting a cadenza più flessibile. Al pari del Daily Scrum, durante questi
meeting ci si è aggiornati riguardo i progressi attuati nei singoli team
interni.

Sulla base di questi meeting, si sono anche prese eventuali misure di revisione
in itinere dei task: è ad esempio successo che in alcuni casi un sotto-team
completasse tutti i task ad esso assegnati in anticipo, o che in alcuni altri
casi ci si rendesse conto che il task sarebbe stato concluso nello Sprint
successivo. In questi casi, si è aggiornato di conseguenza il Backlog.

## Organizzazione in team interni {#sec:team}

Il lavoro è stato sviluppato principalmente in team interni composti da due o
tre componenti, seguendo la metodologia **pair programming**. Questa modalità,
già utilizzata in altri progetti, pur portando iniziali rallentamenti dovuti a
una minore parallelizzazione,porta ad un innalzamento della qualità del
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
- Task di importanza chiave sono stati portati avanti **in comune tra tutti i
  componenti**;
- Task minori sono stati portati avanti **singolarmente** da componenti del
  team.

## Modelli di sviluppo

Durante lo svolgimento del progetto, non si è tenuto costantemente un singolo
modello di sviluppo:

### GitHub Flow in fase embrionale

In una prima fase, all'interno dei singoli repository si è adottato come modello
di sviluppo **GitHub Flow**. Tale modello, come intuibile dal nome, è ispirato a
GitFlow, ma presenta alcune caratteristiche in contrapposizione allo stesso, che
lo rendono più flessibile e semplice.

Il modello richiede ad esempio che la versione stabile del software sia
mantenuta su un branch **master** (o main), senza però la necessità di un branch
**dev** parallelo. Ciò ha permesso di avere nella fase iniziale un **flusso di
sviluppo meno rigido e più agile**: non avendo software abbastanza stabile da
poter essere rilasciato, né tantomeno pipeline di deploy attive, ci si è potuto
concentrare sullo sviluppo delle funzionalità di base. Allo stesso tempo, però,
GitHub Flow suggerisce di organizzare il lavoro in feature branch, come in
GitFlow, i quali confluiscono nel main a seguito di revisione.

### GitFlow a regime

Una volta predisposta una codebase sufficientemente stabile, e una volta
abilitate le pipeline di deploy, si è migrato al più strutturato modello
**GitFlow**. Questo permette di avere all'interno del branch **main** la
versione stabile, indipendente dal branch di lavoro principale, denominato
**dev**. I vari **feature branch** confluiscono ora nel branch dev. Il main
viene aggiornato tramite delle pull request sullo stesso originate da release (o
hotfix) branch, i quali in linea di massima dovrebbero partire dal dev. Pipeline
di CI e CD automatizzate permettono di gestire il rilascio del software e della
documentazione associata negli appositi spazi, e ne garantiscono qualità e
"rilasciabilità" ad ogni singolo push. Regole di protezione aggiuntive sono
inoltre state predisposte per il branch main.

## Strumenti di test, build e CI

Il progetto sfrutta **Gradle** come build automation tool. La scelta è dovuta
primo luogo in quanto richiesto per l'integrazione con il corso di LSS. Ciò è
comunque risultato molto utile per organizzare la build in maniera più
strutturata, e per approfondirne gli strumenti di integrazione con Scala. A tal
proposito, il codice è organizzato in più sotto-progetti, individuati a seguito
di un'iniziale sessione DDD.

È stato adottato per il testing del codice Scala il framework **ScalaTest**. Si
è inoltre sperimentato **WordSpec** come stile di test. Un'ulteriore libreria è
stata utilizzata per il testing del framework funzionale ZIO.

### Continuous Integration

Particolare attenzione è stata posta nell'individuazione di misure per
assicurare la qualità del codice. Pipeline a garanzia di Continuous Integration
e Quality Assurance sono state predisposte tramite il tool **GitHub Actions**,
con criteri di qualità man mano più stringenti e vincolanti, col crescere della
stabilità dei branch. In generale, il branch main non può essere modificato
senza che il codice passi tutti i controlli di CI/QA, e senza che la Pull
Request venga prima revisionata da un ulteriore componente del team, mentre per
il branch dev vengono generati warning in caso il codice non rispetti i
requisiti qualitativi proposti.

In primo luogo, ogni push o pull request genera un controllo tramite il tool
esterno **SonarCloud**, il quale definisce soglie qualitative basate su
coverage, manutenibilità, debito tecnico e molto altro. Sono stati introdotti
inoltre ulteriori controlli basati su delle **pipeline di CI custom**, nelle
quali viene effettuato il lint del codice tramite il plugin Spotless, poste
ulteriori condizioni di coverage, effetuati test e compilato il codice su
molteplici piattaforme.

Tutti gli accorgimenti vengono approfonditi in maniera più dettagliata nel
report di LSS.

### Automazione della delivery

Sono state inoltre predisposte delle pipeline per la generazione e il deploy
delle release, strutturate in maniera tale da rispettare i requisiti imposti da
GitFlow, apportandone importanti caratteristiche di automazione.

<!-- todo! -->
