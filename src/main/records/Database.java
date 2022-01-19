package main.records;

import main.records.user.UserDetail;

import java.io.Serializable;
import java.util.Set;

public class Database implements Serializable {
    private Set<UserDetail> users;
    private String currentUser;

    public Database(Set<UserDetail> users) {
        this.users = users;
    }

    public Set<UserDetail> getUsers() {
        return users;
    }

    public boolean add(UserDetail userDetail) {
        return users.add(userDetail);
    }

    public void updateUser(UserDetail user) {
        this.users.remove(user);
        this.users.add(user);
    }

    public UserDetail getCurrentUser() {
        return users.stream()
                .filter(user -> user.getUsername().equals(currentUser)).
                findAny()
                .orElse(null);
    }

    public void setCurrentUser(UserDetail currentUser) {
        this.currentUser = currentUser != null ? currentUser.getUsername() : null;
    }
}
