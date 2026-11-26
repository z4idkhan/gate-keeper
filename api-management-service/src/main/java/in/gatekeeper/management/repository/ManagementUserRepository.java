package in.gatekeeper.management.repository;

import in.gatekeeper.management.entity.ManagementUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ManagementUserRepository extends JpaRepository<ManagementUser,Long> {

    Optional<ManagementUser>findByUserName(String username);
    Optional<ManagementUser>findByEmail(String email);
    // List<ManagementUser> findByRole(Long role);

}
