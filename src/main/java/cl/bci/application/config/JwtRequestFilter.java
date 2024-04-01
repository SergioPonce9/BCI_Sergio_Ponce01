package cl.bci.application.config;

import cl.bci.application.service.JwtUserService;
import cl.bci.domain.Users;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
			final String requestTokenHeader = request.getHeader("Authorization");

			String username = null;
			String jwtToken = null;

			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				String token = requestTokenHeader;
				jwtToken = token.substring(7, token.length()-7);
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					System.out.println("JWT Token has expired");
				}
			} else {
				logger.warn("JWT Token does not begin with Bearer String");
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.userService.loadUserByUsername(username);

				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

					Users userEntity = this.userService.getUserByName(username);
					userEntity.setLastLogin(new Date());
					userService.updateUser(userEntity);

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}

		chain.doFilter(request, response);
	}

}