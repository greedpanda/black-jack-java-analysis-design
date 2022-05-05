package view.res;

import java.util.ListResourceBundle;

/**
 * Language bundle for English.
 */
public class BundleEn extends ListResourceBundle {

  @Override
  protected Object[][] getContents() {
    return new Object[][]{
          {"welcome", "Hello Black Jack World"},
          {"choice", "Type 'p' to Play, 'h' to Hit, 's' to Stand or 'q' to Quit\n"},
          {"of", " of "},
          {"receives", " receives..."},
          {"interruptedexception", "Thread interrupted."},
          {"Player", "Player"},
          {"Dealer", "Dealer"},
          {"has", " has: "},
          {"score", "Score: "},
          {"gameover", "GameOver: "},
          {"dealerwins", "Dealer Won!"},
          {"playerwins", "You Won!"},
          {"AmericanNewGameStrategy", "\nNew game type: American\n--> The player receives two cards."
                  + " The dealer receives two cards, but the second is hidden.\n"},
          {"InternationalNewGameStrategy", "\nNew game type: International\n--> The player receives two cards."
                  + " The dealer receives one card.\n"},
          {"BasicHitStrategy", "Dealer hit strategy: Basic\n--> The dealer will hit if they have 16 points or less.\n"},
          {"Soft17HitStrategy", "Dealer hit strategy: Soft17\n--> The dealer will hit if they have 16 points or less,"
                  + " or if has 17 points with an ace counting as 11 in hand.\n"},
          {"DealerTieWinStrategy", "Winner on tie: Dealer\n--> If the player and the dealer have the same"
                  + " score and it is below 22, the dealer wins.\n"},
          {"PlayerTieWinStrategy", "Winner on tie: Player\n--> If the player and the dealer have the same"
                  + " score and it is below 22, the player wins.\n"},
      };
  }
}