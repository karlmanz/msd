package my.iium.hr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import my.iium.hr.model.FileRefNumber;

@Repository
public interface FileRefNumberRepository extends JpaRepository<FileRefNumber, Long> {

	@Query(value = "select * from FILE_REFERENCE_NUMBER frn where frn.FRN_REF_CODE_ID like %:keyword%", nativeQuery = true)
	List<FileRefNumber> findByKeyword(@Param("keyword") String keyword);
}
