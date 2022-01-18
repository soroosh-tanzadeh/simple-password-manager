package main.records.user;

import main.records.secure.PasswordStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public final class UserDetail implements Serializable {
    private final String username;
    private final String password;
    private String name;
    private String phoneNumber;
    private String email;

    private ArrayList<PasswordStore> passwordStores;

    public UserDetail(String username, String password) {
        this.username = username;
        this.password = password;
        this.passwordStores = new ArrayList<>();
    }

    public UserDetail(String username, String password, String name, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordStores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<PasswordStore> getPasswordStores() {
        return passwordStores;
    }

    public boolean addPassword(PasswordStore passwordStore) {
        return passwordStores.add(passwordStore);
    }

    public void removePassword(String id) {
        this.passwordStores = (ArrayList<PasswordStore>) passwordStores.stream()
                .filter(passwordStore -> !passwordStore.getId().equals(id))
                .collect(Collectors.toList());
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetail that = (UserDetail) o;

        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "UserDetail[" +
                "username=" + username + ", " +
                "password=" + password + ']';
    }


}
