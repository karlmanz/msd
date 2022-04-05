package my.iium.hr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import my.iium.hr.model.TemplateMain;

@Repository
public interface TemplateMainRepository extends JpaRepository<TemplateMain, Long> {

	@Query(value = "select * from TEMPLATE_MAIN tm where tm.TM_TITLE like %:keyword%", nativeQuery = true)
	List<TemplateMain> findByKeyword(@Param("keyword") String keyword);

}
