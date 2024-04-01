package cl.bci.infrastructure.repository;

import cl.bci.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Users findOneByEmail(String email);
    Users findOneByName(String name);
}
