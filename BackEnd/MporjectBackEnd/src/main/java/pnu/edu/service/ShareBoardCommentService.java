package pnu.edu.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.Member;
import pnu.edu.domain.ShareBoard;
import pnu.edu.domain.ShareComment;
import pnu.edu.domain.dto.ShareCommentDTO;
import pnu.edu.repository.MemberRepository;
import pnu.edu.repository.ShareBoardRepository;
import pnu.edu.repository.ShareCommentRepository;

@Service
@RequiredArgsConstructor
public class ShareBoardCommentService {

	private final ShareCommentRepository scRepo;
	private final ShareBoardRepository sbRepo;
	private final MemberRepository memRepo;
	
	public ShareComment insertFreeComment(int shareBoardId, String username, ShareCommentDTO shareCommentDTO) {

		ShareBoard findsb = sbRepo.findById(shareBoardId).get();
		Member finduser = memRepo.findById(username).get();
		
		shareCommentDTO.setShareBoardid(findsb.getShareBoardId());
		shareCommentDTO.setUsername(finduser.getUsername());
		
		ShareComment newsc = ShareComment.builder()
				.content(shareCommentDTO.getContent())
				.createDate(new Date())
				.shareBoard(findsb)
				.member(finduser)
				.build();
		if(shareCommentDTO.getParentId() != null && shareCommentDTO.getParentId()>0) {
			ShareComment spc = scRepo.findById(shareCommentDTO.getParentId()).orElseThrow(()->new RuntimeException("Not Found ParentComment"));
			newsc.setParent(spc);
			spc.getScchildlist().add(newsc);
			scRepo.save(spc);
		}else {
			newsc.setParent(null);
		}
		
		return scRepo.save(newsc);
	}
	
	public ShareComment updateShareComment(int shareCommentId, ShareComment shareComment) {
		ShareComment findsc = scRepo.findById(shareCommentId).get();
		findsc.setContent(shareComment.getContent());
		findsc.setCreateDate(shareComment.getCreateDate());

		return scRepo.save(findsc);
	}
	
	public ShareComment deleteShareComment(int shareCommentId) {
		ShareComment findsc = scRepo.findById(shareCommentId).get();
		findsc.setDeleted(true);
		scRepo.save(findsc);
		return findsc;
	}
}
