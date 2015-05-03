/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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

    private BudgetData budgetData; // will be set from main controller

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("IVC::initialize()");
        InstitutionNameCol.setCellValueFactory(new PropertyValueFactory<>("InstitutionName"));

        AccountCol.setCellValueFactory(new PropertyValueFactory<>("AccountName"));
    }

    private void init() {
        System.out.println("IVC::init()");

        // set the table up with initial data
        setTable();

        // handle USER selection (from other tab) - set the institution list to this user's list
        budgetData.addUserPropertyChangeListener( evt -> { setTable(); } );

        // handle INSTITUTION table selection events
        institutionTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (institutionTableView.getSelectionModel().getSelectedItem() != null) {
                
                Institution selectedInstitution = institutionTableView.getSelectionModel().getSelectedItem();                   
                budgetData.setSelectedInstitution(selectedInstitution);
 
                // link institution view - Right hand side table - future growth
                institutionAccountTableView.setItems(selectedInstitution.getAccountList());
            }
        });

        // done the first time through
        institutionTableView.getSelectionModel().selectFirst();

    } // init

    @FXML
    protected void addInstitution(ActionEvent event) {
        System.out.println("IVC::addInstitution()");

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
            //institutionData.add(institution);
            budgetData.getSelectedUser().addInstitution(institution);
            institutionTableView.getSelectionModel().select(institution);
        });

    } // addInstitution

    void setBudgetData(BudgetData budgetData) {
        this.budgetData = budgetData;
        init();
    }
    
    private void setTable() {
        User user = budgetData.getSelectedUser();
        
        if (user != null) {
            ObservableList<Institution> institutionList = user.getInstitutionList();
            institutionTableView.setItems(institutionList);

            // link institution view - Right hand side table
            //institutionAccountTableView.setItems(userData.getSelectedUser().getInstitutionData().getAccountData().getAccountList());
        }
    }

} // InstitutionViewController
