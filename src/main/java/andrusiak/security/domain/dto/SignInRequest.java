package andrusiak.security.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "login request")
public class SignInRequest {

    @Schema(description = "username", example = "banan123")
    @Size(min = 5, max = 50, message = "username should be from 5 to 50 symbols")
    @NotBlank(message = "username can not be blank")
    private String username;

    @Schema(description = "password", example = "banan263*ads")
    @Size(max = 255, message = "password length should be to 255 symbols")
    @NotBlank(message = "password can not be empty")
    private String password;
}