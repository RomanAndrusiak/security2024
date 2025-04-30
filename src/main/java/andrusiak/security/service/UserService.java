package andrusiak.security.service;

import andrusiak.security.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import andrusiak.security.domain.model.Role;
import andrusiak.security.domain.model.User;
import andrusiak.security.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }


    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("user with such username already exists");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("user with such email already exists");
        }

        return save(user);
    }


    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public User getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    public void activate(Long id) {
        User user = getById(id);
        user.setEnable(true);
        save(user);
    }

    public List<UserDto> getAllUsersDto() {
        return repository.findAll()
                .stream()
                .map(UserDto::new)
                .toList();
    }
}
