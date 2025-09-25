package storm.server.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storm.server.gateway.model.Authorities;
import storm.server.gateway.model.Users;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

}
