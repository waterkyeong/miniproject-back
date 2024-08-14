package pnu.edu.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pnu.edu.domain.FreeBoard;

@Getter @Setter @ToString
public class FreeBoardImgsDTO {

	private int fimgid;
	private String fimgname;
	private String fimgoriname;
	private FreeBoard freeboard;
	
}