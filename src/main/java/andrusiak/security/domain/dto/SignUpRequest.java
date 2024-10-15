package andrusiak.security.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "registration request")
public class SignUpRequest {

    @Schema(description = "username", example = "banan123")
    @Size(min = 5, max = 50, message = "username should be from 5 to 50 symbols")
    @NotBlank(message = "username can not be blank")
    private String username;

    @Schema(description = "email", example = "banan@gmail.com")
    @Size(min = 5, max = 255, message = "email should be from 5 to 225 symbols")
    @NotBlank(message = "email can not be blank")
    @Email(message = "invalid email format")
    private String email;

    @Schema(description = "password", example = "banan263*ads")
    @Size(max = 255, message = "password length should be to 255 symbols")
    @NotBlank(message = "password can not be empty")
    private String password;
}