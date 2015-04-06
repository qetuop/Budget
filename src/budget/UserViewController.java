/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class UserViewController implements Initializable {

    @FXML
    public TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> FirstNameCol;   // User = data in tableview? String = field in that class?
    @FXML
    private TableColumn<User, String> LastNameCol;

    // ? this is the main data store ?
    private UserData userData = new UserData();
    
    public static final String USER_SELECTION = "user_selection";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    private User selectedUser = new User();

//    public UserViewController() {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserDataView.fxml"));
//        //fxmlLoader.setRoot(this);
//        //fxmlLoader.setController(this);
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("UVC::initialize()");
        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName")); // FirstName or firstName work?
        LastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        init();

    }

    protected void init() {
        System.out.println("UVC::init()");
        //ObservableList<User> userData = userTableView.getItems();
        userData.setUserList(userTableView.getItems());

        
        
        // read in saved users
        // ex:
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Smith");
        userData.addUser(user);
        
        
        //selectedUser = user;
        
        userTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            
            if (userTableView.getSelectionModel().getSelectedItem() != null) {

                User oldSelectedUser = selectedUser;   
        
                selectedUser = userTableView.getSelectionModel().getSelectedItem();
                System.out.println("UVC::selected user now = " + selectedUser.getFirstName());
                System.out.println(" pcs.getPropertyChangeListeners().length " +  pcs.getPropertyChangeListeners().length);
                PropertyChangeEvent evt = new PropertyChangeEvent(this, USER_SELECTION, oldSelectedUser, selectedUser);
                pcs.firePropertyChange(evt);
                }
            
        });
        
        //userTableView.getSelectionModel().selectFirst();
    }
    
//    public void addPropertyChangeListener(ChangeListener<? super User> listener) {
//        //pcs.addPropertyChangeListener(listener);
//        userTableView.getSelectionModel().selectedItemProperty().addListener(listener);
//    }
    
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
        System.out.println("UVC::addPropertyChangeListener " + listener + ", pcs length= " + pcs.getPropertyChangeListeners().length);
    }
    
    public void addPropertyChangeListener(InvalidationListener listener) {
        //pcs.addPropertyChangeListener(listener);
        userTableView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public User getSelectedUser() {
        //return userTableView.getSelectionModel().getSelectedItem();
        return selectedUser;
    }

    @FXML
    protected void addUser(ActionEvent event) {
        System.out.println("addUser");
        System.out.println(this.getSelectedUser().getFirstName());

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

        result.ifPresent(firstLastName -> {
            User newUser = new User();
            newUser.setFirstName(firstLastName.getKey());
            newUser.setLastName(firstLastName.getValue());
            System.out.println("first=" + newUser.getFirstName() + ", last=" + newUser.getLastName());
            userData.addUser(newUser);
        });
    }

}
