package cl.bci.infrastructure.controller;

import cl.bci.application.config.JwtTokenUtil;
import cl.bci.application.dto.general.JwtRequestDTO;
import cl.bci.application.dto.general.JwtResponseDTO;
import cl.bci.application.dto.general.LoginDTO;
import cl.bci.application.dto.user.UserDto;
import cl.bci.application.service.JwtUserService;
import cl.bci.application.service.TokenService;
import cl.bci.application.service.UserService;
import cl.bci.domain.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private JwtUserService jwtUserService;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
    public LoginDTO login(@RequestBody @Valid LoginDTO loginDTO){
		//String token = tokenService.generateAuthenticationToken(loginDTO.getUser(), loginDTO.getToken());
		String token = getJWTToken(loginDTO.getUser());
		LoginDTO user = new LoginDTO();
		user.setUser(loginDTO.getUser());
		user.setToken(token);
		return user;

	}

	private String getJWTToken(String username) {
		String secretKey = "BCI001";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.setId("JWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				//.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}