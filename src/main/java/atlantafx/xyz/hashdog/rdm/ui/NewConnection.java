package atlantafx.xyz.hashdog.rdm.ui;

import atlantafx.base.theme.Dracula;
import atlantafx.base.theme.PrimerLight;
import atlantafx.sampler.theme.SamplerTheme;
import atlantafx.sampler.theme.ThemeManager;
import atlantafx.xyz.hashdog.rdm.ui.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class NewConnection extends Application {
    public static final String BASE_NAME = "atlantafx.xyz.hashdog.rdm.ui.i18n.messages";

    public static Locale DEFAULT_LOCALE = Locale.getDefault();
    public static ResourceBundle RESOURCE_BUNDLE=ResourceBundle.getBundle(BASE_NAME, DEFAULT_LOCALE);
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // find more themes in 'atlantafx.base.theme' package
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        Application.setUserAgentStylesheet(new Dracula().getUserAgentStylesheet());
//        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        // the rest of the code ...
//        stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/NewConnectionView.fxml"),RESOURCE_BUNDLE);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        //root面板作为最底层用于布局，需要设置为透明，否则会影响背景的显示，这里设置为0.1，用于调试，后续需要设置为0
//        root.setOpacity(0.1);调整为css设置了
        ThemeManager TM = ThemeManager.getInstance();
        TM.setScene(scene);
//        TM.setFontFamily(ThemeManager.DEFAULT_FONT_FAMILY_NAME );

        TM.setTheme(TM.getDefaultTheme());
        stage.show();


        TM.setFontFamily("Calibri" );
        TM.setFontFamily("Comic Sans MS" );
        TM.setFontFamily(ThemeManager.DEFAULT_FONT_FAMILY_NAME);
        TM.setTheme(new SamplerTheme(new Dracula()));

//        TM.setFontSize(8);
    }

    @Override
    public void init() throws Exception {
//        DEFAULT_LOCALE= new Locale("en", "US");
//        DEFAULT_LOCALE=Locale.JAPAN;
        DEFAULT_LOCALE=Locale.US;
        RESOURCE_BUNDLE=ResourceBundle.getBundle(BASE_NAME, DEFAULT_LOCALE);
    }
}