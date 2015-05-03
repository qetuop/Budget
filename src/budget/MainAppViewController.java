/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    
    private MainApp mainApp;
    private BudgetData budgetData;
    
    @FXML Node usersTab;
    @FXML private UserViewController usersTabController;
    
    @FXML Node institutionsTab;
    @FXML private InstitutionViewController institutionsTabController;
    
    @FXML Node accountsTab;
    @FXML private AccountViewController accountsTabController;
    
    @FXML Node transactionsTab;
    @FXML private TransactionViewController transactionsTabController;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("MAVC::initialize()");        
    }    
    
    @FXML
    protected void fileSaveSelected(Event event) {
        System.out.println("MAVC::fileSaveSelected");        
        mainApp.save();
    }
    
    @FXML
    protected void fileOpenSelected(Event event) {
        System.out.println("MAVC::fileOpenSelected");        
        mainApp.load();
    }
    
    // two events on selection, then new tab and the old tab....
    @FXML
    protected void userTabSelected(Event event) {
//        usersTabController.setFirstEntry();  // these mess things up
    }
    
    @FXML
    protected void institutionTabSelected(Event event) {
//        institutionsTabController.setFirstEntry();
    }
    
    @FXML
    protected void accountTabSelected(Event event) {
//        accountsTabController.setFirstEntry(); 
    } 
    
    @FXML
    protected void transactionTabSelected(Event event) {
//        transactionsTabController.setFirstEntry();  
    } 

    void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    void setBudgetData(BudgetData budgetData) {
        this.budgetData = budgetData;    

        usersTabController.setBudgetData(budgetData);
        institutionsTabController.setBudgetData(budgetData);
        accountsTabController.setBudgetData(budgetData);
        transactionsTabController.setBudgetData(budgetData);
    }
}
