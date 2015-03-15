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
public class Institution {
    private final StringProperty institutionName;
    
    public Institution(){
        this.institutionName = new SimpleStringProperty(this, "institutionName", "");
    }
    
    public final String getInstitutionName() { return institutionName.get(); }
    public final void setInstitutionName( String value ) {institutionName.set(value); }
    public final StringProperty getInstitutionNameProperty() { return institutionName; }
}
