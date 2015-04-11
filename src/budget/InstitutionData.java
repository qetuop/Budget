/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class InstitutionData {
    private ObservableList<Institution> institutionList = FXCollections.observableArrayList();
    private User selectedInstitution;
    
    public void addInstitution( Institution institution ) {
        institutionList.add(institution);
    }
    
    public ObservableList<Institution> getInstitutionList() {
        return institutionList;
    }
    
    public Institution getInstitution(){return new Institution();}
}
