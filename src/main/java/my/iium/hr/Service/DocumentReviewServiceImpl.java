package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.DocumentReview;
import my.iium.hr.repository.DocumentReviewRepository;

@Service
public class DocumentReviewServiceImpl implements DocumentReviewService {

	@Autowired
	private DocumentReviewRepository documentReviewRepository;

	@Override
	public List<DocumentReview> getAllDocumentReview() {
		return documentReviewRepository.findAll();
	}

	@Override
	public void saveDocumentReview(DocumentReview documentReview) {
		this.documentReviewRepository.save(documentReview);
	}

	@Override
	public DocumentReview getDocumentReview(long code) {
		Optional<DocumentReview> optional = documentReviewRepository.findById(code);
		DocumentReview documentReview = null;
		if (optional.isPresent()) {
			documentReview = optional.get();
		} else {
			throw new RuntimeException(" DocumentReview not found for id :: " + code);
		}
		return documentReview;
	}

	@Override
	public void deleteDocumentReviewById(long code) {
		this.documentReviewRepository.deleteById(code);

	}

	@Override
	public Page<DocumentReview> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.documentReviewRepository.findAll(pageable);
	}

	@Override
	public List<DocumentReview> findByKeyword(String keyword) {
		return documentReviewRepository.findByKeyword(keyword);
	}
	
	@Override
	public List<DocumentReview> getDocumentReviewByDocID(Long id) {
		return documentReviewRepository.getDocumentReviewByDocID(id);
	}
}
