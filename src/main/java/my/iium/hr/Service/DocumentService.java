
package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.Document;

public interface DocumentService {

	List<Document> getAllDocument();

	void saveDocument(Document document);

	Document getDocument(long id);
	
	Long getDocumentCount();

	void deleteDocumentById(long id);

	Page<Document> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String status);

	Page<Document> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<Document> findByKeyword(String keyword, String status); // search

	List<Document> findByKeyword(String keyword); // search

	List<Document> findByStatus(String status);

	//void assignMainTopic(Document document, String refID);

}
