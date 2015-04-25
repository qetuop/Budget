/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author Brian
 */
public class UserViewController implements Initializable {

    private Budget budget;
    UserData userData; // will be set from budget class

    @FXML
    public TableView<User> userTableView;
    @FXML
    private TableColumn<User, String> FirstNameCol;   // User = data in tableview? String = field in that class?
    @FXML
    private TableColumn<User, String> LastNameCol;

    @FXML
    public TableView<Institution> userInstitutionTableView;
    @FXML
    private TableColumn<Institution, String> InstitutionCol;

    // ? this is the main data store ?
    //private UserData userData = new UserData();
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

        InstitutionCol.setCellValueFactory(new PropertyValueFactory<>("InstitutionName"));
        //init();

    }

    protected void init() {
        System.out.println("UVC::init()");

        userData = budget.getUserData();
        userTableView.setItems(userData.getUserList());

        // handle table selection events
        userTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (userTableView.getSelectionModel().getSelectedItem() != null) {

                selectedUser = userTableView.getSelectionModel().getSelectedItem();
                userData.setSelectedUser(selectedUser);

                System.out.println("UVC::selected user now = " + selectedUser.getFirstName());
                debugUser(selectedUser);

                // reset data
                //userInstitutionTableView
                // link institution view
                userInstitutionTableView.setItems(selectedUser.getInstitutionData().getInstitutionList());
            }
        });

        // done the first time through
        userTableView.getSelectionModel().selectFirst();

        User user = userData.getSelectedUser();
        System.out.println("user* " + user);

        System.out.println(userInstitutionTableView);
        System.out.println(user.getInstitutionData());
        System.out.println(user.getInstitutionData().getInstitutionList());

    } // init

    @FXML
    protected void addUser(ActionEvent event) {
        System.out.println("addUser");

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
            userTableView.getSelectionModel().select(newUser);
        });
    }

    void setBudget(Budget budget) {
        this.budget = budget;

        init();
    }

    private void debugUser(User selectedUser) {
        InstitutionData institutionData = selectedUser.getInstitutionData();
        if (institutionData != null) {
            ObservableList<Institution> institutionList = institutionData.getInstitutionList();
            System.out.println("UVC::debugUser, inst list = " + institutionList.size());
        }
    }

}
