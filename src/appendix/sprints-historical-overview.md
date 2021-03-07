# Sprints Historical Overview

In this document keep track of what we have done in the last Sprints (not the
current). It is an historical document linking the issues and pull request that
we opened or used in a given Sprints, so that we have an historical unique entry
point for the work we have done.

The typical Sprint in our organizaion should last a week, but the first ones
have a different duration as they are done into the holidays.

## Sprint 0 (21st December - 24th December)

This is not exactly a real Sprint, but a first basic footprint for the future
work (this is the reason for name "Sprint 0"). We made a lot of internal
meetings to define context bounds and do knowledge crunching.

Precisely, we started doing some vague knowledge chrunching sessions from the
submission of the project on the 6th of December, but we started making some
heavy effort from the 24th.

In addition, we set the foundations of the project, by giving the Gradle
Subproject Structure, setting up the test framework (ScalaTest) and starting
setting up the CI.

Useful links:

- [(Issue) Project skeleton, Gradle sub-projects and test infrastructure](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/1)
- [(Issue) CI infrastructure (Scala + Gradle)](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/3)
- [(Issue) Basic application structure: traits and relations](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/5)
- [(Issue) Check style in a split job in CI](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/11)

## Sprint 1 (24th December - 2nd January)

Here is how we really start the group work, splitting the team into two
sub-teams, each one working into a different part of the project. We proceeded
in parallel mostly, but we have done some intermediate meetings for integrate
parts and solve common problems. The two parts are in progress yet, and they
will be finished in the next Sprints. In addition, some minor tasks have been
finished during the Sprint.

Roles:

- **Team 1**: @maldins46, @corinz97, @ThomasAngeliniUnibo: in charge of
  developing the `Model` basics, and linked to it, also the `Interpreter` and
  `Resolver` components;
- **Team 2**: @lippo97, @francescogorini: in charge of developing the Prolog
  engine basics, and linked to it, also the `Parser` and `Lexer`components.

Useful links:

- [(Issue) Change Effect keyword to StateUpdate?](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/8)
- [(Issue) Spotless problems](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/13)
- [(Issue) Deepen model, interpreter and resolver component](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/7)
- [(Issue) Deepen Prolog engine, parser and lexer components](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/6)
- [(Issue) Sprint overview document](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/19)

## Sprint 2 (3rd January - 9th January)

In the second Sprint we basically continued the work started in the first one,
divided in teams. Tasks regarding model and Prolog engine are two of the most
funding part of the project, and as such they deserve particular attention. In
particular, we have carried on various meeting to take some decisions about how
to use the model in a more functional way, as described in the linked issue.

Useful links:

- [(Issue) Rename Update into Reaction](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/17)
- [(Issue) Deepen model, interpreter and resolver component](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/7)
- [(Issue) Deepen Prolog engine, parser and lexer components](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/6)

## Sprint 3 (10th January - 16th January)

In the third Sprint we basically continued the work started in the previous,
divided in teams. Tasks about Model and Engine basic structure are basically
finished. We handled merge conflicts about the two parts during the Sprint
Review. In addition, some minor tasks have been resolved in common meetings.

Useful links:

- [(Issue) Deepen model, interpreter and resolver component](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/7)
- [(Issue) Deepen Prolog engine, parser and lexer components](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/6)
- [(Issue) Improve Scaladoc format rules](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/29)

## Sprint 4 (17th January - 23rd January)

