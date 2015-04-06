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
    
    // ? this is the main data store ?
    private UserData userData = new UserData();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root;
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Budget.class.getResource("MainAppView.fxml"));
        root = loader.load();
        
        // enable all children to get this class (and thus the userData)
        MainAppViewController mvc = loader.getController();
        mvc.setBudget(this);
            
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
    
    public UserData getUserData() {
        return userData;
    }
    
} // Budget
