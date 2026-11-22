
package in.gatekeeper.management.service;

import in.gatekeeper.management.dto.requestdto.LoginRequest;
import in.gatekeeper.management.dto.responsedto.LoginResponse;
import in.gatekeeper.management.repository.ManagementUserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final ManagementUserRepository manageUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;
    private final Long expirationSeconds;

    @Transactional(readOnly = true)
    public LoginResponse login(@Valid LoginRequest request) {

        // Authentication logic will go here

        return new LoginResponse();
    }
}
