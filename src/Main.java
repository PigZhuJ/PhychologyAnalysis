import Utils.ViewLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewLoader.showStage(null, "/View/Login.fxml", "Login", new Stage());

    }
    public static void main(String[] args) {
        launch(args);
    }
}
