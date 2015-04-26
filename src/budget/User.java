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
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class User implements Externalizable {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private InstitutionData institutionData;

    public User() {
        this.firstName = new SimpleStringProperty(this, "firstName", "");
        this.lastName = new SimpleStringProperty(this, "lastName", "");

        this.institutionData = new InstitutionData();
    }

    public User(String firstName, String lastName) {
        this();
        
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public final String getFirstName() {
        return firstName.get();
    }

    public final void setFirstName(String value) {
        firstName.set(value);
    }

    public final StringProperty firstNameProperty() {
        return firstName;
    }

    public final String getLastName() {
        return lastName.get();
    }

    public final void setLastName(String value) {
        lastName.set(value);
    }

    public final StringProperty lastNameProperty() {
        return lastName;
    }

    public void addInstitution(Institution institution) {
        this.institutionData.addInstitution(institution);
    }

    public InstitutionData getInstitutionData() {
        return institutionData;
    }

    //public Institution getInstitution();
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getFirstName());
        out.writeObject(getLastName());
        out.writeObject(institutionData);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setFirstName((String) in.readObject());
        setLastName((String) in.readObject());
        
        institutionData = (InstitutionData)in.readObject();
    }

    Institution getSelectedInstitution() {
        return institutionData.getSelectedInstitution();
    }
}
