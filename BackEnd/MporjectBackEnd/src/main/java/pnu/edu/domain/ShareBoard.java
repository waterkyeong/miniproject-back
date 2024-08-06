package pnu.edu.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shareboard")
public class ShareBoard {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shareBoardId;
	private String type;
	private String title;
	private String content;
	private String img;
	
	@ManyToOne
	@JoinColumn(name = "member_username", referencedColumnName = "username")
	private Member member;
	
	private String latitude;
	private String longitude;
	@Column(insertable = false, columnDefinition = "number default 0")
	private int view;
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	@Column(columnDefinition = "timestamp default current_timestamp")
	private Date createDate = new Date();
}
