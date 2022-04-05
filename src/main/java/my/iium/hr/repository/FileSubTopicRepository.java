package my.iium.hr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import my.iium.hr.model.FileSubTopic;

@Repository
public interface FileSubTopicRepository extends JpaRepository<FileSubTopic, Long> {

	@Query(value = "select * from FILE_SUBTOPIC fs where fs.FS_TOPIC_REF_ID like %:keyword%", nativeQuery = true)
	List<FileSubTopic> findByKeyword(@Param("keyword") String keyword);

}
