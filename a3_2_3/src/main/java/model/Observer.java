package model;

import model.Card;
import model.PlayerType;

/**
 * Encapsulates the observer functionality.
 */
public interface Observer {

  void updateNewCard(Card card, PlayerType receiver);

}
