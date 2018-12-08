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
        Parent root = FXMLLoader.load(getClass().getResource("../screens/fxml/start.fxml"));
        theStage.setTitle( "Pacman" );

        Group root1 = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        theScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        theStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}