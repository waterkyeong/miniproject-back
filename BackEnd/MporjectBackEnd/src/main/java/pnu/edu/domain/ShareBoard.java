package pnu.edu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
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
	@Column(length = 5, nullable = false)
	private String type;
	@Column(length = 50, nullable = false)
	private String title;
	@Column(length = 5000, nullable = false)
	private String content;
	@Column(length = 30)
	private String address;
	@Builder.Default
	@Column(columnDefinition = "int default 0")
	private int view = 0;
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	@Column(columnDefinition = "timestamp default current_timestamp")
	private Date createDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "timestamp default current_timestamp")
	private Date modifyDate;
	
	@Builder.Default
	@OneToMany(mappedBy = "shareboard", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<ShareBoardImgs> simges = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(mappedBy = "shareBoard",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ShareComment> scomes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "username")
	Member member;
}
