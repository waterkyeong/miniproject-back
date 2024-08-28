package pnu.edu.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.FreeBoard;
import pnu.edu.domain.FreeComment;
import pnu.edu.domain.Member;
import pnu.edu.domain.dto.FreeCommentDTO;
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

	public FreeComment insertFreeComment(int freeBoardId, String username, FreeCommentDTO freeCommentDTO) {
		
		FreeBoard findfb = fbRepo.findById(freeBoardId).get();
		Member finduser = memRepo.findById(username).get();
		
		freeCommentDTO.setFreeBoardId(findfb.getFreeBoardId());
		freeCommentDTO.setUsername(finduser.getUsername());
		
		FreeComment newfc = FreeComment.builder()
				.content(freeCommentDTO.getContent())
				.createDate(new Date())
				.freeBoard(findfb)
				.member(finduser)
				.build();
		
		if(freeCommentDTO.getParentId() != null && freeCommentDTO.getParentId()>0) {
			 logger.info("Inserting comment: {}", freeCommentDTO);
			FreeComment fpc = fcRepo.findById(freeCommentDTO.getParentId()).orElseThrow(()-> new RuntimeException("Not Found ParentComment"));
			newfc.setParent(fpc);
			fpc.getFcchildlist().add(newfc);
			fcRepo.save(fpc);
		}else {
			newfc.setParent(null);
		}
		
		return fcRepo.save(newfc);
	}
//	public FreeComment insertFreeComment(int freeBoardId, String username, FreeComment freeComment) {
//		
//		FreeBoard findfb = fbRepo.findById(freeBoardId).get();
//		Member finduser = memRepo.findById(username).get();
//		
//		freeComment.setFreeBoard(findfb);
//		freeComment.setMember(finduser);
//		if(freeComment.getParent() != null && freeComment.getParent().getFreeCommentId() >0) {
//			logger.info("Inserting comment: {}", freeComment);
//			FreeComment pc = fcRepo.findById(freeComment.getParent().getFreeCommentId()).orElseThrow(()-> new RuntimeException("Not Found ParentComment"));
//			freeComment.setParent(pc);
//			
//			pc.getFcchildlist().add(freeComment);
//			fcRepo.save(pc);
//		}
//		FreeComment newfc = FreeComment.builder()
//				.content(freeComment.getContent())
//				.createDate(freeComment.getCreateDate())
//				.freeBoard(freeComment.getFreeBoard())
//				.member(freeComment.getMember())
//				.parent(freeComment.getParent())
//				.build();
//		
//		return fcRepo.save(newfc);
//	}
	
	public FreeComment updateFreeComment(int freeCommentId, FreeComment freeComment) {
		FreeComment findfc =  fcRepo.findById(freeCommentId).get();
		findfc.setContent(freeComment.getContent());
		findfc.setCreateDate(freeComment.getCreateDate());
		
		return fcRepo.save(findfc);
	}
	
	public FreeComment deleteFreeComment(int freeCommentId) {
		FreeComment findfc = fcRepo.findById(freeCommentId).get();
		findfc.setDeleted(true);
		fcRepo.save(findfc);
		return findfc;
	}
}
	
	