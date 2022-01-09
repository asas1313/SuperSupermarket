package exceptions;

public class PayNotAcceptedException extends RuntimeException {
    public PayNotAcceptedException() {
        super("Was provided non-accepted bills or coins. Try again.");
    }
}