package pnu.edu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.ShareBoard;

public interface ShareBoardRepository extends JpaRepository<ShareBoard, Integer> {
	Page<ShareBoard> findAll(Pageable pageable);
}
