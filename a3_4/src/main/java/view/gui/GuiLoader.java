package view.gui;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gui loader, scene builder.
 */
public class GuiLoader extends Application {
  private boolean hasLaunched = false;

  /**
   * Gui initialization.
   */
  public void initialize() {
    if (!hasLaunched) {
      launch();
      hasLaunched = true; // never reached.
    }
  }

  /**
   * Builds the scene for the gui.

   * @param primaryStage main stage.
   * @throws Exception if fxml not found.
   */
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Gui.fxml")));
    primaryStage.setTitle("Black Jack World");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
