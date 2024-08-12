package pnu.edu;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import pnu.edu.domain.FreeBoard;
import pnu.edu.domain.Member;
import pnu.edu.domain.Role;
import pnu.edu.repository.FreeBoardRepository;
import pnu.edu.repository.MemberRepository;

//@Component
public class Testdatainit implements ApplicationRunner{

	@Autowired
	private FreeBoardRepository freeBRepo;
	@Autowired
	private MemberRepository memRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		Member member1 = memRepo.save(Member.builder().username("member").password("abcd").alias("mem").email("member@test.com").role(Role.ROLE_MEMBER).enabled(true).build());
		Member member2 = memRepo.save(Member.builder().username("admin").password("abcd").alias("ad").email("admin@test.com").role(Role.ROLE_ADMIN).enabled(true).build());
		
		Member[] members = {member1, member2};
		for (int i = 1 ; i <= 10 ; i++) {
			freeBRepo.save(FreeBoard.builder().privateType("public").type("기타").title("title"+i).content("content"+i).member(members[i%2]).view(0).createDate(new Date()).build());
		}
	}
}
