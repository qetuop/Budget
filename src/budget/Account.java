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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class Account implements Externalizable {

    private final StringProperty accountName;
    private ObservableList<Transaction> transactionList;

    public Account() {
        this.accountName = new SimpleStringProperty(this, "accountName", "");
        this.transactionList = FXCollections.observableArrayList();
    }

    public final String getAccountName() {
        return accountName.get();
    }

    public final void setAccountName(String value) {
        accountName.set(value);
    }

    public final StringProperty getAccountNameProperty() {
        return accountName;
    }

    public void addTransaction(Transaction account) {
        transactionList.add(account);
    }

    ObservableList<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getAccountName());

        ArrayList<Transaction> tmp = new ArrayList<>(transactionList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setAccountName((String) in.readObject());

        ArrayList<Transaction> tmp = (ArrayList<Transaction>) in.readObject();
        transactionList = FXCollections.observableArrayList(tmp);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        result.append(this.getAccountName());
        return result.toString();
    }

} // Account
