package pnu.edu.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
public class Member {

	@Id
	@Column(length=16)
	private String username;
	@Column(length=15, nullable = false)
	private String password;
	@Column(length=10, nullable = false)
	private String alias;
	@Column(length=20, nullable = false)
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled;
}
