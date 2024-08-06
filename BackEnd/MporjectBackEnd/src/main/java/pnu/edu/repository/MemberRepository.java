package pnu.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
