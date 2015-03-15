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
    @FXML private TableView<Institution> institutionTableView;
    @FXML private TableColumn<Institution, String> InstitutionNameCol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        InstitutionNameCol.setCellValueFactory(new PropertyValueFactory<>("InstitutionName"));
        init();
    }   
    
    private void init() {
        ObservableList<Institution> institutionData = institutionTableView.getItems();
        Institution intit = new Institution();
        intit.setInstitutionName("Hole In Backyard Inc.");
        institutionData.add(intit);
    }
    
    @FXML
    protected void addInstitution(ActionEvent event) {
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
        
    }

    
    
}
