package andrusiak.security.service;

import andrusiak.security.domain.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import andrusiak.security.domain.dto.JwtAuthenticationResponse;
import andrusiak.security.domain.dto.SignInRequest;
import andrusiak.security.domain.dto.SignUpRequest;
import andrusiak.security.domain.model.Role;
import andrusiak.security.domain.model.User;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final ActivationService activationService;


    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .isEnable(false)
                .build();

        user = userService.create(user);
        String key = activationService.generateCode(user.getId());
        emailService.sendEmail(new EmailDto(user.getEmail(), "Account activation",
                "To activate your account, please, follow this link:\n" +
                        "http://localhost:8081/auth/activate/" + key));
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
