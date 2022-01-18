package main.service.dataAccess.database;

import main.records.Database;
import main.records.user.UserDetail;
import main.service.AuthenticationService;

import java.io.*;
import java.util.HashSet;
import java.util.Optional;

public class FileDatabaseService implements DatabaseInterface {

    private Database database;

    public FileDatabaseService() throws IOException, ClassNotFoundException {
        File dbFile = new File("database.db");
        if (dbFile.exists()) {
            this.loadDatabase(dbFile);
        } else if (dbFile.createNewFile()) {
            this.database = new Database(new HashSet<>());
            this.writeDatabase(dbFile);
        }
    }

    private void loadDatabase(File dbFile) throws IOException, ClassNotFoundException {
        ObjectInputStream outputStream = new ObjectInputStream(new FileInputStream(dbFile));
        this.database = (Database) outputStream.readObject();
    }

    private void writeDatabase(File dbFile) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dbFile));
        outputStream.writeObject(this.database);
    }

    public void save() {
        try {
            this.writeDatabase(new File("database.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createUser(String username, String password, String name, String phoneNumber, String email) {
        boolean addResult = this.database.add(new UserDetail(username, AuthenticationService.hashPassword(password), name, phoneNumber, email));
        try {
            this.writeDatabase(new File("database.db"));
        } catch (IOException e) {
            addResult = false;
            e.printStackTrace();
        }
        return addResult;
    }


    @Override
    public boolean anyUserExists() {
        return this.database.getUsers().size() > 0;
    }

    @Override
    public UserDetail getCurrentUser() {
        return this.database.getCurrentUser();
    }

    @Override
    public void setCurrentUser(UserDetail user) {
        this.database.setCurrentUser(user);
    }

    @Override
    public Optional<UserDetail> findByUsername(String username) {
        return this.database.getUsers().stream().filter(userDetail -> userDetail.getUsername().equals(username)).findFirst();
    }
}
