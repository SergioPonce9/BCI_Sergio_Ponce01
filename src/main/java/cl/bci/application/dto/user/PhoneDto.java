package cl.bci.application.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PhoneDto {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message="El campo number no es valido")
    private String number;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message="El campo citycode no es valido")
    @Size(max = 4, message = "El campo citycode no puede superar los 4 caracteres")
    private String citycode;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{2}$", message="El campo contrycode no es valido")
    private String contrycode;
}
