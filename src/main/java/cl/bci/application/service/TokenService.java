package cl.bci.application.service;

import cl.bci.application.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	public String generateAuthenticationToken(String name, String password) throws Exception {
		authenticate(name, password);

		final UserDetails userDetails = userService.loadUserByUsername(name);

		return jwtTokenUtil.generateToken(userDetails);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
