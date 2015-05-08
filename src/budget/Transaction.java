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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brian
 */
public class Transaction implements Externalizable {
    
    private static final long serialVersionUID = 1;

    private final StringProperty transactionName;
    private final ObjectProperty<LocalDate> transactionDate;
    private final DoubleProperty transactionAmount;  // ? BigDecmial ?

    final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Transaction() {
        this.transactionDate = new SimpleObjectProperty<>(this, "transactionDate", LocalDate.MIN);
        this.transactionName = new SimpleStringProperty(this, "transactionName", "");        
        this.transactionAmount = new SimpleDoubleProperty(this, "transactionAmount", 0.0);
    }
    
    public Transaction( LocalDate localDate, String name, Double amount ) {
        this();
        this.setTransactionDate(localDate);
        this.setTransactionName(name);
        this.setTransactionAmount(amount);
    }

    public final String getTransactionName() {
        return transactionName.get();
    }

    public final void setTransactionName(String value) {
        transactionName.set(value);
    }

    public final StringProperty getTransactionNameProperty() {
        return transactionName;
    }

    public final LocalDate getTransactionDate() {
        return transactionDate.get();
    }

    public final void setTransactionDate(LocalDate value) {
        transactionDate.set(value);
    }

    public final ObjectProperty getTransactionDateProperty() {
        return transactionDate;
    }
    
    public final Double getTransactionAmount() {
        return transactionAmount.get();
    }

    public final void setTransactionAmount(Double value) {
        transactionAmount.set(value);
    }

    public final DoubleProperty getTransactionFloatProperty() {
        return transactionAmount;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getTransactionDate());
        out.writeObject(getTransactionName());
        out.writeObject(getTransactionAmount());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setTransactionDate((LocalDate) in.readObject());
        setTransactionName((String) in.readObject());
        setTransactionAmount((Double) in.readObject());        
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        result.append(this.getTransactionDate() + " " 
                + this.getTransactionName() + " " 
                + this.getTransactionAmount());
        return result.toString();
    }
}
