---
autoSectionLabels: true
title: Pandoc cross-ref demo
author: Scalaquest group
numbersections: true
---

# Introduzione

L'obiettivo di questo progetto è quello di realizzare un framework per
l'implementazione di giochi del genere Interactive Fiction (come ad esempio
Zork), nel quale il giocatore può utilizzare comandi di testo per influenzare
l’ambiente e proseguire nel gioco.

Il framework metterà a disposizione una parte principale definita "core"
attraverso la quale è possibile gestire i comandi inseriti dall'utente e, di
conseguenza, modificare lo stato di gioco.

Il giocatore si interfaccerà attraverso una CLI, senza escludere una possibile
soluzione che utilizzi un'altra tecnologia come ad esempio un'interfaccia web.

Inoltre, all'interno del progetto sarà presente un Domain Specific Language per
permettere di creare nuove storie giocabili. Alcuni di queste saranno
implementate come esempio allo scopo di dimostrare il corretto funzionamento di
ogni parte del sistema.

Il progetto verrà realizzato interamente seguendo la metodologia di sviluppo
Agile con alcuni elementi di Devops, tra cui:

- struttura Gradle multiprogetto;
- sistema di QA automatizzato;
- sistema di CI.

In aggiunta verrà svolta in fase di design una parte di Domain Driven Design
comprendente diverse sessioni di knowledge crunching, praticolarmente utili al
fine di uniformare le idee di ciascun membro del team e ad innalzare la qualità
della fase di sviluppo.

In corso d'opera verranno considerati ulteriori sviluppi, come ad esempio la
creazione di una interfaccia web oppure il rilascio degli artefatti su Maven
Central Repository.
