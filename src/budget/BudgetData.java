/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class BudgetData implements Externalizable {

    private ObservableList<User> userList = FXCollections.observableArrayList();

    private User selectedUser = new User();
    private Institution selectedInstitution = new Institution();
    private Account selectedAccount = new Account();
    private Transaction selectedTransaction = new Transaction();

    // selection vars
    public static final String USER_SELECTION = "user_selection";
    public static final String INSTITUTION_SELECTION = "institution_selection";
    public static final String ACCOUNT_SELECTION = "account_selection";
    public static final String TRANSACTION_SELECTION = "transaction_selection";

    private final PropertyChangeSupport pcsUser = new PropertyChangeSupport(this);
    private final PropertyChangeSupport pcsInstitution = new PropertyChangeSupport(this);
    private final PropertyChangeSupport pcsAccount = new PropertyChangeSupport(this);
    private final PropertyChangeSupport pcsTransaction = new PropertyChangeSupport(this);

    public BudgetData() {

    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public Institution getSelectedInstitution() {
        return selectedInstitution;
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public Transaction getSelectedTransaction() {
        return selectedTransaction;
    }

    public void setSelectedUser(User user) {
        User oldSelectedUser = selectedUser;
        selectedUser = user;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, USER_SELECTION, oldSelectedUser, selectedUser);
        
        // un set next lower class
        setSelectedInstitution(new Institution());
        
        pcsUser.firePropertyChange(evt);
    }

    public void setSelectedInstitution(Institution institution) {
        Institution oldSelectedInstitution = selectedInstitution;
        selectedInstitution = institution;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, INSTITUTION_SELECTION, oldSelectedInstitution, selectedInstitution);
        
        // un set next lower class
        setSelectedAccount(new Account());
        
        pcsInstitution.firePropertyChange(evt);
    }

    public void setSelectedAccount(Account account) {
        Account old = selectedAccount;
        selectedAccount = account;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, ACCOUNT_SELECTION, old, selectedAccount);
        
        // un set next lower class
        setSelectedTransaction(new Transaction());
        
        pcsAccount.firePropertyChange(evt);
    }

    void setSelectedTransaction(Transaction transaction) {
        Transaction old = selectedTransaction;
        selectedTransaction = transaction;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, TRANSACTION_SELECTION, old, selectedTransaction);
        pcsTransaction.firePropertyChange(evt);
    }

    public void addUserPropertyChangeListener(PropertyChangeListener listener) {
        pcsUser.addPropertyChangeListener(listener);
    }

    public void removeUserPropertyChangeListener(PropertyChangeListener listener) {
        pcsUser.removePropertyChangeListener(listener);
    }

    public void addInstitutionPropertyChangeListener(PropertyChangeListener listener) {
        pcsInstitution.addPropertyChangeListener(listener);
    }

    public void removeInstitutionPropertyChangeListener(PropertyChangeListener listener) {
        pcsInstitution.removePropertyChangeListener(listener);
    }

    public void addAccountPropertyChangeListener(PropertyChangeListener listener) {
        pcsAccount.addPropertyChangeListener(listener);
    }

    public void removeAccountPropertyChangeListener(PropertyChangeListener listener) {
        pcsAccount.removePropertyChangeListener(listener);
    }

    public void addTransactionPropertyChangeListener(PropertyChangeListener listener) {
        pcsTransaction.addPropertyChangeListener(listener);
    }

    public void removeTransactionPropertyChangeListener(PropertyChangeListener listener) {
        pcsTransaction.removePropertyChangeListener(listener);
    }

    public void setUserList(ObservableList<User> userList) {
        this.userList = userList;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

//    public void setInsitutionList(ObservableList<Institution> institutionList) {
//        getSelectedUser().setInstitutionList( institutionList);
//    }
//    public ObservableList<Institution> getInstitutionList() {
//        return getSelectedUser().getInstitutionList();
//    }
    void addUser(User user) {
        userList.add(user);
    }
    
    public void update() {
        this.debugAllUserData();
        //this.setSelectedUser(this.userList.get(0));
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ArrayList<User> tmp = new ArrayList<>(userList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ArrayList<User> tmp = (ArrayList<User>) in.readObject();
        userList = FXCollections.observableArrayList(tmp);
    }

    void debugSelectedUserData() {
        System.out.println("** SelectedData **");

        User u = this.getSelectedUser();
        Institution i = this.getSelectedInstitution();
        Account a = this.getSelectedAccount();
        Transaction t = this.getSelectedTransaction();

        System.out.println("  USER: " + u + ", institution size: " + u.getInstitutionList().size());
        System.out.println("  INST: " + i + ", account size: " + i.getAccountList().size());
        System.out.println("  ACNT: " + a);
        System.out.println("  TRAS: " + t);
        
        System.out.println("****************");
    }

    void debugAllUserData() {
        System.out.println("** All Data **");
        
        for (User u : getUserList()) {
            System.out.println(u);
            
            for (Institution i : u.getInstitutionList()) {
                System.out.println("  " + i);
                
                for (Account a : i.getAccountList()) {
                    System.out.println("    " + a);
                    
                    for (Transaction t : a.getTransactionList()) {
                        System.out.println("     " + t);
                    } // transaction
                } // account
            } // institution            
        } // user

        System.out.println("****************");
    }

} // BudgetData
