package pnu.edu.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pnu.edu.domain.ShareBoard;

@Getter @Setter @ToString
public class ShareBoardImgsDTO {

	private int simgid;
	private String simgname;
	private String simgoriname;
	private ShareBoard shareboard;
}
