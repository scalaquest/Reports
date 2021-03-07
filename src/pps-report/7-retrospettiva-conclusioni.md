# Retrospettiva

Rispetto a progetti trattati in precedenza dai singoli membri del gruppo, in
questo si è posta un'attenzione particolare alla metodologia e
all'organizzazione di progetto. Si è cercato per quanto possibile di approcciare
lo sviluppo con mentalità ingegneristica, pragmatica e strutturata.

## Il backlog

Per il backlog di progetto è stato adottato GitHub Projects, un'alternativa a
Trello che presenta una forte integrazione delle funzionalità di GitHub. Ragion
per cui la quasi totalità dei task è associata a un issue o una pull request,
tenendo traccia in maniera assolutamente trasparente del processo che ha portato
alla risoluzione degli stessi. È possibile accedere alla board pubblica
[da questo link](https://github.com/orgs/scalaquest/projects/1). La board è
organizzata in diverse colonne:

- Una colonna **Backlog** tiene traccia dei task che si è programmato di
  svolgere in futuro, con priorità inferiore rispetto allo Sprint corrente. Essa
  viene popolata in particolare durante lo Scrum meeting settimanale, nella
  definizione degli obiettivi di lungo termine;

- Una colonna **Backlog (current Sprint)** tiene traccia dei task da svolgere
  nello Sprint corrente. Essa viene popolata principalmente durante lo Scrum
  meeting settimanale, permettendo una pianificazione dei task a maggiore
  priorità. Inoltre, nel caso in cui uno dei team riuscisse a terminare tutti i
  task ad esso assegnati durante lo Sprint, può aggiungerne degli altri,
  accingendo da quelli del backlog;

- Una colonna **In progress (current Sprint)** tiene traccia dei task ai quali
  dei membri stanno attualmente lavorando;

- Una colonna **Done (current Sprint)** tiene traccia dei task conclusi nello
  Sprint corrente. Dei trigger di automazione permettono di porre in automatico
  i task in questa colonna a seguito della chiusura di issue e al merge di pull
  request;

- Una colonna **Done** tiene traccia dei task completati negli Sprint
  precedenti. La colonna viene popolata a seguito della terminazione dello
  Sprint, accingendo dai task terminati nello stesso.

Tale organizzazione non ha dato spazio ad equivoci, rendendo possibile
monitorare in maniera continuativa l'operato del team, ed effettuare eventuali
aggiustamenti in corso d'opera.

## Organizzazione in Sprint

Il lavoro si è svolto in Sprint settimanali, intermezzati da meeting portati
avanti principalmente nel fine settimana, durante la quale si sono svolte le
fasi di Retrospective e Planning. Durante ogni meeting si è andato ad aggiornare
un documento di overview, tale da tener traccia di ciò che è stato
effettivamente fatto durante lo Sprint, completo di link alle pull request e
agli issue ad essi collegati. Il documento è accessibile tra la documentazione
di progetto, tra i documenti di appendice, consultabili
[via web](https://scalaquest.github.io/Reports/docs/appendix.html) o come
[LaTeX PDF](https://github.com/scalaquest/Reports/releases/latest).

Questo documento permette di ricostruire ciò che effettivamente è stato fatto ad
ogni Sprint. Da questo si può anche notare come all'atto pratico alcuni task
abbiano sforato il periodo di Sprint previsto, e come alcuni altri siano stati
invece terminati in anticipo, dando spazio al team di occuparsi di task
aggiuntivi. Ciò non ha però compromesso in maniera eccessiva lo svolgimento del
progetto. Ritardi e anticipi sui tempi previsti sono stati gestiti in itinere,
adattando incrementalmente l'organizzazione sulla base dei problemi riscontrati.

## Processo di sviluppo

In generale, la fase implementativa è risultata essere particolarmente lineare
ed efficace, comparata con altri progetti portati avanti in passato.

Particolare importanza è stata assunta in questo contesto anche dalle sessioni
preliminari di knowledge crunching, nelle quali si son potuti mettere a nudo in
anticipo buona parte degli aspetti architetturali, dei requsiti e delle
problematiche legate al progetto.

In aggiunta, è risultata essenziale l'infrastruttura di automazione adottata. La
messa in atto di workflow efficaci di Contiuous Integration, Quality Assurance,
Linting del codice sorgente, combinati a sessioni di refactoring periodiche, ha
permesso di individuare facilmente nel nascere e risolvere problematiche legate
all'accumularsi di debito tecnico e alla qualità del codice.

## Commenti finali

In conclusione, possiamo affermare che il progetto finale soddisfa in ogni parte
i requisiti definiti in fase di analisi. Questo ha portato a grande
soddisfazione da parte dei membri del team, in quanto raggiungere gli obiettivi
prefissati a pieno non era considerato affatto scontato nelle fasi iniziali.

Il livello di qualità del codice è da considerarsi buona per la maggior parte
delle sezioni, avendo raggiunto al contempo il livello di espressività
inizialmente prefissato. Ciò si rispecchia nella semplicità (sia di utilizzo
delle API, sia per la presenza di elementi di base) con cui un utente "story
teller" è in grado di creare storie più o meno complesse.

Come membri del team ci teniamo inoltre ad aggiungere che nessuno di noi aveva
in precedenza lavorato ad un progetto con un'organizzazione e una metodologia
strutturata. Due membri del team provengono poi da una triennale differente dal
percorso degli altri (Riccardo Maldini e Francesco Gorini, UniUrb), la quale
proponeva un approccio ai progetti ben più approssimativo. Una sfida che si è
accettata di buon grado, coscienti delle potenzialità dei singoli.
