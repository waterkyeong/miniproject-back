package pnu.edu.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShareBoardDTO {
	private int shareBoardId;
	private String type;
	private String title;
	private String content;
	private int view;
	private Date createDate;
	private String username;
}
