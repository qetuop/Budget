/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;



/**
 * FXML Controller class
 *
 * @author Brian
 */
public class UserViewController implements Initializable {
    @FXML private TableView<User> userTableView;
    @FXML private TableColumn<User, String> FirstNameCol;   // User = data in tableview? String = field in that class?
    @FXML private TableColumn<User, String> LastNameCol;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName")); // FirstName or firstName work?
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        init();
       
    }    
    protected void init() {
        System.out.println("here");
        ObservableList<User> userData = userTableView.getItems();
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Smith");
        userData.add(user);
    }
    
    @FXML
    protected void addUser(ActionEvent event) {
        System.out.println("here");
        ObservableList<User> userData = userTableView.getItems();
        
        
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("New User");
        dialog.setHeaderText("<header text>");

        // Set the button types.
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the first and last labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstName = new TextField();
        firstName.setPromptText("Frist Name");
        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastName, 1, 1);

        // Enable/Disable ok button depending on whether a first name was entered.
        Node okButton = dialog.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        firstName.textProperty().addListener((observable, oldValue, newValue) -> {
            okButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the first field by default.
        Platform.runLater(() -> firstName.requestFocus());

        // Convert the result to a first-last-pair when the ok button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(firstName.getText(), lastName.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        User newUser = new User();
        result.ifPresent(firstLastName -> {
            newUser.setFirstName(firstLastName.getKey());
            newUser.setLastName(firstLastName.getValue());
            System.out.println("first=" + newUser.getFirstName() + ", last=" + newUser.getLastName());
            userData.add(newUser);
        });

    }
    
}
