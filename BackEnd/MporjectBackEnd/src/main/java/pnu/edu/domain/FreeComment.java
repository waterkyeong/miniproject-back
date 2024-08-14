package pnu.edu.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "freecomment")
public class FreeComment {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int freeCommentId;
	@Lob
	@Column(nullable = false)
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	@Builder.Default
	@Column(columnDefinition = "timestamp default current_timestamp")
	private Date createDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private FreeComment parent;
	
	@Builder.Default
	@OneToMany(mappedBy = "parent", orphanRemoval = false)
	private List<FreeComment> fcchildlist = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "freeBoardId")
	FreeBoard freeBoard;

	@ManyToOne
	@JoinColumn(name = "username")
	Member member;
}
