package cl.bci.application.dto.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class GeneralRequestDto<T> {
    @JsonProperty
    @Valid
    private T params;
}