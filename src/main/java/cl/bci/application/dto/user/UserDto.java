package cl.bci.application.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "El campo name no puede ser nulo")
    @NotEmpty(message = "El campo name no puede estar vacío")
    private String name;

    @NotNull(message = "El campo email no puede ser nulo")
    @NotEmpty(message = "El campo email no puede estar vacío")
    @Size(max = 50, message = "El campo email no puede superar los 50 caracteres")
    @Email
    private String email;

    @NotNull(message = "El campo password no puede ser nulo")
    @NotEmpty(message = "El campo password no puede estar vacío")
    @Size(max = 8, message = "El campo password no puede superar los 8 caracteres")
    private String password;

    @Valid
    @NotNull(message = "la Lista phones no puede ser nulo")
    @NotEmpty(message = "la Lista phones no puede estar vacío")
    private List<PhoneDto> phones;

}
