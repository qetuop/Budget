/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author Brian
 */
public class Budget extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        Scene scene;
        System.out.println("Budget::1");
        root = FXMLLoader.load(getClass().getResource("MainAppView.fxml"));
        System.out.println("Budget::2");
        scene = new Scene(root);
        System.out.println("Budget::3");
        primaryStage.setScene(scene);
        System.out.println("Budget::4");
        primaryStage.show();
        System.out.println("Budget::5");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
