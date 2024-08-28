package pnu.edu.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor @AllArgsConstructor

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
	private Date modifyDate;
	private String username;
	@Builder.Default
	private List<String> fimges = new ArrayList<>();
	@Builder.Default
	private List<FreeCommentDTO> fcomts = new ArrayList<>();
}
