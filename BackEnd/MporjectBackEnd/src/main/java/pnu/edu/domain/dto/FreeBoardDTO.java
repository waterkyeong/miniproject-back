package pnu.edu.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FreeBoardDTO {
	private int freeBoardId;
	private String privateType;
	private String type;
	private String title;
	private String content;
	private int view;
	private Date createDate;
	private String username;
	private List<String> fimges = new ArrayList<>();
	private List<FreeCommentDTO> fcomts = new ArrayList<>();
}
