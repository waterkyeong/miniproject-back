package pnu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.ShareBoard;
import pnu.edu.domain.ShareBoardImgs;
import pnu.edu.domain.ShareComment;
import pnu.edu.domain.dto.ShareBoardDTO;
import pnu.edu.domain.dto.ShareCommentDTO;
import pnu.edu.repository.ShareBoardImgsRepository;
import pnu.edu.repository.ShareBoardRepository;
import pnu.edu.repository.ShareCommentRepository;
import pnu.edu.util.Fileutil;

@Service
@RequiredArgsConstructor
public class ShareBoardService {

	private final ShareBoardRepository shareRepo;
	private final ShareBoardImgsRepository shardImgRepo;
	private final ShareCommentRepository shareComRepo;
	
//	public List<ShareBoardDTO> getShareBoard(int page, int size) {
//		
//		Pageable pageable = PageRequest.of(page, size);
//		
//		Page<ShareBoard> list = shareRepo.findAll(pageable);
//		
//		List<ShareBoardDTO> ret = new ArrayList<>();
//		for(ShareBoard sb : list) {
//			
//			ShareBoardDTO sdto = new ShareBoardDTO();
//			sdto.setShareBoardId(sb.getShareBoardId());
//			sdto.setType(sb.getType());
//			sdto.setTitle(sb.getTitle());
//			sdto.setContent(sb.getContent());
//			sdto.setUsername(sb.getMember().getUsername());
//			sdto.setView(sb.getView());
//			sdto.setCreateDate(sb.getCreateDate());
//			ret.add(sdto);
//		}
//		return ret;
//	}
	
	public List<ShareBoardDTO> getShareBoard() {
		List<ShareBoard> slist = shareRepo.findAll();
		List<ShareBoardDTO> list = new ArrayList<>();
		for(ShareBoard sb : slist) {
			ShareBoardDTO sdto = new ShareBoardDTO();
			sdto.setShareBoardId(sb.getShareBoardId());
			sdto.setType(sb.getType());
			sdto.setTitle(sb.getTitle());
			sdto.setUsername(sb.getMember().getUsername());
			sdto.setView(sb.getView());
			sdto.setCreateDate(sb.getCreateDate());
			list.add(sdto);
		}
		return list;
	}
	
	public ShareBoardDTO getShareBoard(int shareboardid) {
		
		countView(shareboardid);
		
		ShareBoard finds = shareRepo.findById(shareboardid).get();
		
		ShareBoardDTO sbi = new ShareBoardDTO();
		
		sbi.setShareBoardId(finds.getShareBoardId());
		sbi.setType(finds.getType());
		sbi.setTitle(finds.getTitle());
		sbi.setContent(finds.getContent());
		sbi.setAddress(finds.getAddress());
		sbi.setUsername(finds.getMember().getUsername());
		sbi.setView(finds.getView());
		sbi.setCreateDate(finds.getCreateDate());
		if(finds.getSimges() != null) {
			for(ShareBoardImgs sit : finds.getSimges()) {
				if(sit.getSimgname() != null) {
					sbi.getSimges().add(sit.getSimgname());
				}
			}
		}
		
		List<ShareComment> rootCom = shareComRepo.findByShareBoard_ShareBoardIdAndParentIsNull(shareboardid);
		sbi.setScomts(loadShareComts(rootCom));
		
		return sbi;
	}
	
	public ShareBoard countView(int shareboardid) {
		ShareBoard finds = shareRepo.findById(shareboardid).get();
		finds.setView(finds.getView()+1);
		return shareRepo.save(finds);
	}
	
	public List<ShareCommentDTO> loadShareComts(List<ShareComment> sharecomts) {
		List<ShareCommentDTO> scDTO = new ArrayList<>();
		for(ShareComment sc : sharecomts) {
			ShareCommentDTO scd = new ShareCommentDTO();
			scd.setShareCommentId(sc.getShareCommentId());
			scd.setContent(sc.getContent());
			scd.setCreateDate(sc.getCreateDate());
			scd.setParentId(sc.getParent() != null ? sc.getParent().getShareCommentId() : null);
			scd.setShareBoardid(sc.getShareBoard().getShareBoardId());
			scd.setUsername(sc.getMember() != null ? sc.getMember().getUsername() : "없는 회원입니다.");
			List<ShareCommentDTO> childDTOs = loadShareComts(sc.getScchildlist());
			scd.setScchildlist(childDTOs);
			scd.setDeleted(sc.isDeleted());
			scDTO.add(scd);
		}
		return scDTO;
	}
	
	public ShareBoard insertShareBoard(ShareBoard shareBoard, MultipartFile[] files) {
		
		if(shareBoard == null) {
			throw new IllegalArgumentException("ShareBoard cannot be null");
		}
		ShareBoard shareBoard1 = ShareBoard.builder()
				.type(shareBoard.getType())
				.title(shareBoard.getTitle())
				.content(shareBoard.getContent())
				.address(shareBoard.getAddress())
				.member(shareBoard.getMember()).build();
		ShareBoard shareBoard2 = shareRepo.save(shareBoard1);
		
		if(files != null && files.length >0) {
			String[] newFilename = Fileutil.uploadImg(files);
			
			for(int i = 0; i < files.length; i++) {
				ShareBoardImgs simg = ShareBoardImgs.builder()
						.simgoriname(files[i].getOriginalFilename())
						.simgname(newFilename[i])
						.shareboard(shareBoard2).build();
				shardImgRepo.save(simg);
			}
		}
		
		return shareBoard2;
	}
	
	public ShareBoard updateShareBoard(ShareBoard shareBoard, MultipartFile[] files) {
		
		ShareBoard finds = shareRepo.findById(shareBoard.getShareBoardId()).get();
		finds.setType(shareBoard.getType());
		finds.setTitle(shareBoard.getTitle());
		finds.setContent(shareBoard.getContent());
		finds.setAddress(shareBoard.getAddress());
		finds.setModifyDate(shareBoard.getCreateDate());
		ShareBoard finds1 = shareRepo.save(finds);
		
		if(files != null && files.length >0) {
			
			if(finds1.getSimges() != null) {
				for(ShareBoardImgs i : finds.getSimges()) {
					shardImgRepo.deleteById(i.getSimgid());
				}
			}
			String[] newFilename = Fileutil.uploadImg(files);
			
			for(int i = 0; i < files.length; i++) {
				ShareBoardImgs simg = ShareBoardImgs.builder()
						.simgoriname(files[i].getOriginalFilename())
						.simgname(newFilename[i])
						.shareboard(finds1).build();
				shardImgRepo.save(simg);
			}
		}
		return finds1;
	}
	
	public ShareBoard deleteShareBoard(int shareboardid) {
		ShareBoard finds = shareRepo.findById(shareboardid).get();
		shareRepo.delete(finds);
		return finds;
	}
}
