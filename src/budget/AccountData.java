/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

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
    
    public void addAccount( Account account ) {
        accountList.add(account);
    }
    
    public ObservableList<Account> getAccountList() {
        return accountList;
    }
    
    public Account getAccount(){return new Account();}

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
