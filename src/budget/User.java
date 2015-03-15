/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Brian
 */
public class User {
    private final StringProperty firstName;
    private final StringProperty lastName;
    
    public User() {
        this.firstName = new SimpleStringProperty(this, "firstName", "");
        this.lastName = new SimpleStringProperty(this, "lastName", "");
    }
    public final String getFirstName() { return firstName.get(); }
    public final void setFirstName(String value) { firstName.set(value); }
    public final StringProperty firstNameProperty() { return firstName; }
    
    public final String getLastName() { return lastName.get(); }
    public final void setLastName(String value) { lastName.set(value); }
    public final StringProperty lastNameProperty() { return lastName; }
}
