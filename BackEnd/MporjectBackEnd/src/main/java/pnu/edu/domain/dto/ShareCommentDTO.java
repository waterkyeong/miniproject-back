package pnu.edu.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShareCommentDTO {

	private int shareCommentId;
	private String content;
	private Date createDate;
	private boolean deleted;
	private Integer parentId;
	private List<ShareCommentDTO> scchildlist = new ArrayList<>();
	private int shareBoardid;
	private String username;
}
