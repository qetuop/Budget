/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import static budget.InstitutionData.INSTITUTION_SELECTION;
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
public class AccountData implements Externalizable {
    private ObservableList<Account> accountList = FXCollections.observableArrayList();
    private Account selectedAccount;
    
    public static final String ACCOUNT_SELECTION = "account_selection";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addAccount( Account account ) {
        accountList.add(account);
    }
    
    public ObservableList<Account> getAccountList() {
        return accountList;
    }
    
    // TODO: change to ???
    public Account getAccount(){
        return new Account();
    }

    // set by table selection, use index?
    public void setSelectedAccount(Account selected) {

        Account old = selectedAccount;
        selectedAccount = selected;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, ACCOUNT_SELECTION, old, selectedAccount);
        pcs.firePropertyChange(evt);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ArrayList<Account> tmp = new ArrayList<>(accountList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ArrayList<Account> tmp = (ArrayList<Account>) in.readObject();
        accountList = FXCollections.observableArrayList(tmp);
    }   
    
} // AccountData
