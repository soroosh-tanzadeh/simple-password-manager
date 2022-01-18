package main.records;

import main.records.user.UserDetail;

import java.io.Serializable;
import java.util.Set;

public class Database implements Serializable {
    private Set<UserDetail> users;
    private UserDetail currentUser;
    public Database(Set<UserDetail> users) {
        this.users = users;
    }

    public Set<UserDetail> getUsers() {
        return users;
    }

    public boolean add(UserDetail userDetail) {
        return users.add(userDetail);
    }

    public boolean remove(UserDetail userDetail) {
        return users.remove(userDetail);
    }

    public UserDetail getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDetail currentUser) {
        this.currentUser = currentUser;
    }
}
