package storm.server.gateway.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
public class AuthoritiesKey  implements Serializable {

    private static final long serialVersionUID = -1257821517891392898L;
    private String username;
    private String authority;

    // Default constructor, getters, setters, equals, and hashCode
    public AuthoritiesKey() {
    }

}

