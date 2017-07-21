package edu.cnm.deepdive.security;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuardUi extends Application {

  private static final String STYLES_SHEET = "/styles/application.css";
  private static final String LAYOUTS_RESOURCE = "/layouts/Generate.fxml"; //front slash means "go back to classpath root - package"
  private static final String UI_BUNDLE = "resources/ui"; // no front slash means 'start in current classpath.'
  private static final String WINDOW_TITLE_KEY = "window.title";
  
  @FXML 
  private VBox passwordOptions;
  @FXML
  private VBox passphraseOptions;
  @FXML
  private CheckBox upperIncluded;
  @FXML
  private CheckBox lowerIncluded;
  @FXML
  private CheckBox punctuationIncluded;
  @FXML
  private CheckBox ambiguousExcluded;
  
  private SecurePasswordGenerator passwordGenerator = new SecurePasswordGenerator();
  

  @Override
  public void start(Stage primaryStage) throws Exception {
    ResourceBundle uiBundle = ResourceBundle.getBundle(UI_BUNDLE);
    Pane pane = FXMLLoader.load(getClass().getResource(LAYOUTS_RESOURCE), uiBundle);
    Scene scene = new Scene(pane, 400, 400);
    scene.getStylesheets().add(getClass().getResource(STYLES_SHEET).toExternalForm());
    primaryStage.setScene(scene);
    primaryStage.setTitle(uiBundle.getString(WINDOW_TITLE_KEY));
//    upperIncluded.setSelected(passwordGenerator.isUpperCaseIncluded());
//    lowerIncluded.setSelected(passwordGenerator.isLowerCaseIncluded());
//    punctuationIncluded.setSelected(passwordGenerator.isPunctuationIncluded());
//    ambiguousExcluded.setSelected(passwordGenerator.isAmbiguousExcluded());


    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }

}
