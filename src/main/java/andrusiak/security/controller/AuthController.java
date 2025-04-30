package andrusiak.security.controller;

import andrusiak.security.service.ActivationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import andrusiak.security.domain.dto.JwtAuthenticationResponse;
import andrusiak.security.domain.dto.SignInRequest;
import andrusiak.security.domain.dto.SignUpRequest;
import andrusiak.security.service.AuthenticationService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final ActivationService activationService;

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
    @Operation(summary = "Activate")
    @GetMapping("/activate/{key}")
    public ResponseEntity<?> activate(@PathVariable("key") String key) {
        try {
            activationService.activate(key);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

