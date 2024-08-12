package pnu.edu.domain.dto;

import java.util.Date;

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
	private int view;
	private Date createDate;
	private String username;
}
