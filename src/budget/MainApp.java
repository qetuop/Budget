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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Brian
 */
public class MainApp extends Application {
    
    //  this *is* the main data store 
    private BudgetData budgetData = new BudgetData();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        if ( !load() )
            hardcodedSetup();

        budgetData.debugAllUserData();
        
        Parent root;
        Scene scene;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("MainAppView.fxml"));
        root = loader.load();

        // enable all children to get this class (and thus the userData)
        MainAppViewController mvc = loader.getController();            
        mvc.setBudgetData(budgetData);
        mvc.setMainApp(this);
                    
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
    
    public BudgetData getBudgetData() {
        return budgetData;
    }
    
//    public void setUserData(UserData userData) {
//        this.userData = userData;        
//    }

    private void hardcodedSetup() {
        System.out.println("Budget::hardcodedSetup()");
        User user = new User();
        user.setFirstName("Bob2");
        user.setLastName("Smith2");
        
        Institution institution = new Institution();
        institution.setInstitutionName("Hole in Backyard INC.");
        
        Account account = new Account();
        account.setAccountName("Old Sock");
        
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionName("wooden nickle");
        transaction.setTransactionAmount(100.28);
        
                
        account.addTransaction(transaction);
        institution.addAccount(account);
        user.addInstitution(institution);        
        budgetData.addUser(user);
     }
    
    public Boolean load() {
        try {
            FileInputStream fileIn = new FileInputStream("test.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            budgetData = (BudgetData) in.readObject();
            in.close();
            fileIn.close();            
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
        System.out.println("SAVING");
        String fileName = "test.ser";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(budgetData);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAppViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

} // Budget
