package pnu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pnu.edu.domain.FreeBoard;
import pnu.edu.domain.ShareBoard;
import pnu.edu.domain.dto.FreeBoardDTO;
import pnu.edu.domain.dto.ShareBoardDTO;
import pnu.edu.repository.FreeBoardRepository;
import pnu.edu.repository.ShareBoardRepository;

@Service
@RequiredArgsConstructor
public class MainPageService {

	private final FreeBoardRepository fbRepo;
	private final ShareBoardRepository sbRepo;
	
	public List<FreeBoardDTO> getLatestfiveFB() {
		
		List<FreeBoard> flist = fbRepo.findByOrderByCreateDateDesc(Limit.of(6));
		List<FreeBoardDTO> fbdlist = new ArrayList<>();
		for(FreeBoard fb : flist) {
			FreeBoardDTO fd = new FreeBoardDTO();
			fd.setFreeBoardId(fb.getFreeBoardId());
			fd.setPrivateType(fb.getPrivateType());
			fd.setType(fb.getType());
			fd.setTitle(fb.getTitle());
			fd.setCreateDate(fb.getCreateDate());
			fbdlist.add(fd);
		}
		return fbdlist;
	}

	public List<ShareBoardDTO> getLatestfiveSB() {
		
		List<ShareBoard> slist = sbRepo.findByOrderByCreateDateDesc(Limit.of(6));
		List<ShareBoardDTO> sbdlist = new ArrayList<>();
		for(ShareBoard sb : slist) {
			ShareBoardDTO sd = new ShareBoardDTO();
			sd.setShareBoardId(sb.getShareBoardId());
			sd.setType(sb.getType());
			sd.setTitle(sb.getTitle());
			sd.setCreateDate(sb.getCreateDate());
			sbdlist.add(sd);
		}
		return sbdlist;
	}
}
