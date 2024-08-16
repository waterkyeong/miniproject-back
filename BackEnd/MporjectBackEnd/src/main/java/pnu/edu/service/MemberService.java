package pnu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pnu.edu.domain.Member;
import pnu.edu.domain.Role;
import pnu.edu.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memRepo;
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public Member signin(Member member) {
		
		Member newMem =  Member.builder()
				.username(member.getUsername())
				.password(encoder.encode(member.getPassword()))
				.alias(member.getAlias())
				.email(member.getEmail())
				.role(Role.ROLE_MEMBER)
				.enabled(true).build();
		
		return memRepo.save(newMem);
	}
	
}
