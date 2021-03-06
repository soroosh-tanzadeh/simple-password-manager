package main.service.dataAccess.database;

import main.records.user.UserDetail;
import main.service.ApplicationService;
import main.service.Service;

import java.util.Optional;

@Service
public interface DatabaseInterface extends ApplicationService {
    boolean createUser(String username, String password, String name, String phoneNumber, String email);

    boolean anyUserExists();

    UserDetail getCurrentUser();

    void setCurrentUser(UserDetail user);
    void updateUser(UserDetail user);

    void save();

    Optional<UserDetail> findByUsername(String username);
}
