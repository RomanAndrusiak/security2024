package andrusiak.security.controller;

import andrusiak.security.domain.dto.UserDto;
import andrusiak.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.getAllUsersDto());
    }
}
