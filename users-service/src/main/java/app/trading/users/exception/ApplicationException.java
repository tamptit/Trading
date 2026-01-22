package app.trading.users.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vito Nguyen (<a href="https://github.com/cuongnh28">...</a>)
 */

@Getter
public abstract class ApplicationException extends RuntimeException {
    public abstract HttpStatus getHttpStatus();

    private final List<String> messages;

    protected ApplicationException() {
        super();
        this.messages = null;
    }

    protected ApplicationException(String... messages) {
        super(messages.length > 0 ? messages[0] : null);
        this.messages = Arrays.asList(messages);
    }
}
