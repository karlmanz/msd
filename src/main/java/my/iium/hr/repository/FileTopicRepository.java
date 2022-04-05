package my.iium.hr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import my.iium.hr.model.FileTopic;

@Repository
public interface FileTopicRepository extends JpaRepository<FileTopic, Long> {
	@Query(value = "select * from FILE_TOPIC ft where ft.FT_TITLE like %:keyword%", nativeQuery = true)
	List<FileTopic> findByKeyword(@Param("keyword") String keyword);

	//FileTopic findByRefID(String refID);
}
