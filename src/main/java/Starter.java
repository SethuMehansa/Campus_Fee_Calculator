import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Event-Driven UI initialization happens here
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/view/MainForm.fxml") // FIXED PATH
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Saegis Fee Calculator");
        stage.setScene(scene);
        stage.show();
    }
}