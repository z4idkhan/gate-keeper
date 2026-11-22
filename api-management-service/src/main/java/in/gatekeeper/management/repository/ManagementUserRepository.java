package in.gatekeeper.management.repository;

import in.gatekeeper.management.entity.ManagementUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagementUserRepository extends JpaRepository<ManagementUser,Long> {

    Optional<ManagementUser>findByUserName(String username);

}
