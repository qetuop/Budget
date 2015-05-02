/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    // left side table/col
    @FXML
    private TableView<Institution> institutionTableView;
    @FXML
    private TableColumn<Institution, String> InstitutionNameCol;

    // right side table/col
    @FXML
    public TableView<Account> institutionAccountTableView;
    @FXML
    private TableColumn<Account, String> AccountCol;

    //public static final String INSTITUTION_SELECTION = "institution_selection";
    //private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    //private Institution selectedInstitution = new Institution();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("IVC::initialize()");
        InstitutionNameCol.setCellValueFactory(new PropertyValueFactory<>("InstitutionName"));

        AccountCol.setCellValueFactory(new PropertyValueFactory<>("AccountName"));
        //init();
    }

    private void init() {
        System.out.println("IVC::init()");

        userData = budget.getUserData();

        // set the table up with initial data
        setTable(userData.getSelectedUser());

        // handle USER selection (from other tab) - set the institution list to this user's list
        userData.addPropertyChangeListener(evt -> {
            User user = (User) evt.getNewValue();
budget.debugSelectedUserData();
            setTable(user);
        });

        // handle INSTITUTION table selection events
        institutionTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (institutionTableView.getSelectionModel().getSelectedItem() != null) {

                Institution selectedInstitution = institutionTableView.getSelectionModel().getSelectedItem();
                
                System.out.println("IVC::selected Institution now = " + selectedInstitution.getInstitutionName());     
                
                userData.getInstitutionData().setSelectedInstitution(selectedInstitution);
                
                           

                // link institution view - Right hand side table - future growth
                institutionAccountTableView.setItems(userData.getSelectedUser().getInstitutionData().getAccountData().getAccountList());
            }
        });

        // done the first time through
        institutionTableView.getSelectionModel().selectFirst();

    } // init

    @FXML
    protected void addInstitution(ActionEvent event) {
        System.out.println("IVC::addInstitution()");

        ObservableList<Institution> institutionData = institutionTableView.getItems();

        // TODO - move this somewhere
        List<String> choices = new ArrayList<>();
        choices.add("Navy Federal");
        choices.add("Vanguard");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Add Institution");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your Institution:");

        // The Java 8 way to get the response value (with lambda expression).
        Optional<String> result = dialog.showAndWait();

        // Add institution to data store and set it as table selection
        result.ifPresent(institutionName -> {
            Institution institution = new Institution(institutionName);
            institutionData.add(institution);
            institutionTableView.getSelectionModel().select(institution);

//            // write the selection back to the user's list
//            User user = userData.getSelectedUser();
//            if (user != null) {
//                System.out.println("The selected user is " + user.getFirstName());
//                //user.addInstitution(institution);
//             }
        });

    } // addInstitution

    void setBudget(Budget budget) {
        this.budget = budget;

        init();
    }

    private void setTable(User selectedUser) {
        
        if (selectedUser != null) {
            InstitutionData institutionData = selectedUser.getInstitutionData();

            if (institutionData != null) {
                ObservableList<Institution> institutionList = institutionData.getInstitutionList();
                institutionTableView.setItems(institutionList);
            }

            // link institution view - Right hand side table
            institutionAccountTableView.setItems(userData.getSelectedUser().getInstitutionData().getAccountData().getAccountList());
        }
    }
    
} // InstitutionViewController
