package cl.bci.infrastructure.repository;

import cl.bci.domain.Phones;

import cl.bci.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, String> {
    Phones findOneByNumberAndUserId(String number, Users user);
}
