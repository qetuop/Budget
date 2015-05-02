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
public class AccountViewController implements Initializable {

    private Budget budget;
    UserData userData; // will be gotten from budget class

    @FXML
    private TableView<Account> accountTableView;
    @FXML
    private TableColumn<Account, String> AccountNameCol;

    private Account selectedAccount = new Account();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("AVC::initialize()");
        AccountNameCol.setCellValueFactory(new PropertyValueFactory<>("AccountName"));
    }

    private void init() {
        System.out.println("AVC::init()");

        userData = budget.getUserData();

        // set the table up with initial data
        setTable(userData.getSelectedUser().getSelectedInstitution());

        // handle INSTITUTION table selection events 
        userData.getInstitutionData().addPropertyChangeListener(evt -> {
            System.out.println("AVC::INSTITUTION has changed");
            Institution institution = (Institution) evt.getNewValue();

            setTable(institution);
        });
        
        // propagate account selections
        accountTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (accountTableView.getSelectionModel().getSelectedItem() != null) {

                selectedAccount = accountTableView.getSelectionModel().getSelectedItem();
                userData.getInstitutionData().getAccountData().setSelectedAccount(selectedAccount);

                // link institution view - Right hand side table - future growth
                //userInstitutionTableView.setItems(selectedUser.getInstitutionData().getInstitutionList());
            }
        });

        // done the first time through
        accountTableView.getSelectionModel().selectFirst();

    } // init

    @FXML
    protected void addAccount(ActionEvent event) {
        System.out.println("AVC::addAccount()");

        ObservableList<Account> accountData = accountTableView.getItems();

        // TODO - move this somewhere
        List<String> choices = new ArrayList<>();
        choices.add("Savings");
        choices.add("Checking");
        choices.add("Credit Card");
        choices.add("Stock");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Add Account");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your Account:");

        // The Java 8 way to get the response value (with lambda expression).
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(accountName -> {
            System.out.println("Your choice: " + accountName);
            
            Account account = new Account();
            account.setAccountName(accountName);
            accountData.add(account);

//            // write the selection back to the user's list
//            User user = userData.getSelectedUser();
//            if (user != null) {
//                System.out.println("The selected user is " + user.getFirstName());
//             }
        });

    } // addAccount


//    }
    void setBudget(Budget budget) {
        this.budget = budget;
        init();
    }

    private void setTable(Institution selectedInstitution) {
        System.out.println("AVC::setTable() " + selectedInstitution);
        
        //if (selectedInstitution != null) {
            AccountData accountData = selectedInstitution.getAccountData();

            //if (accountData != null) {
                ObservableList<Account> accountList = accountData.getAccountList();
                accountTableView.setItems(accountList);
            //}
        //}
    }

}
