package pnu.edu.repository;

import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pnu.edu.domain.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer>{
	Page<FreeBoard> findAll(Pageable pageable);

	List<FreeBoard> findByOrderByCreateDateDesc(Limit of);

}
