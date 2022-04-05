package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.Document;
import my.iium.hr.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;
	
	
	@Override
	public List<Document> getAllDocument() {
		// return documentRepository.findByStatus("Draft");
		return documentRepository.findAll();
	}

	@Override
	public void saveDocument(Document document) {
		this.documentRepository.save(document);
	}

	@Override
	public Document getDocument(long id) {
		Optional<Document> optional = documentRepository.findById(id);
		Document document = null;
		if (optional.isPresent()) {
			document = optional.get();
		} else {
			throw new RuntimeException(" Document not found for id :: " + id);
		}
		return document;
	}
	//testing for main topic impl
	/*
	@Override
    public void assignMainTopic(Document document,String refID) {
        FileTopic fileTopicDocument = fileTopicRepo.findByRefID(refID);
        document.addFileTopic(fileTopicDocument);
 
        documentRepository.save(document);
    }
*/

	@Override
	public void deleteDocumentById(long id) {
		this.documentRepository.deleteById(id);

	}

	@Override
	public Page<Document> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,
			String status) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		// need different retrn for diff status.
		// Draft
		if (status == "Draft")
			return this.documentRepository.findAllByDraft(pageable); // edited
		// submitted
		else if (status == "Submitted")
			return this.documentRepository.findAllBySubmitted(pageable);
		// Recommended
		else if (status == "Recommended")
			return this.documentRepository.findAllByRecommended(pageable);
		// approved
		else if (status == "Approved")
			return this.documentRepository.findAllByApproved(pageable);
		// recycle bin
		else
			return this.documentRepository.findAllByRecycleBin(pageable);
	}

	@Override
	public Page<Document> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.documentRepository.findAll(pageable);
	}

	// @Override
	public List<Document> findByKeyword(String keyword, String status) {
		return documentRepository.findByKeyword(keyword, status);
	}

	// @Override
	public List<Document> findByKeyword(String keyword) {
		return documentRepository.findByKeyword(keyword);
	}

	@Override
	public List<Document> findByStatus(String status) {
		return documentRepository.findByStatus(status);
	}

	@Override
	public Long getDocumentCount() {
		
		return documentRepository.count();
	}

}
