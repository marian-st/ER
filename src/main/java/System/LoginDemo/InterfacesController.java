package System.LoginDemo;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class InterfacesController {
    private HashMap<String, Pane> interfaces = new HashMap<>();
    private Stage main;

    public InterfacesController(Stage stage) {
        this.main = stage;
    }

    public void addInterface(String name, Pane pane){
        interfaces.put(name, pane);
    }

    public void activate(String name, String title){
        main.setTitle(title);
        main.setScene(new Scene(interfaces.get(name)));
        main.sizeToScene();
    }
}
