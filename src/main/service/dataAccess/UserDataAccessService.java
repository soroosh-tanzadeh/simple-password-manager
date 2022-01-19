package main.service.dataAccess;

import main.dependencyInjection.annotation.Inject;
import main.records.secure.PasswordStore;
import main.records.user.UserDetail;
import main.service.ApplicationService;
import main.service.AuthenticationService;
import main.service.Service;
import main.service.dataAccess.database.DatabaseInterface;

import java.util.ArrayList;

@Service
public class UserDataAccessService implements ApplicationService {

    private final DatabaseInterface databaseInterface;

    @Inject
    public UserDataAccessService(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public void addPassword(String title, String username, String password, String website, String description) {
        UserDetail currentUser = this.databaseInterface.getCurrentUser();
        currentUser.addPassword(new PasswordStore(title, username, password, website, description));
        this.databaseInterface.updateUser(currentUser);
        this.databaseInterface.save();
        AuthenticationService.currentUser = currentUser;
    }

    public ArrayList<PasswordStore> getPasswords() {
        UserDetail currentUser = AuthenticationService.currentUser;
        return currentUser.getPasswordStores();
    }

    public void removePassword(String id) {
        UserDetail currentUser = AuthenticationService.currentUser;
        currentUser.removePassword(id);
        System.out.println(currentUser.getPasswordStores());
        this.databaseInterface.updateUser(currentUser);
        this.databaseInterface.save();
        AuthenticationService.currentUser = currentUser;
    }

}
