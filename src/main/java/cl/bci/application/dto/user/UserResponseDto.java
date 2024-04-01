package cl.bci.application.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserResponseDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Date dateCreated;
    private Date dateModified;
    private Date lastLogin;
    private String token;
    private boolean isactive;
    private List<PhoneDto> phones;

}
