package main.records.secure;

import java.io.Serializable;
import java.util.UUID;

public class PasswordStore implements Serializable {
    private String id;
    private String password;
    private String title;
    private String username;
    private String description;
    private String website;

    public PasswordStore(String password, String title, String username) {
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.title = title;
        this.username = username;
    }

    public PasswordStore(String password, String title, String username, String description, String website) {
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.title = title;
        this.username = username;
        this.description = description;
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordStore that = (PasswordStore) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
