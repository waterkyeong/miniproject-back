package pnu.edu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pnu.edu.domain.Member;
import pnu.edu.domain.Role;
import pnu.edu.repository.MemberRepository;

//@Component
public class Testdatainit implements ApplicationRunner{

//	@Autowired
//	private FreeBoardRepository freeBRepo;
//	@Autowired
//	private ShareBoardRepository shareBRepo;
	@Autowired
	private MemberRepository memRepo;

	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

//		Member member1 = memRepo.save(Member.builder().username("member").password(encoder.encode("abcd")).alias("mem").email("member@test.com").role(Role.ROLE_MEMBER).enabled(true).build());
//		Member member4 = memRepo.save(Member.builder().username("youyou").password(encoder.encode("abcd")).alias("you").email("you@test.com").role(Role.ROLE_MEMBER).enabled(true).build());
//		Member member5 = memRepo.save(Member.builder().username("meme").password(encoder.encode("abcd")).alias("me").email("me@test.com").role(Role.ROLE_MEMBER).enabled(true).build());
//		Member member2 = memRepo.save(Member.builder().username("admin").password(encoder.encode("abcd")).alias("ad").email("admin@test.com").role(Role.ROLE_ADMIN).enabled(true).build());
//		Member member3 = memRepo.save(Member.builder().username("test").password(encoder.encode("abcd")).alias("tester").email("test@test.com").role(Role.ROLE_MEMBER).enabled(true).build());
//		
//		Member[] members = {member1, member2};
//		for (int i = 1 ; i <= 10 ; i++) {
//			freeBRepo.save(FreeBoard.builder().privateType("public").type("기타").title("title"+i).content("content"+i).member(members[i%2]).view(0).createDate(new Date()).build());
//			shareBRepo.save(ShareBoard.builder().type("나눔").title("title"+i).content("content"+i).adress("부산대학교 학생회관").member(members[i%2]).view(0).createDate(new Date()).build());
//		}
	}
}
