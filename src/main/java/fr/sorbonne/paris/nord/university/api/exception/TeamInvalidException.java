package fr.sorbonne.paris.nord.university.api.exception;

public class TeamInvalidException extends RuntimeException {
    //private static final long serialVersionUID = 5533062491519219409L;

    public TeamInvalidException(String message) {
        super(message);
    }
}