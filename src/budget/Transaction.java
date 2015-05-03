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
public class Transaction implements Externalizable {

    private final StringProperty transactionName;
    
    public Transaction(){
        this.transactionName = new SimpleStringProperty(this, "transactionName", "");
    }
    
    public final String getTransactionName() { 
        return transactionName.get(); 
    }
    
    public final void setTransactionName( String value ) {
        transactionName.set(value); 
    }
    
    public final StringProperty getTransactionNameProperty() { 
        return transactionName; 
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getTransactionName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setTransactionName((String) in.readObject());
    }
}
