package atlantafx.xyz.hashdog.rdm.ui;

import atlantafx.xyz.hashdog.rdm.ui.controller.MainController;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main  extends Application {
    public static final String BASE_NAME = "atlantafx.xyz.hashdog.rdm.ui.i18n.messages";

    public static Locale DEFAULT_LOCALE = Locale.getDefault();
    public static ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle(BASE_NAME, DEFAULT_LOCALE);
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/MainView.fxml"),RESOURCE_BUNDLE);
        fxmlLoader.setControllerFactory(c -> new MainController(stage));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        //root面板作为最底层用于布局，需要设置为透明，否则会影响背景的显示，这里设置为0.1，用于调试，后续需要设置为0
//        root.setOpacity(0.1);调整为css设置了

        stage.show();
        Node menuBar = root.lookup("#topMenuBar");

        menuBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        menuBar.setOnMouseDragged(event -> {
            root.setOpacity(0.8);
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        menuBar.setOnMouseReleased(e->{
            root.setOpacity(1);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}