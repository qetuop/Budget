/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class MainAppViewController implements Initializable {
    
    
    private Budget budget;
 
    //private UserViewController userViewController;
    //private InstitutionViewController institutionViewController;
    
    @FXML Node usersTab;
    @FXML private UserViewController usersTabController;
    @FXML Node institutionsTab;
    @FXML private InstitutionViewController institutionsTabController;
    
    //<fx:include fx:id="InstitutionsTabPage" source="InstitutionDataView.fxml" />
    //@FXML private InstitutionViewController InstitutionsTabPage;
    
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
    
    @FXML
    protected void fileSaveSelected(Event event) {
        System.out.println("MAVC::fileSaveSelected");
        
        budget.save();
    }
    
    @FXML
    protected void fileOpenSelected(Event event) {
        System.out.println("MAVC::fileOpenSelected");
        
        budget.load();
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
    
    public void setBudget(Budget budget) {      
        this.budget = budget;               
        // hopefully it's not too late to call this here
        //PC/SC can't use the main app in its initalize/ctor
        usersTabController.setBudget(budget);
        institutionsTabController.setBudget(budget);
    }
    
   
    
}
