package model;

import java.util.HashSet;

/**
 * Observer pattern for new cards received.
 */
class Observable {
  protected HashSet<Observer> observersNewCard = new HashSet<Observer>();

  void subscribeNewCard(Observer obs) {
    observersNewCard.add(obs);
  }

  void unsubscribeNewCard(Observer obs) {
    observersNewCard.remove(obs);
  }

  protected void notifyNewCard(Card card, boolean wasHidden) {
    PlayerType receiver;

    if (this.getClass().getSimpleName().equalsIgnoreCase(PlayerType.Player.toString())) {
      receiver = PlayerType.Player;
    } else {
      receiver = PlayerType.Dealer;
    }

    for (Observer obs : observersNewCard) {
      obs.updateNewCard(card, receiver, wasHidden);
    }
  }
}
