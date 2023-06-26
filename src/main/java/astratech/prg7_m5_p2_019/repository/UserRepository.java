package astratech.prg7_m5_p2_019.repository;

import astratech.prg7_m5_p2_019.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
