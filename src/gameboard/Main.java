package gameboard;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the game
 */
public class Main extends Application {

    /**
     * Creates the window which shows the first screen of the game which is the start page, where player has to enter username
     * @param theStage The window where the screen will be displayed
     * @throws Exception FXMLLoader throws an exception
     */
    @Override
    public void start(Stage theStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../screens/start.fxml"));
        theStage.setTitle( "Pacman" );

        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        theScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        theStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}