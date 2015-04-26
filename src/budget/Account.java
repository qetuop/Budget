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
public class Account implements Externalizable {

    private final StringProperty accountName;
    
    public Account(){
        this.accountName = new SimpleStringProperty(this, "accountName", "");
    }
    
    public final String getAccountName() { return accountName.get(); }
    public final void setAccountName( String value ) {accountName.set(value); }
    public final StringProperty getAccountNameProperty() { return accountName; }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getAccountName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setAccountName((String) in.readObject());
    }
}
