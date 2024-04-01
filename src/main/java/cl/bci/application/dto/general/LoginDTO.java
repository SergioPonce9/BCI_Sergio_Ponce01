package cl.bci.application.dto.general;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String user;
	private String token;

	public LoginDTO()
	{

	}

	public LoginDTO(String username, String password) {
		this.setUser(username);
		this.setToken(password);
	}

}