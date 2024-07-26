package ir.javapro.springsecurityjavapro.service;

import ir.javapro.springsecurityjavapro.config.JwtService;
import ir.javapro.springsecurityjavapro.dto.UserRestLoginRequest;
import ir.javapro.springsecurityjavapro.dto.UserRestLoginResponse;
import ir.javapro.springsecurityjavapro.model.User;
import ir.javapro.springsecurityjavapro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("user.not.found"));
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public User findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user.not.found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public UserRestLoginResponse login(UserRestLoginRequest userRestLoginRequest) {
        UserDetails userDetails = loadUserByUsername(userRestLoginRequest.getUsername());
        String token = jwtService.generateToken(userDetails);
        return new UserRestLoginResponse(token);
    }
}
