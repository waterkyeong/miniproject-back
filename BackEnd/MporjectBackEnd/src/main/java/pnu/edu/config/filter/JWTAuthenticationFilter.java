package pnu.edu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pnu.edu.domain.Member;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res){
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Member member = mapper.readValue(req.getInputStream(), Member.class);
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			return authenticationManager.authenticate(authToken);
		}catch(Exception e) {
			log.info(e.getMessage());
		}
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		return null;
	}
	
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		log.info("successfulAuthentication");
		User user = (User)authResult.getPrincipal();
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
				.withClaim("username", user.getUsername())
				.sign(Algorithm.HMAC256("pnu.edu.jwt"));
		res.addHeader(HttpHeaders.AUTHORIZATION,"Bearer "+ token);
		res.setStatus(HttpStatus.OK.value());
	}
	
}
