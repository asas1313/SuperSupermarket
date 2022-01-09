package exceptions;

public class NotEnoughChangeException extends RuntimeException {
    public NotEnoughChangeException() {
        super("We doesn't have sufficient change to complete this transaction. Try again.");
    }
}