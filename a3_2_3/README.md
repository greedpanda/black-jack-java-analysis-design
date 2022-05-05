# Assignment 3 - Grade 3
##### Albert Henmyr, Atakan Coban, Paolo Molinaro 

## 1. Application purpose

This Java console application is developed with the purpose of having a
playable Black Jack game with a "modular set of rules".

_Note: Since a number of classes didn't change since the initial
implementation we will skip the description of those, and we will focus only on the changes._

## 2. Rules

In the implementation the modular set of rules is composed in the **RulesFactory** Class, 
A visitor pattern will compose the set of rules combining the 3 strategy available.
In the current implementation the **EpicRulesFactory** and **LesserEpicRulesFactory** are alternative to choose from.

**EpicRulesFactory** consists of:  American New Game, Soft 17 Hit, Dealer Tie Win.
**LesserEpicRulesFactory** consists of: International New Game, Basic Hit, Player Tie Win.

Therefore, the strategies and rules details available in the implementation are:

- New Game Strategy, defining the rules of the game. 
  In the current version of the application the New Game rules available are:
    - American, where the dealer is dealt a second card hidden.
    - International, where the dealer is dealt only one card.
  
- Hit Strategy, defining the dealer strategy to hit.
  In the current version of the application the Hit strategies available are:
  - Basic, where the dealer hits only if the score is less than 17.
  - Soft 17, where the dealer hits if the score is less than 17, or it's equal to 17 having an ace combined with 6 points in hand.
  
- Win Strategy, defining the winning conditions.
  In the current version of the application the Win conditions available are:
    - Player Tie, in which a tie score makes the player wins.
    - Dealer Tie, in which a tie score makes the dealer wins.

## 3. Model changes

The model is composed of: Game, Deck, Card, Player,
 Dealer, PlayerType, Observable and Observer (and the package rules already described previously).

_**Card** and **Deck** had no changes._

In the **Player** class the only change made was the decomposition of the method for score calculation,
now a method calculates the "raw score": that is the score without applying the
reduction of the value of the Ace (that is used for the implementation of the Soft 17 Hit Strategy).
Therefore, that method is fed into the score calculation already in place where the reduction of the
value of the Ace can take place according to the rules.

In the **Dealer** class the changes made, beside the implementation of the Stand method, were:
- the Hit method that now takes a new parameter (card is hidden: true/false) to facilitate the refactor necessary due to
duplication in code when it came to "give a card to a player" (hit).
- The isDealerWinner that now leans on the **WinStrategy** of the **RulesFactory**

In the **Game** class it was necessary to add the subscribe method needed for the observer patter
to subscribe the observer to the **Player** and **Dealer**.

The class **Observable** was created to function as a superclass to all the observable concrete objects,
in our case the **Player** and **Dealer**.
It has methods to subscribe, unsubscribe and notifyNewCard all the subscribed **Observers**.

The **Observer** interface was created to realize all the observers needed for the observer pattern
(all the views subscribed to the updates).

The **PlayerType** enumeration helps to communicate the receiver of a new card dealt.

## 4. View changes

The view is composed of:
- Resource package with language bundles.
- View interface.
- InternationalView and RulesVisitor classes.
- PlayerOption enumeration.

The **PlayerOption** enumeration was created to solve the hidden dependency between the controller and the view.

The **InternationalView** is introduced to resolve the code duplication that was present having two views just with different translations of the messages.
The bundle package contains the languages objects that are implemented. The improvement to remove hidden dependencies and the observer pattern are still in place, plus the RulesVisitor class will aid the communication of the rules selected when the game starts.

## 5. Planned features
- implementation of GUI.
