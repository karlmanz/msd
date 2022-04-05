package my.iium.hr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import my.iium.hr.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
	// with status
	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_TITLE like %:keyword% and DM.DM_STATUS = :status", nativeQuery = true)
	List<Document> findByKeyword(@Param("keyword") String keyword, @Param("status") String status);

	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_TITLE like %:keyword%", nativeQuery = true)
	List<Document> findByKeyword(@Param("keyword") String keyword);

	// @Query(value="select * from DOCUMENT_MAIN d where d.DM_STATUS = 'Draft'",
	// nativeQuery= true)
	List<Document> findByStatus(String status);
	
	// Draft
	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_STATUS = 'Draft'", nativeQuery = true)
	Page<Document> findAllByDraft(Pageable page);

	// Submitted
	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_STATUS = 'Submitted'", nativeQuery = true)
	Page<Document> findAllBySubmitted(Pageable page);

	// Recommended
	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_STATUS = 'Recommended'", nativeQuery = true)
	Page<Document> findAllByRecommended(Pageable page);

	// approved
	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_STATUS = 'Approved'", nativeQuery = true)
	Page<Document> findAllByApproved(Pageable page);

	// recycle bin
	@Query(value = "select * from DOCUMENT_MAIN DM where DM.DM_STATUS = 'RecycleBin'", nativeQuery = true)
	Page<Document> findAllByRecycleBin(Pageable page);
	
	

}
