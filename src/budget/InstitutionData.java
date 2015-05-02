/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import static budget.UserData.USER_SELECTION;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class InstitutionData implements Externalizable {
    private ObservableList<Institution> institutionList = FXCollections.observableArrayList();
    private Institution selectedInstitution = new Institution();
    
    public static final String INSTITUTION_SELECTION = "institution_selection";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public void addInstitution( Institution institution ) {
        institutionList.add(institution);
    }
    
    public ObservableList<Institution> getInstitutionList() {
        return institutionList;
    }
    
    public Institution getInstitution(){
        return new Institution();
    }

    // set by table selection, use index?
    public void setSelectedInstitution(Institution institution) {
        System.out.println("ID::setSelectedInstitution " +pcs.getPropertyChangeListeners().length);
        Institution oldSelectedInstitution = selectedInstitution;
        selectedInstitution = institution;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, INSTITUTION_SELECTION, oldSelectedInstitution, selectedInstitution);
        pcs.firePropertyChange(evt);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        System.out.println("ID::addPropertyChangeListener");
        pcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ArrayList<Institution> tmp = new ArrayList<>(institutionList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ArrayList<Institution> tmp = (ArrayList<Institution>) in.readObject();
        institutionList = FXCollections.observableArrayList(tmp);
    }

    Institution getSelectedInstitution() {
        return selectedInstitution;
    }

    AccountData getAccountData() {
        return selectedInstitution.getAccountData();
    }
}
