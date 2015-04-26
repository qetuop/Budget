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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class InstitutionData implements Externalizable {
    private ObservableList<Institution> institutionList = FXCollections.observableArrayList();
    private User selectedInstitution;
    
    public void addInstitution( Institution institution ) {
        institutionList.add(institution);
    }
    
    public ObservableList<Institution> getInstitutionList() {
        return institutionList;
    }
    
    public Institution getInstitution(){return new Institution();}

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
}
