package pnu.edu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.ShareComment;

public interface ShareCommentRepository extends JpaRepository<ShareComment, Integer>{

	 List<ShareComment> findByShareBoard_ShareBoardIdAndParentIsNull(int shareboardid);
}
