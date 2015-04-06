/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class MainAppViewController implements Initializable {
    
    
 
    //private UserViewController userViewController;
    //private InstitutionViewController institutionViewController;
    
    //@FXML 
    //private UserViewController UsersTab;
    
    //<fx:include fx:id="InstitutionsTabPage" source="InstitutionDataView.fxml" />
    @FXML private InstitutionViewController InstitutionsTabPage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("MAVC::initialize()");
        //InstitutionsTabPage.setUserController(UsersTab);
        // TODO
        
//        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDataView.fxml"));
//        try {
//            Parent root = (Parent) loader.load();
//             userViewController = loader.getController();
//        } catch (IOException ex) {
//            Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        loader = new FXMLLoader(getClass().getResource("InstitutionDataView.fxml"));
//        try {
//            Parent root = (Parent) loader.load();
//              institutionViewController = loader.getController();
//        } catch (IOException ex) {
//           Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
       
        
    }    
    
       
    // two events on selection, then new tab and the old tab....
    @FXML
    protected void userTabSelected(Event event) {
        System.out.println("MAVC::userTabSelected");
        //System.out.println(event.getEventType());
        //System.out.println(((Tab)event.getSource()).getId());
        
//        if ( userViewController != null ) {
//        User u = userViewController.getSelectedUser();
//        if ( u != null) {
//            System.out.println("*" + userViewController.getSelectedUser().getFirstName() + "*");
//        }
//        }
    }
    
    @FXML
    protected void institutionTabSelected(Event event) {
        System.out.println("MAVC::institutionTabSelected");
        
        //institutionViewController.setup();
        //InstitutionsTabPage.setup();
     
    }
    
}
