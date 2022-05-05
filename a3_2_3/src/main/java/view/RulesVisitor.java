package view;

import java.util.ResourceBundle;
import model.rules.Visitor;
import model.rules.hit.BasicHitStrategy;
import model.rules.hit.Soft17HitStrategy;
import model.rules.newgame.AmericanNewGameStrategy;
import model.rules.newgame.InternationalNewGameStrategy;
import model.rules.win.DealerTieWinStrategy;
import model.rules.win.PlayerTieWinStrategy;

/**
 * A Visitor that inspects the game rules and prints localized explanations of them.
 */
public class RulesVisitor implements Visitor {

  private ResourceBundle resourceBundle;

  public RulesVisitor(InternationalView.Language language) {
    this.resourceBundle = ResourceBundle.getBundle("view.res.Bundle" + language.str);
  }

  public void visit(BasicHitStrategy s) {
    System.out.println(resourceBundle.getString("BasicHitStrategy"));
  }

  public void visit(Soft17HitStrategy s) {
    System.out.println(resourceBundle.getString("Soft17HitStrategy"));
  }

  public void visit(AmericanNewGameStrategy s) {
    System.out.println(resourceBundle.getString("AmericanNewGameStrategy"));
  }

  public void visit(InternationalNewGameStrategy s) {
    System.out.println(resourceBundle.getString("InternationalNewGameStrategy"));
  }

  public void visit(DealerTieWinStrategy s) {
    System.out.println(resourceBundle.getString("DealerTieWinStrategy"));
  }

  public void visit(PlayerTieWinStrategy s) {
    System.out.println(resourceBundle.getString("PlayerTieWinStrategy"));
  }
}
