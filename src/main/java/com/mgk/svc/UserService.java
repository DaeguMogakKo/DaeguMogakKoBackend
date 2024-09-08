package com.mgk.svc;


import com.mgk.user.SiteUser;
import com.mgk.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String username, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		// 스프링 시큐리티의 BCryptPasswordEncoder 클래스를 사용하여 암호화하여 비밀번호를 저장
		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}
}