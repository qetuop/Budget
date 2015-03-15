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
  /*    
Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText("I have a great message for you!");
alert.showAndWait();
*/

        //Parent root = FXMLLoader.load(getClass().getResource("budget_tableview.fxml"));
        //Scene scene = new Scene(root);  
        
        //root = FXMLLoader.load(getClass().getResource("MainAppView.fxml"));
        //scene = new Scene(root);
        
        root = FXMLLoader.load(getClass().getResource("UserDataView.fxml"));
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
    
}
