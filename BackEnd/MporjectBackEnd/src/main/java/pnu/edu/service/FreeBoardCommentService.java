package pnu.edu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.FreeBoard;
import pnu.edu.domain.FreeComment;
import pnu.edu.domain.Member;
import pnu.edu.repository.FreeBoardRepository;
import pnu.edu.repository.FreeCommentRepository;
import pnu.edu.repository.MemberRepository;


@Service
@RequiredArgsConstructor
public class FreeBoardCommentService {

	private final FreeCommentRepository fcRepo;
	private final FreeBoardRepository fbRepo;
	private final MemberRepository memRepo;
	private static final Logger logger = LoggerFactory.getLogger(FreeBoardCommentService.class);

	public FreeComment insertFreeComment(int freeBoardId, String username, FreeComment freeComment) {
		
		FreeBoard findfb = fbRepo.findById(freeBoardId).get();
		Member finduser = memRepo.findById(username).get();
		
		freeComment.setFreeBoard(findfb);
		freeComment.setMember(finduser);
		if(freeComment.getParent() != null && freeComment.getParent().getFreeCommentId() >0) {
			 logger.info("Inserting comment: {}", freeComment);
			FreeComment pc = fcRepo.findById(freeComment.getParent().getFreeCommentId()).orElseThrow(()-> new RuntimeException("Not Found ParentComment"));
			freeComment.setParent(pc);
			
			pc.getFcchildlist().add(freeComment);
			fcRepo.save(pc);
		}
		FreeComment newfc = FreeComment.builder()
				.content(freeComment.getContent())
				.createDate(freeComment.getCreateDate())
				.freeBoard(freeComment.getFreeBoard())
				.member(freeComment.getMember())
				.parent(freeComment.getParent())
				.build();
		
		return fcRepo.save(newfc);
	}
}