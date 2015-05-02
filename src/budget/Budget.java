/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 *
 * @author Brian
 */
public class Budget extends Application {
    
    //  this *is* the main data store 
    private UserData userData = new UserData();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        if ( !load() )
            hardcodedSetup();

        debugAllUserData();
        
        Parent root;
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Budget.class.getResource("MainAppView.fxml"));
        root = loader.load();

        // enable all children to get this class (and thus the userData)
        MainAppViewController mvc = loader.getController();            
        mvc.setBudget(this);
                    
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Budget 2000");
        primaryStage.show();  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
    
    public UserData getUserData() {
        return userData;
    }
    
    public void setUserData(UserData userData) {
        this.userData = userData;        
    }

    private void hardcodedSetup() {
        System.out.println("Budget::hardcodedSetup()");
        User user = new User();
        user.setFirstName("Bob2");
        user.setLastName("Smith2");
        
        Institution institution = new Institution();
        institution.setInstitutionName("Hole in Backyard INC.");
        user.addInstitution(institution);
        
        userData.addUser(user);
     }
    
    public Boolean load() {
        try {
            FileInputStream fileIn = new FileInputStream("test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            userData = (UserData) in.readObject();
            in.close();
            fileIn.close();
            
            User u2 = userData.getUser();
            
            //System.out.println(u2.getFirstName() + " " + u2.getLastName() + " " + u2.getInstitutionData().getInstitutionList().get(0).getInstitutionName());
            
            //budget.setUserData(userDataIn);
            
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        } catch (ClassNotFoundException c) {
            System.out.println("User class not found");
            c.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public void save() {
        String fileName = "test.ser";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(userData);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void debugSelectedUserData() {
        System.out.println("** SelectedUser **");
        
        User u = userData.getSelectedUser();
        System.out.println("  USER: " + u.getFirstName() + " " + u.getLastName());
        
        InstitutionData i = u.getInstitutionData();
        System.out.println("  INST: " + i.getSelectedInstitution().getInstitutionName());
        
        AccountData a = i.getAccountData();
        System.out.println("  ACNT: " + a.getAccountList().size()); 
        
        System.out.println("****************");
    }
    
    void debugAllUserData() {
        
        for (User u : userData.getUserList() ){
            System.out.println(u.getFirstName() + " " + u.getLastName());
            for ( Institution i : u.getInstitutionData().getInstitutionList() ) {
                System.out.println("  " + i.getInstitutionName());
                for ( Account a : i.getAccountData().getAccountList() ) {
                    System.out.println("    " + a.getAccountName());
                }
            } // institution            
        } // user
    }
    
} // Budget
