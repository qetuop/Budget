/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import au.com.bytecode.opencsv.CSVReader;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Brian
 */
public class BudgetTableViewController implements Initializable {

    @FXML
    private TableView<Expense> budgetTableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Listen for selection changes and show the person details when changed.
        budgetTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> editData(new ActionEvent()));
    }

    @FXML
    private void editData(ActionEvent event) {
        System.out.println("IM here");

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("edit_dialog.fxml"));
            AnchorPane frame = fxmlLoader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Stage stageTheLabelBelongs = (Stage) budgetTableView.getScene().getWindow();
            dialogStage.initOwner(stageTheLabelBelongs);
            Scene scene = new Scene(frame);

            dialogStage.setScene(scene);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
//             return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

    }

    @FXML
    private void readData(ActionEvent event) {

        ObservableList<Expense> expenses = budgetTableView.getItems();
        /*
         data.add(new Person(firstNameField.getText(),
         lastNameField.getText(),
         emailField.getText()
         ));
         */
        //System.out.println("You clicked me!");
        //expenses = new ArrayList<Expense>();

        //BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Brian\\Documents\\NetBeansProjects\\ExpenseReport\\May2009_1023.csv"));
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader("sample.csv"));
            String[] line;
            line = csvReader.readNext(); // get first non line
            while ((line = csvReader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                //System.out.println(line);
                //for ( String foo : nextLine ) {
                //System.out.println(foo.trim());

                //String[] line = csvReader.readNext();
//                    String out = String.format("%s, %s, %s, %s, %s", line[0], line[1], line[2], line[3], line[4]);
                //"Transaction Date"  ,  "Posted Date"  ,  "Description"  ,  "Debit"  ,  "Credit"
                //"11/13/2014"  ,  "11/14/2014"  ,  "7-ELEVEN 12345 Foo FG"  ,  "41.33",""
                //                   System.out.println(out);
                // convert date string to long
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                System.out.println("line 0 " + line[0]);
                System.out.println("line 1 " + line[1]);
                Date date1 = df.parse(line[0]);
                Date date2 = df.parse(line[1]);

                String amount = "";
                if (line[3].length() != 0) {
                    amount = "-" + line[3];
                } else {
                    amount = line[4];
                }
                //Expense(Long date, Long id, String merchant, String location, Double amount)
                //Expense expense = new Expense(date.getTime(), new Double(line[1]), line[2], line[3], new Double(line[4]));
                Expense expense = new Expense(date1.getTime(), date2.getTime(), line[2], new Double(amount));
                expenses.add(expense);
                System.out.println(expense + "\n");
                // }
                System.out.println("-------" + expenses.size() + "-----------");

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Budget.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
