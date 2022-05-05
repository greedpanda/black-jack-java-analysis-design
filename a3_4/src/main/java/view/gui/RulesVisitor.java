package view.gui;

import java.util.ResourceBundle;
import model.rules.Visitor;
import model.rules.hit.BasicHitStrategy;
import model.rules.hit.Soft17HitStrategy;
import model.rules.newgame.AmericanNewGameStrategy;
import model.rules.newgame.InternationalNewGameStrategy;
import model.rules.win.DealerTieWinStrategy;
import model.rules.win.PlayerTieWinStrategy;
import view.Language;

/**
 * A Visitor that inspects the game rules and returns localized explanations of them.
 */
public class RulesVisitor implements Visitor {

  private ResourceBundle resourceBundle;
  private String newGameRules = "";
  private String hitRules = "";
  private String tieRules = "";

  public RulesVisitor(Language language) {
    this.resourceBundle = ResourceBundle.getBundle("view.res.Bundle" + language.str);
  }

  public String getNewGameRules() {
    return newGameRules;
  }

  public String getHitRules() {
    return hitRules;
  }

  public String getTieRules() {
    return tieRules;
  }

  public void visit(BasicHitStrategy s) {
    this.hitRules = resourceBundle.getString("BasicHitStrategy");
  }

  public void visit(Soft17HitStrategy s) {
    this.hitRules = resourceBundle.getString("Soft17HitStrategy");
  }

  public void visit(AmericanNewGameStrategy s) {
    this.newGameRules = resourceBundle.getString("AmericanNewGameStrategy");
  }

  public void visit(InternationalNewGameStrategy s) {
    this.newGameRules = resourceBundle.getString("InternationalNewGameStrategy");
  }

  public void visit(DealerTieWinStrategy s) {
    this.tieRules = resourceBundle.getString("DealerTieWinStrategy");
  }

  public void visit(PlayerTieWinStrategy s) {
    this.tieRules = resourceBundle.getString("PlayerTieWinStrategy");
  }
}
