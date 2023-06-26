package astratech.prg7_m5_p2_019.repository;

import astratech.prg7_m5_p2_019.model.AdminCredential;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminCredentialRepository extends CrudRepository<AdminCredential, Integer> {
    @Query(value = "SELECT h FROM AdminCredential h WHERE h.username = :username")
    AdminCredential getUserByUsername (@Param("username") String username);
}
