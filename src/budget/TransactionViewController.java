/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class TransactionViewController implements Initializable {

    private BudgetData budgetData;

    @FXML
    private TableView<Transaction> transactionTableView;
    @FXML
    private TableColumn<Transaction, String> TransactionNameCol;
    @FXML
    private TableColumn<Transaction, LocalDate> TransactionDateCol;
    @FXML
    private TableColumn<Transaction, Double> TransactionAmountCol;
    
    final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("TVC::initialize()");
        
        TransactionDateCol.setCellValueFactory(new PropertyValueFactory<>("TransactionDate"));
        TransactionNameCol.setCellValueFactory(new PropertyValueFactory<>("TransactionName"));
        TransactionAmountCol.setCellValueFactory(new PropertyValueFactory<>("TransactionAmount"));
        
//        TransactionDateCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<LocalDate>() {
//
//            @Override
//            public String toString(LocalDate t) {
//                if (t==null) {
//                    return "" ;
//                } else {
//                    return dateFormat.format(t);
//                }
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                try {
//                    return LocalDate.parse(string, dateFormat);
//                } catch (DateTimeParseException exc) {
//                    return null ;
//                }
//            }
//
//        }));
        
    } // initialize

    private void init() {
        System.out.println("TVC::init()");

        // set the table up with initial data
        setTable();

        // handle INSTITUTION selection (from other tab) - set the institution list to this user's list
        budgetData.addAccountPropertyChangeListener(evt -> {
            setTable();
        });

        // propagate account selections
        transactionTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (transactionTableView.getSelectionModel().getSelectedItem() != null) {

                Transaction selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
                budgetData.setSelectedTransaction(selectedTransaction);

                // link institution view - Right hand side table
                //accounttransactionTableView.setItems(selectedTransaction.getTransactionList());
            }
        });

        // done the first time through
        transactionTableView.getSelectionModel().selectFirst();

    } // init

    @FXML
    protected void importTransaction(ActionEvent event) {
        System.out.println("TVC::importTransaction()");
        Importer i = new Importer();
        ArrayList<Transaction> transactionList;
        transactionList = i.readData();
        for (Transaction t: transactionList )
            budgetData.getSelectedAccount().addTransaction(t);
    }
    
    @FXML
    protected void addTransaction(ActionEvent event) {
        System.out.println("TVC::addTransaction()");

        // TODO - move this somewhere
        List<String> choices = new ArrayList<>();
        choices.add("Money IN");
        choices.add("Money OUT");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Add Transaction");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your Transaction:");

        // The Java 8 way to get the response value (with lambda expression).
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(transactionName -> {
            System.out.println("Your choice: " + transactionName);

            Transaction transaction = new Transaction();
            transaction.setTransactionName(transactionName);
            budgetData.getSelectedAccount().addTransaction(transaction);
        });

    } // addTransaction

    void setBudgetData(BudgetData budgetData) {
        this.budgetData = budgetData;
        init();
    }

    private void setTable() {
        Account account = budgetData.getSelectedAccount();

        if (account != null) {
            ObservableList<Transaction> transactionList = account.getTransactionList();
            transactionTableView.setItems(transactionList);

            // link institution view - Right hand side table
            //accounttransactionTableView.setItems(selectedTransaction.getTransactionList());
        }
    }
    
    public void setFirstEntry() {
        transactionTableView.getSelectionModel().selectFirst();
    }

} // TransactionViewController
