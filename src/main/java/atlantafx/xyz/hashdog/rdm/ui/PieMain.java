package atlantafx.xyz.hashdog.rdm.ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class PieMain extends Application {

    public static final String BASE_NAME = "atlantafx.xyz.hashdog.rdm.ui.i18n.messages";

    public static Locale DEFAULT_LOCALE = Locale.getDefault();
    public static ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle(BASE_NAME, DEFAULT_LOCALE);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/PieView.fxml"),RESOURCE_BUNDLE);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
    }
}