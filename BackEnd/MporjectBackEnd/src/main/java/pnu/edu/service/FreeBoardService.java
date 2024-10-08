package pnu.edu.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.FreeBoard;
import pnu.edu.domain.FreeBoardImgs;
import pnu.edu.domain.FreeComment;
import pnu.edu.domain.dto.FreeBoardDTO;
import pnu.edu.domain.dto.FreeCommentDTO;
import pnu.edu.repository.FreeBoardImgsRepository;
import pnu.edu.repository.FreeBoardRepository;
import pnu.edu.repository.FreeCommentRepository;
import pnu.edu.util.Fileutil;

@Service
@RequiredArgsConstructor
public class FreeBoardService {

	private final FreeBoardRepository freebRepo;
	private final FreeBoardImgsRepository freebImgRepo;
	private final FreeCommentRepository freeComRepo;

//	public List<FreeBoardDTO> getFreeBoard(int page, int size) {
//		Pageable pageable = PageRequest.of(page, size);
//
//		Page<FreeBoard> list = freebRepo.findAll(pageable);
//
//		List<FreeBoardDTO> ret = new ArrayList<>(); 
//		for (FreeBoard fb : list) {
//
//			FreeBoardDTO t = new FreeBoardDTO();
//			t.setFreeBoardId(fb.getFreeBoardId());
//			t.setPrivateType(fb.getPrivateType());
//			t.setType(fb.getType());
//			t.setTitle(fb.getTitle());
//			t.setView(fb.getView());
//			t.setCreateDate(fb.getCreateDate());
//			t.setUsername(fb.getMember().getUsername());
//			ret.add(t);
//		}
//		return ret;
//	}
	
	public List<FreeBoardDTO> getFreeBoard() {
		List<FreeBoard> flist = freebRepo.findAll();
		List<FreeBoardDTO> list = new ArrayList<>();
		for(FreeBoard fb : flist) {
			FreeBoardDTO t = new FreeBoardDTO();
			t.setFreeBoardId(fb.getFreeBoardId());
			t.setPrivateType(fb.getPrivateType());
			t.setType(fb.getType());
			t.setTitle(fb.getTitle());
			t.setView(fb.getView());
			t.setCreateDate(fb.getCreateDate());
			t.setUsername(fb.getMember().getUsername());
			list.add(t);
		}
		return list; 
	}
	

	public FreeBoardDTO getFreeBoard(int freeboardid) {
		
		countView(freeboardid);

		FreeBoard findfb = freebRepo.findById(freeboardid).get();

		FreeBoardDTO fbi = new FreeBoardDTO();

		fbi.setFreeBoardId(findfb.getFreeBoardId());
		fbi.setPrivateType(findfb.getPrivateType());
		fbi.setType(findfb.getType());
		fbi.setTitle(findfb.getTitle());
		fbi.setContent(findfb.getContent());
		fbi.setView(findfb.getView());
		fbi.setCreateDate(findfb.getCreateDate());
		if (findfb.getFimges() != null) {
	        for (FreeBoardImgs fit : findfb.getFimges()) {
	            if (fit.getFimgname() != null) {
	                fbi.getFimges().add(fit.getFimgname());
	            }
	        }
	    }
		fbi.setUsername(findfb.getMember().getUsername());
		
		List<FreeComment> rootCom = freeComRepo.findByFreeBoard_FreeBoardIdAndParentIsNull(freeboardid);
		fbi.setFcomts(loadFreeComts(rootCom));
		
		return fbi;
	}

	public FreeBoard countView(int freeboardid) {
		FreeBoard findf = freebRepo.findById(freeboardid).get();
		findf.setView(findf.getView()+1);
		return freebRepo.save(findf);
	}
	
	public List<FreeCommentDTO> loadFreeComts(List<FreeComment> freecomts) {
		List<FreeCommentDTO> fcDTO = new ArrayList<>();
		for(FreeComment fc : freecomts) {
			FreeCommentDTO fcd = new FreeCommentDTO();
			fcd.setFreeCommentId(fc.getFreeCommentId());
			fcd.setContent(fc.getContent());
			fcd.setCreateDate(fc.getCreateDate());
			fcd.setParentId(fc.getParent()!= null ? fc.getParent().getFreeCommentId() : null);
			fcd.setFreeBoardId(fc.getFreeBoard().getFreeBoardId()); 
			fcd.setUsername(fc.getMember() != null ? fc.getMember().getUsername() : "없는 회원입니다.");
			List<FreeCommentDTO> childDTOs = loadFreeComts(fc.getFcchildlist());
	        fcd.setFcchildlist(childDTOs);
	        fcd.setDeleted(fc.getDeleted());
			fcDTO.add(fcd);
		}
		return fcDTO;
	}

	public FreeBoard insertFreeBoard(FreeBoard freeBoard, MultipartFile[] files) throws IllegalStateException, IOException {
		
		if(freeBoard == null) {
			throw new IllegalArgumentException("freeBoard cannot be null");
		}
		//게시판 DB저장
		FreeBoard freeBoard1 = FreeBoard.builder()
				.privateType(freeBoard.getPrivateType())
				.type(freeBoard.getType())
				.title(freeBoard.getTitle())
				.content(freeBoard.getContent())
				.member(freeBoard.getMember()).build();
		FreeBoard freeBoard2 = freebRepo.save(freeBoard1);
		if(files != null && files.length > 0) {
			//파일저장
			String [] newFilename = Fileutil.uploadImg(files);

			//이미지 정보저장(객체만들어서)
			for(int i = 0 ; i < files.length ; i++) {			
				FreeBoardImgs fimg = FreeBoardImgs.builder()
						.fimgoriname(files[i].getOriginalFilename())
						.fimgname(newFilename[i])
						.freeboard(freeBoard2)
						.build();
				freebImgRepo.save(fimg);
			}
		}
		return freeBoard2;
	}


	public FreeBoard updateFreeBoard(FreeBoard freeBoard, MultipartFile[] files) {

		FreeBoard findf = freebRepo.findById(freeBoard.getFreeBoardId()).get();
		findf.setPrivateType(freeBoard.getPrivateType());
		findf.setType(freeBoard.getType());
		findf.setTitle(freeBoard.getTitle());
		findf.setContent(freeBoard.getContent());
		findf.setModifyDate(freeBoard.getCreateDate());
		FreeBoard findf1 =  freebRepo.save(findf);

		if(findf.getFimges() != null && files == null) {
			for(FreeBoardImgs i : findf.getFimges()) {
				freebImgRepo.deleteById(i.getFimgid());
			}
		}
		if(files != null && files.length > 0) {
			//새로운 파일저장
			String [] newFilename = Fileutil.uploadImg(files);

			//이미지 정보저장(객체만들어서)
			for(int i = 0 ; i < files.length ; i++) {			
				FreeBoardImgs img = FreeBoardImgs.builder()
						.fimgoriname(files[i].getOriginalFilename())
						.fimgname(newFilename[i])
						.freeboard(findf1)
						.build();
				freebImgRepo.save(img);
				findf1.getFimges().add(img);
//				if( findf.getFimges() != null) {
//					// 기존 저장된 파일 삭제
//					for(FreeBoardImgs i : findf.getFimges()) {
//						freebImgRepo.deleteById(i.getFimgid());
//					}}
					
					
				}
			
		}
		return findf1;
	}

	public FreeBoard deleteFreeBoard(int freeboardid) {
		FreeBoard findf = freebRepo.findById(freeboardid).get();
		freebRepo.delete(findf);
		return findf;
	}
}
