package my.iium.hr.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import my.iium.hr.model.DocumentReview;

@Repository
public interface DocumentReviewRepository extends JpaRepository<DocumentReview, Long> {
//searching here will require a joined table between review and the original one i think!
	// for the time being i will set it to
	@Query(value = "select * from DOCUMENT_REVIEW DR where DR.DR_CODE_DOCUMENT like %:keyword%", nativeQuery = true)
	List<DocumentReview> findByKeyword(@Param("keyword") String keyword);
	
	@Query(value = "select * from DOCUMENT_REVIEW DR", nativeQuery = true)
	List<DocumentReview> getAllDocumentReview();
	
	@Query(value = "select * from document_review DR where DR.DR_DOCUMENT_ID = :keyword", nativeQuery = true)
	List<DocumentReview> getDocumentReviewByDocID(@Param("keyword") Long ID);
}
