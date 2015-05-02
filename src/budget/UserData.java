/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Brian
 */
public class UserData implements Externalizable {

    private ObservableList<User> userList = FXCollections.observableArrayList();
    private User selectedUser = new User();

    public static final String USER_SELECTION = "user_selection";
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void UserData() {
    }

    public void addUser(User user) {
        userList.add(user);
    }

    // TODO: Fix this
    public User getUser() {
        return userList.get(0);
    }

    public void setUserList(ObservableList<User> ul) {
        this.userList = ul;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    // set by table selection, use index?
    public void setSelectedUser(User user) {
        User oldSelectedUser = selectedUser;
        selectedUser = user;

        PropertyChangeEvent evt = new PropertyChangeEvent(this, USER_SELECTION, oldSelectedUser, selectedUser);
        pcs.firePropertyChange(evt);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        ArrayList<User> tmp = new ArrayList<>(userList);
        out.writeObject(tmp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        ArrayList<User> tmp = (ArrayList<User>) in.readObject();
        userList = FXCollections.observableArrayList(tmp);
    }

    InstitutionData getInstitutionData() {
        return selectedUser.getInstitutionData();
    }

}
