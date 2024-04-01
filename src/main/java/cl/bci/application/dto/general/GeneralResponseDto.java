package cl.bci.application.dto.general;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponseDto<T> {
    private T data;
}
