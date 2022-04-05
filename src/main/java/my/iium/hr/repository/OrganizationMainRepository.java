package my.iium.hr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import my.iium.hr.model.OrganizationMain;

@Repository
public interface OrganizationMainRepository extends JpaRepository<OrganizationMain, Long> {

	@Query(value = "select * from ORGANIZATION_MAIN om where om.OM_TITLE like %:keyword%", nativeQuery = true)
	List<OrganizationMain> findByKeyword(@Param("keyword") String keyword);
}
