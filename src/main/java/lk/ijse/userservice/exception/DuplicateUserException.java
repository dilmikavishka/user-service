package lk.ijse.userservice.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String id) {
        super("User already exists with ID: " + id);
    }
}
