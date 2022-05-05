package model.rules;

import model.rules.hit.BasicHitStrategy;
import model.rules.hit.Soft17HitStrategy;
import model.rules.newgame.AmericanNewGameStrategy;
import model.rules.newgame.InternationalNewGameStrategy;
import model.rules.win.DealerTieWinStrategy;
import model.rules.win.PlayerTieWinStrategy;

/**
 * For visiting model.rules subpackages. If a new strategy is added, it should receive its own method here.
 */
public interface Visitor {

  void visit(BasicHitStrategy s);

  void visit(Soft17HitStrategy s);

  void visit(AmericanNewGameStrategy s);

  void visit(InternationalNewGameStrategy s);

  void visit(DealerTieWinStrategy s);

  void visit(PlayerTieWinStrategy s);

}
