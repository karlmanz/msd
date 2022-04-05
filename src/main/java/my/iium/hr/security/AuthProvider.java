package my.iium.hr.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import my.iium.hr.security.Attempts;
//import my.iium.hr.security.MyUser;
//import my.iium.hr.security.AttemptsRepository;
//import my.iium.hr.security.MyUserRepository;

@Component
public class AuthProvider implements AuthenticationProvider {
	private static final int ATTEMPTS_LIMIT = 3;
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AttemptsRepository attemptsRepository;
	@Autowired
	private MyUserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		System.out.println("Attempt Name : " + username);

		Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
		if (userAttempts.isPresent()) {
			Attempts attempts = userAttempts.get();
			attempts.setAttempts(0);
			attemptsRepository.save(attempts);

		} else
			throw new BadCredentialsException("Bad Credentials");

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

	private void processFailedAttempts(String username, MyUser user) {
		Optional<Attempts> userAttempts = attemptsRepository.findAttemptsByUsername(username);
		if (!userAttempts.isPresent()) { // edited
			Attempts attempts = new Attempts();
			attempts.setUsername(username);
			attempts.setAttempts(1);
			attemptsRepository.save(attempts);
		} else {
			Attempts attempts = userAttempts.get();
			attempts.setAttempts(attempts.getAttempts() + 1);
			attemptsRepository.save(attempts);

			if (attempts.getAttempts() + 1 > ATTEMPTS_LIMIT) {
				user.setAccountNonLocked(false);
				userRepository.save(user);
				throw new LockedException("Too many invalid attempts. Account is locked!!");
			}
		}
	}

}
