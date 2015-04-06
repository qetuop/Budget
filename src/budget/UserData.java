/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author Brian
 */
public class UserData {
    private ObservableList<User> userList;
    private User selectedUser;
    
    public void addUser(User user){
        userList.add(user);
    }
    public User getUser(){return new User();}
    
    public void setUserList( ObservableList<User> ul){ 
        this.userList = ul;
    }
    
    public ObservableList<User> getUserList() {
        return userList;
    }
    
    public User getSelectedUser(){
        return selectedUser;
    }
    
    // set by table selection, use index?
    public void setSelectedUser(User user) {
        selectedUser = user;
    }
    
}
