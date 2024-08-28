package pnu.edu.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShareBoardDTO {
	private int shareBoardId;
	private String type;
	private String title;
	private String content;
	private String address;
	private int view;
	private Date createDate;
	private Date modifyDate;
	private String username;
	private List<String> simges = new ArrayList<>();
	private List<ShareCommentDTO> scomts = new ArrayList<>();
}
