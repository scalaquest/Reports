# Guide to the examples

## EscapeRoom

EscapeRoom is the first example created for ScalaQuest. The example is created
using mainly configuring builders and `Item`s given by the library.

In the following lines there is shown a little guide for playing this example,
explaining the steps to do in order to win the match.

After have red the initial message shown at the beginning, it's always
recommended typing the command `inspect` and send it pressing the Enter button.
Furthermore, this command is essential to understand the environment design and
content.

About sentences, articles are always optional, and adjectives also but just if
there aren't ambiguity.

Initially, you are in a basement. Inspecting the room there are:

- a _brown chest_
- an _iron hatch_
- a _rusty heavy crowbar_

You may try the command `open the hatch with the crowbar`, but it will be
ineffective. Sending `open the chest` will open it, without needing a chest key.
An `old rusty key` will appear in the room. Now the command
`open the hatch with the key` will work and you can go in the living room using
`go u`,`go up` or `enter the hatch`. Now you can go in the bathroom using
`go north`, but it will contain nothing! So go back, and you may want to eat an
apple... but which? `Eat red apple` will be a tasty action, while
`eat the green apple` will bring in a sad ending, because is poisoned. Anyway,
there is a big doorway, which is locked, so it needs some tool, like the chest
previously. So, neither `open the doorway` nor `open doorway with key`, because
it's not avalaible anymore. Going back in the basement, there is a crowbar
(maybe will be effective?). `go down` and `take crowbar` will bring it in your
bag, as you can see with `inspect bag`. This allows you to move the object in
another room. Go up again and `open the doorway with crowbar` will let you win.

## PokeQuest

Pokequest is the second example created for ScalaQuest. The example is created
using mainly custom `Item`s.

In the following lines there is shown a little guide for playing this example,
explaining the steps to do in order to win the match.

After have red the initial message shown at the beginning, it's always
recommended to type the command `inspect` and send it pressing the Enter button.
Furthermore, this command is essential to understand the environment design and
content.

About sentences, articles are always optional, and adjectives also but just if
there aren't ambiguity.

Initially, you are in Vermilion City. Inspecting the room there is a sleeping
snorlax. `inspect bag` will tell there are a pikachu, a pokeball, a pokeflute.

Snorlax is sleeping and blocking the way, but `wake snorlax` would be
ineffective and lead you to death. So, the pokeflute may be useful...

`play the pokeflute` or `wake snorlax with pokeflute` allows you to have the
free way, going north with `go n` and moving into the forest.

There is a Charizard into the forest! You should capture it!

With `catch charizard with pokeball` it could happen, but at first time it is
too strong, and in that case it would fly away.

This means it's Pikachu's turn!

`attack charizard with pikachu` is very effective, so don't do it again, or
Charizard will die!

Now `catch charizard with pokeball` will work and let you win the match!

## WizardQuest

WizardQuest is the last example given for the project. This makes use of custom
`Item`s and a custom `Ground`, showing the potentiality of the last construct,
mainly.

In the following lines there is a little guide for playing this example,
explaining the steps to take in order to win the match.

After reading the initial message, it's always recommended typing the command
`inspect` and send it pressing the Enter button. Furthermore, this command is
essential to understand the environment design and content.

About sentences, articles are always optional, and adjectives also but just if
there aren't ambiguity.

You are Harry Potter. And probably in the worst situation. Inspecting the room,
you discover that you are in the Chamber of Secrets, with Tom Riddle in the
front of you.

Tom has been subjugated a terrible basilisk and the poor Ginny Weasley is lying
in the ground. Fortunately the basilisk is blind, but you don't have any weapon
to kill it.

You have to run away, through a tunnel in direction East with the command
`go east`. The basilisk follows your sound, and you can't return to the Chamber.

With `inspect` you notice that there's a little stone in the ground behind you.
`throw the stone` to distract the basilisk. Now you can go back to the Chamber
with `go west`.

But the terrible basilisk is following you, so you have to find a sword or
something else to kill it! In the Chamber there is the sorting hat and
`look inside the hat` to find what you need.

Now `kill the basilisk with the gryffindor sword`!

The problems aren't over! Now you have to destroy Tom Riddle for ever (or
better, until next film). The basilisk hurts you, and a tooth of the basilisk
remains on the ground. To kill Tom you have to
`destroy the diary with the tooth`.
