package app.trading.users.exception;

public class InsufficientFundsException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Insufficient funds for the requested operation";

    public InsufficientFundsException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
