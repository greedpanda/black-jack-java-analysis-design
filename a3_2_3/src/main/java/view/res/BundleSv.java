package view.res;

import java.util.ListResourceBundle;

/**
 * Language bundle for Swedish.
 */
public class BundleSv extends ListResourceBundle {

  @Override
  protected Object[][] getContents() {
    String[] colors = {"Hjärter", "Spader", "Ruter", "Klöver"};
    String[] values = {"två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio",
        "knekt", "dam", "kung", "ess"};
    return new Object[][]{
          {"welcome", "Hej Black Jack Världen"},
          {"choice", "Skriv 'p' för att Spela, 'h' för nytt kort, 's' för att stanna 'q' för att avsluta\n"},
          {"of", " "},
          {"receives", " får..."},
          {"interruptedexception", "Tråden avbruten."},
          {"Player", "Spelare"},
          {"Dealer", "Croupier"},
          {"has", " har: "},
          {"score", "Poäng: "},
          {"gameover", "Slut: "},
          {"dealerwins", "Croupiern vann!"},
          {"playerwins", "Du vann!"},
          {"hidden", "Dolt Kort"},
          {"colors", colors},
          {"values", values},
          {"AmericanNewGameStrategy", "\nSpeltyp: Amerikanskt\n--> "
                  + "Spelaren får två kort. Croupiern får två kort; ett är dolt."},
          {"InternationalNewGameStrategy", "\nSpeltyp: Internationellt\n--> "
                  + "Spelaren får två kort. Croupiern får ett kort."},
          {"BasicHitStrategy", "\nCroupierstrategi: Normal\n--> "
                  + "Croupiern tar kort så länge hen har 16 poäng eller mindre."},
          {"Soft17HitStrategy", "\nCroupierstrategi: Mjuk 17\n--> "
                  + "Croupiern tar kort om hen har 16 poäng eller mindre, eller om hen har"
                  + " 17 poäng med ett ess som räknas som 11 poäng."},
          {"DealerTieWinStrategy", "\nTiebreak: Croupiern vinner\n--> "
                  + "Om det blir oavgjort utan att någon deltagare får över 21 poäng"
                  + " så vinner croupiern."},
          {"PlayerTieWinStrategy", "\nTiebreak: Spelaren vinner\n--> "
                  + "Om det blir oavgjort utan att någon deltagare får över 21 poäng"
                  + " så vinner spelaren."},
      };
  }
}