In this Sprint we closed the base structure tasks of the previous Sprint, and we
continued working divided in teams: we finished Model's ScalaDoc and
refactoring, and started a re-organization of the `ItemRef` concept, as an `AST.
Additionally, we started some minor tasks, as an automation structure for the
report, and CI optimization.

Useful links:

- [(Issue) Re-organize Model packages, ScalaDoc](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/35)
- [(Issue) Represent ItemRefs as an ADT](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/30)
- [(Issue) Report basic structure](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/20)
- [(Issue) CI optimization](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/23)
- [(Issue) Improve Scaladoc format rules](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/29)

## Sprint 5 (24th January - 30th January)

In this Sprint we continued working in teams, bringing up more other tasks.
**Team 1** revised the model, dealing with the concept of `MessagePusher` and
`Message`s, improving the ScalaDoc and making some refactoring. In the
meanwhile, **Team 2** started working in a more structured concept of
`ItemDescription` and `Verbs`, as a lexical abstraction for the game concepts.
In addition, the team refactored the `Prolog` generation and some of the related
concepts.

Furthermore, a task has been closed together, regarding a re-organization of the
reports in a Gradle subproject, with a dedicated formatter and Linter for
Markdown.

Useful links:

- [(Issue) Messages and MessagePusher implementation](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/47)
- [(Issue) Dictionary and Prolog Parser refactoring](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/48)
- [(Issue) Represent ItemRefs as an ADT](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/30)
- [(Issue) Reports basic structure](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/20)
- [(Pull request) Set up Markdown subproject](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/46)

## Sprint 6 (31st January - 6th February)

In this Sprint we continued working divided in groups. **Team 2** continued and
finished the dictionary module, a sort of initial "wrapper" used to generate
Prolog code initially. **Team 1** carried on various tasks regarding CI, QA
enhancement, release generation, and created a bot for dependency update.

In addition, reports and documentation now are located in a separate repository,
and a new page for the project description has been deployed.

Useful links:

- [(Issue) Dictionary and Prolog Parser refactoring](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/48)
- [(Issue) Review and refactor model](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/66)
- [(Issue) Set up delivery and deployment](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/22)
- [(Issue) Further QA features](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/52)
- [(Issue) Reports in a separate repository](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/54)
- [(Issue) Dependabot](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/64)
- [(Pull Request) Release with GitFlow and semantic versioning](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/63)

## Sprint 7 (7th February - 13th February)

In this Sprint we continued working divided in groups. **Team 1** we
re-organized the commons package, by splitting common reactions from the
behaviors, we added some utility builders, and worked hard on the CI, by
enabling a well-defined release workflow (with automatic scalaDoc generation and
deploy at the end of the process), and by enabling coverage threshold, both on
GH Actions and with the QA tool SonarCloud. **Team 2** worked into the CLI, by
enabling a specific test framework for ZIO. In addition, they worked into the
resolver, in order to manage ambiguous description. Then, in a common meeting
with all members, we work into the Escape Room example, by creating a real story
with our framework. We also created our first stable releases.

Useful links:

- [(Pull request) Release 0.2.0](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/89)
- [(Pull request) Common reactions and builder-based behaviors](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/87)
- [(Pull request) Escape room tests](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/86)
- [(Pull request) Allow resolver to better manage ambiguous descriptions](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/79)
- [(Pull request) Generate html on release workflow](https://github.com/scalaquest/reports/pull/13)
- [(Issue) Better Model API and DSL for the Storyteller](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/66)
- [(Pull request) Minor workflow optimizations, readme badges](https://github.com/scalaquest/reports/pull/83)
- [(Pull request) Sonarqube Scoverage integration](https://github.com/scalaquest/reports/pull/77)
- [(Pull request) Improve CLI prompt](https://github.com/scalaquest/reports/pull/80)

## Sprint 8 (14th February - 20th February)

The sprint 8 started with an important refactor of the model, carried out
jointly from all members of the team. This was an important modification,
regarding the split between `State` and `Message`s, that are separate entities
actually, involved at different abstraction levels. In this joint group
configuration, we also finished the second example, called PokeQuest. In the
second part of the week, resuming the sub-team organization, we finished some
minor tasks like a re-organization of the Reports' CI and the common package
based on the new State-Message configuration (in **Team 1**), and a
refactoring/scaladoc addition for parser`and`dictionary` packages (in **Team
2**).

Useful links:

- [(Pull request) Remove messages from the state](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/102)
- [(Pull request) PokeQuest example](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/105)
- [(Issue) Compilation warnings](https://github.com/scalaquest/PPS-19-ScalaQuest/issue/95)
- [(Pull request) Add ScalaDoc, CommonReactions cleaning](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/108)
- [(Pull request) Various commons additions](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/97)
- [(Pull request) Completed tests](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/103)
- [(Issue) Dictionary and Prolog Parser refactoring](https://github.com/scalaquest/PPS-19-ScalaQuest/issue/97)
- [(Issue) PDF for appendix documents, yaml index](https://github.com/scalaquest/Reports/issues/12)

# Sprint 9 (21st February - 27th February)

In Sprint 9 we focused on reports, in particular into the PPS one. The plan was
to join forces reorganizing pre-existent documentation produced throughout the
duration of the project into a single report, and finish it. The initial plan
was to work in sub-teams: from Monday to Wednesday the Team 1 should have worked
on chapters 1, 2, 3, meanwhile @scalaquest/team-2 on 4,5,7. Then, in the second
part of the week we should have switched roles, in order to complete, review and
integrate the different parts. Due to various problems related to lessons and
personal problems of the members, the second phase was prolonged, and continued
until the next Sprint with both teams focusing on the last chapters. In
addition, a major feature and some minor task has been completed, such as the
enabling of continuous delivery on Maven Central, enabling of a development
prerelease delivery system, and some minor adjustments.

Useful links:

- [(Issue) Focus on PPS report](https://github.com/scalaquest/Reports/issues/18)
- [(Pull request) Add how-to-use guide to the Readme](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/112)
- [(Issue) Auto generate prerelease on each dev push](https://github.com/scalaquest/PPS-19-ScalaQuest/issue/110)
- [(Pull request) Release to Maven Central](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/113)

# Sprint 10 (28th February - 6th Februrty)

In Sprint 10 we focused onto finish the PPS report. We finished it at the end of
the Sprint. In parallel, we started working on the LSS report, we finished the
last example WizardQuest, and we finished some minor modifications to the CI. We
are planning to close the works in the next Sprint.

Useful links:

- [(Issue) Error in navigation with Lens](https://github.com/scalaquest/PPS-19-ScalaQuest/issues/114)
- [(Pull request) WizardQuest example](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/116)
- [(Pull request) Release 0.3.1](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/117)
- [(Issue) Bug in EscapeRoom](https://github.com/scalaquest/PPS-19-ScalaQuest/issue/119)
- [(Pull request) More builders](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/120)
- [(Pull request) LSS report structure](https://github.com/scalaquest/Reports/pull/29)
- [(Pull request) Refactoring CLI component and minor fixes](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/122)
- [(Pull request) Enhance release flows](https://github.com/scalaquest/PPS-19-ScalaQuest/pull/122)
- [(Issue) Pandoc CD fixes](https://github.com/scalaquest/Reports/issues/30)
- [(Pull request) PPS revision](https://github.com/scalaquest/Reports/pull/33)
- [(Pull request) LSS report](https://github.com/scalaquest/Reports/pull/34)
