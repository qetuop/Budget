/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class InstitutionData {
    private ObservableList<Institution> instutuionList;
    
    public void addInstitution( Institution institution ) {
        instutuionList.add(institution);
    }
    
    public Institution getInstitution(){return new Institution();}
}
