/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class InstitutionViewController implements Initializable {

    private Budget budget;
    UserData userData; // will be gotten from budget class
    
    @FXML
    private TableView<Institution> institutionTableView;
    @FXML
    private TableColumn<Institution, String> InstitutionNameCol;
    
    //private UserViewController userViewController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("IVC::initialize()");
        InstitutionNameCol.setCellValueFactory(new PropertyValueFactory<>("InstitutionName"));
        //init();
    }

    private void init() {
        System.out.println("IVC::init()");
        userData = budget.getUserData();
        
        ObservableList<Institution> institutionData = institutionTableView.getItems();
        Institution intit = new Institution();
        intit.setInstitutionName("Hole In Backyard Inc.");
        institutionData.add(intit);
    }

    @FXML
    protected void addInstitution(ActionEvent event) {
        System.out.println("IVC::addInstitution()");
        
        System.out.println("addInstitution");
        ObservableList<Institution> institutionData = institutionTableView.getItems();

        List<String> choices = new ArrayList<>();
        choices.add("Navy Federal");
        choices.add("Vanguard");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Navy Federal", choices);
        dialog.setTitle("Add Institution");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your Institution:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

//        if (result.isPresent()) {
//            System.out.println("Your choice: " + result.get());
//        }
        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(institution -> {
            System.out.println("Your choice: " + institution);
            Institution intit = new Institution();
            intit.setInstitutionName(institution);
            institutionData.add(intit);
        });

        
      
       ObservableList<User> ulist;
        ulist = userData.getUserList();
        System.out.println("ulist " + ulist.size());
        User u;
        u = userData.getSelectedUser();
        if (u != null) {
            System.out.println("The selected user is " + u.getFirstName());
        }

    } // addInstitution

//    void setup() {
//        System.out.println("IVC::setup()");
//        
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("UserDataView.fxml"));
//        try {
//            Parent root = (Parent) loader.load();
//            UserViewController userViewController = loader.getController();
//            System.out.println("IVC::setup() *" + userViewController.getSelectedUser().getFirstName() + "*");
//        } catch (IOException ex) {
//            Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    
    void setBudget(Budget budget) {
        this.budget = budget;
        
        init();
    }

   

}
