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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brian
 */
public class Institution implements Externalizable {
    private final StringProperty institutionName;
    private AccountData accountData;
    
    public Institution(){
        this.institutionName = new SimpleStringProperty(this, "institutionName", "");        
        this.accountData = new AccountData();
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
        this.accountData.addAccount(account);
    }

    public AccountData getAccountData() {
        return accountData;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getInstitutionName());
        out.writeObject(accountData);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setInstitutionName((String) in.readObject());
        accountData = (AccountData)in.readObject();
    }
}
