package in.gatekeeper.management.service;

import in.gatekeeper.management.dto.requestdto.LoginRequest;
import in.gatekeeper.management.dto.responsedto.LoginResponse;
import in.gatekeeper.management.entity.ManagementUser;
import in.gatekeeper.management.repository.ManagementUserRepository;
import in.gatekeeper.management.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ManagementUserRepository userRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

//    public RegisterResponse register(RegisterRequest request) {
//
//        if (userRepo.findByEmail(request.email()).isPresent()) {
//            throw new ResponseStatusException(
//                    HttpStatus.CONFLICT, "Email already registered"
//            );
//        }
//
//        ManagementUser user = new ManagementUser();
//        user.setUsername(request.username());
//        user.setEmail(request.email());
//        user.setPassword(passwordEncoder.encode(request.password()));
//        user.setRole(Role.USER);
//
//        User savedUser = userRepo.save(user);
//
//        String token = jwtService.generateToken(savedUser);
//
//        return new AuthResponse(token);
//    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        ManagementUser user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"
                ));

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,"Bearer",3600,
                user.getRole().getName(),null
        );
    }
    private RuntimeException invalidCredentials(){
        return new RuntimeException("Invalid credentials");
    }
}
