package main.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("No User Found with this user name");
    }
}
