/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class MainAppViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       
    // two events on selection, then new tab and the old tab....
    @FXML
    protected void userTabSelected(Event event) {
        System.out.println("userTabSelected");
        System.out.println(event.getEventType());
        System.out.println(((Tab)event.getSource()).getId());

    }
    
    @FXML
    protected void institutionTabSelected(Event event) {
        System.out.println("institutionTabSelected");
     
    }
    
}
