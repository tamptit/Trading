package storm.server.gateway.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@IdClass(AuthoritiesKey.class)
//@Table(name = "authorities")
@Getter
@Setter
public class Authorities {

    @Id
    private String authority;  // ✅ Phải trùng với AuthoritiesId.java

    @Id
    private String username;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private Users user;

    public Authorities() {
    }

}