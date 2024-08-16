package pnu.edu.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString 
public class FreeCommentDTO {
	
	private int freeCommentId;
	private String content;
	private Date createDate;
	private Integer parentId;
	private List<FreeCommentDTO> fcchildlist = new ArrayList<>();
	private int freeBoardId;
	private String username;
	private boolean deleted;
}
