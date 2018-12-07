package gameboard;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../screens/start.fxml"));
        theStage.setTitle( "Pacman" );

        Group root1 = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        theScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        /*Canvas canvas = new Canvas( 1225, 600 );
        root.getChildren().add( canvas );
        GameManager gameManager = new GameManager(root);

        gameManager.drawBoard();

        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));*/

        theStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}