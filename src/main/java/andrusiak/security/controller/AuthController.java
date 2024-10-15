package andrusiak.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import andrusiak.security.domain.dto.JwtAuthenticationResponse;
import andrusiak.security.domain.dto.SignInRequest;
import andrusiak.security.domain.dto.SignUpRequest;
import andrusiak.security.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Sign up")
    @PostMapping("/register")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Sign in")
    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}

