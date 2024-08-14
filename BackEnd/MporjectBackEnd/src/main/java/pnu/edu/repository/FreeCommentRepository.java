package pnu.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.FreeComment;

public interface FreeCommentRepository extends JpaRepository<FreeComment, Integer>{

	 List<FreeComment> findByFreeBoard_FreeBoardIdAndParentIsNull(int freeboardid);

}
