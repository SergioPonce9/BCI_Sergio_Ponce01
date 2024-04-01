package cl.bci.application.service.contracts;

import cl.bci.application.dto.user.UserDto;
import cl.bci.application.dto.user.UserResponseDto;

import java.util.List;

public interface IUserService {
    List<UserDto> getAll();
    UserResponseDto getByEmail(String email);
    UserResponseDto save(UserDto userDto) throws Exception;
    boolean update(UserDto dto) throws Exception;
    boolean delete(String email);
}
