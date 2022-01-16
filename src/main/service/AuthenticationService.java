package main.service;

import main.dependencyInjection.annotation.Inject;
import main.exceptions.UserNotFoundException;
import main.records.user.UserDetail;
import main.service.dataAccess.database.DatabaseInterface;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticationService implements ApplicationService {

    private DatabaseInterface databaseInterface;

    @Inject
    public AuthenticationService(DatabaseInterface databaseInterface) {
        this.databaseInterface = databaseInterface;
    }

    public boolean anyUserExists(){
        return this.databaseInterface.anyUserExists();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(password.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean login(String username, String password) {
        UserDetail userDetail = this.databaseInterface.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return userDetail.getPassword().equals(this.hashPassword(password));
    }
}
