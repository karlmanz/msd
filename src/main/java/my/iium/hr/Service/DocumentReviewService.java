package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.DocumentReview;

public interface DocumentReviewService {

	List<DocumentReview> getAllDocumentReview();

	void saveDocumentReview(DocumentReview documentReview);

	DocumentReview getDocumentReview(long id);

	void deleteDocumentReviewById(long id);

	Page<DocumentReview> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<DocumentReview> findByKeyword(String keyword); // search
	
	List<DocumentReview> getDocumentReviewByDocID(Long id); // search

}
