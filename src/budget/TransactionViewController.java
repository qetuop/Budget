/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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

        // handle ACCOUNT selection (from other tab) - set the institution list to this user's list
        budgetData.addAccountPropertyChangeListener(evt -> {
            setTable();
        });

        // propagate transactions selections
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
    protected void contextMenuRequested() {
        System.out.println("TVC::contextMenuRequested()");
    }

    @FXML
    protected void importTransaction(ActionEvent event) {
        System.out.println("TVC::importTransaction()");

        Stage stage = (Stage) transactionTableView.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
//        fileChooser.setInitialDirectory( new File(System.getProperty("user.home")) ); 
        fileChooser.setInitialDirectory(new File("C:\\Users\\Brian\\Documents\\NetBeansProjects\\Budget"));

        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text CSV", "*.csv"),
                new ExtensionFilter("All Files", "*.*"));

        List<File> list = fileChooser.showOpenMultipleDialog(stage);
        if (list != null) {
            for (File file : list) {
                Importer i = new Importer();
                ArrayList<Transaction> transactionList;
                transactionList = i.readData(file);
                transactionList.stream().forEach((t) -> {
                    budgetData.getSelectedAccount().addTransaction(t);
                });
            }
        }

//        File file = fileChooser.showOpenDialog(stage);
//        if (file != null) {
//            Importer i = new Importer();
//            ArrayList<Transaction> transactionList;
//            transactionList = i.readData(file);
//            transactionList.stream().forEach((t) -> {
//                budgetData.getSelectedAccount().addTransaction(t);
//            });
//        }
    }

    @FXML
    protected void editTransaction(ActionEvent event) {
        System.out.println("TVC::editTransaction()");
    }

    @FXML
    protected void addTransaction(ActionEvent event) {
        System.out.println("TVC::addTransaction()");
// Create the custom dialog.
        Dialog<Transaction> dialog = new Dialog<>();
        dialog.setTitle("New Transaction");
        dialog.setHeaderText("<header text>");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the first and last labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        TextField dateTF = new TextField();
        dateTF.setPromptText("Transaction Date");

        TextField name = new TextField();
        name.setPromptText("Transaction Name");

        TextField displayName = new TextField();
        displayName.setPromptText("Display Name");

        TextField amount = new TextField();
        amount.setPromptText("Transaction Amount");
        amount.setText("0.0");

        // restrict amount text field to numbers
        TextFormatter<String> formatter = new TextFormatter<String>(change -> {
            change.setText(change.getText().replaceAll("[\\D+]", ""));
//            change.setText(change.getText().replaceAll("[^\\d+(\\.\\d{0,2})?$]", ""));
//            change.setText(change.getText().replaceAll("[^-?\\d+(\\.\\d{2})?$]", ""));
            return change;
        });
        amount.setTextFormatter(formatter);

        grid.add(new Label("Transaction Date:"), 0, 0);
        grid.add(datePicker, 1, 0);
        grid.add(new Label("Transaction Name:"), 0, 1);
        grid.add(name, 1, 1);
        grid.add(new Label("Display Name:"), 0, 2);
        grid.add(displayName, 1, 2);
        grid.add(new Label("Transaction Amount:"), 0, 3);
        grid.add(amount, 1, 3);

        // Enable/Disable ok button depending on whether a first name was entered.
//        Node okButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
//        okButton.setDisable(true);
//        // Do some validation (using the Java 8 lambda syntax).
//        firstName.textProperty().addListener((observable, oldValue, newValue) -> {
//            okButton.setDisable(newValue.trim().isEmpty());
//        });
        dialog.getDialogPane().setContent(grid);

        // Request focus on the first field by default.
        //Platform.runLater(() -> firstName.requestFocus());
        // Convert the result to a Transaction when the ok button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                //DateTimeFormatter dtf = DateTimeFormat.forPattern("MM-dd-yyyy");
                //LocalDate dt = dateFormat.parseLocalDate(date.getText());

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                //LocalDate ld = LocalDate.parse(date.getText(), formatter);
                return new Transaction(datePicker.getValue(), displayName.getText(), Double.parseDouble(amount.getText()));
            }
            return null;
        });

        Optional<Transaction> result = dialog.showAndWait();

        // Add user to data store and set it as table selection
        result.ifPresent(newTransaction -> {
            budgetData.getSelectedAccount().addTransaction(newTransaction);
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
