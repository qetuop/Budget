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
public class Institution implements Externalizable {
    private final StringProperty institutionName;
    private ObservableList<Account> accountList;
    
    public Institution(){
        this.institutionName = new SimpleStringProperty(this, "institutionName", "");        
        this.accountList = FXCollections.observableArrayList();
    }
    
    public Institution(String institutionName) {
        this();
        this.setInstitutionName(institutionName);
    }
        
    public final String getInstitutionName() { 
        return institutionName.get(); 
    }
    
    public final void setInstitutionName( String value ) {
        institutionName.set(value); 
    }
    
    public final StringProperty getInstitutionNameProperty() { 
        return institutionName; 
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }
    
    ObservableList<Account> getAccountList() {
        return accountList;
    }
    
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getInstitutionName());
        
        ArrayList<Account> tmp = new ArrayList<>(accountList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setInstitutionName((String) in.readObject());
        
        ArrayList<Account> tmp = (ArrayList<Account>) in.readObject();
        accountList = FXCollections.observableArrayList(tmp);
    }
    
} // Institution
