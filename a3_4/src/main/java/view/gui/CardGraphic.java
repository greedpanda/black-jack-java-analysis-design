package view.gui;

import javafx.scene.image.Image;
import model.Card;

/**
 * Card graphic class.
 */
class CardGraphic {

  private Image[][] cardsGraphic = new Image[13][4];
  private Image backGraphic;
  private Image blankCard;
  boolean loadingComplete = false;

  /**
   * Loads the cards graphic including empty card and card back.
   */
  void loadCardGraphic() {

    if (!loadingComplete) {
      for (int colIx = 0; colIx < Card.Color.Count.ordinal(); colIx++) {
        for (int valIx = 0; valIx < Card.Value.Count.ordinal(); valIx++) {
          String file = "/" + Card.Value.values()[valIx].value + Card.Color.values()[colIx].color + ".gif";
          cardsGraphic[valIx][colIx] = new Image(file);
        }
      }
      backGraphic = new Image("/BK.gif");
      blankCard = new Image("/BL.gif");
      loadingComplete = true;
    }
  }

  /**
   * Fetches the image of the card.
   *
   * @param card Card referenced for image.
   * @return The relative card image.
   */
  Image getImage(Card card) {
    if (card.getColor() == Card.Color.Hidden) {
      return backGraphic;
    } else {
      return cardsGraphic[card.getValue().ordinal()][card.getColor().ordinal()];
    }
  }

  Image getBackGraphic() {
    return backGraphic;
  }

  Image getBlankCard() {
    return blankCard;
  }

}
