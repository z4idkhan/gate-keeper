package in.gatekeeper.management.security;
import in.gatekeeper.management.repository.ManagementUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

    private final ManagementUserRepository repo;  //  User repository

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) repo.findByUserName(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found "+email));
    }
}

