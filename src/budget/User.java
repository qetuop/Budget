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
public class User implements Externalizable {
    
    private static final long serialVersionUID = 1;

    private final StringProperty firstName;
    private final StringProperty lastName;

    private ObservableList<Institution> institutionList;

    public User() {
        this.firstName = new SimpleStringProperty(this, "firstName", "");
        this.lastName = new SimpleStringProperty(this, "lastName", "");

        institutionList = FXCollections.observableArrayList();
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
        this.institutionList.add(institution);
    }

    ObservableList<Institution> getInstitutionList() {
        return institutionList;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getFirstName());
        out.writeObject(getLastName());

        ArrayList<Institution> tmp = new ArrayList<>(institutionList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setFirstName((String) in.readObject());
        setLastName((String) in.readObject());

        // TODO use setList ?
        ArrayList<Institution> tmp = (ArrayList<Institution>) in.readObject();
        institutionList = FXCollections.observableArrayList(tmp);
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        result.append(this.getFirstName() + " " + this.getLastName());
        return result.toString();
    }

} // User
