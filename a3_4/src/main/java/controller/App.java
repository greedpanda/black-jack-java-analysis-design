package controller;

import model.Game;
import model.rules.factory.EpicRulesFactory;
import view.gui.GuiLoader;

/**
 * Starts the application using the console.
 */
public class App {
  /**
   * Starts the game.

  * @param args Not used.
  */
  public static void main(String[] args) {

    GuiLoader gui = new GuiLoader();
    gui.initialize();

    /* // Code from CLI implementation
    RulesFactory rulesFactory = new EpicRulesFactory();  // Change to another one if you'd like Mr. Casino Owner.
    Game g = new Game(rulesFactory);
    Player ctrl = new Player(g, gui);

    while (ctrl.play()) {

    }*/
  }
}