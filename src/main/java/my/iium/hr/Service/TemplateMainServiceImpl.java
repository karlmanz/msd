package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.TemplateMain;
import my.iium.hr.repository.TemplateMainRepository;

@Service
public class TemplateMainServiceImpl implements TemplateMainService {

	@Autowired
	private TemplateMainRepository templateMainRepository;

	@Override
	public List<TemplateMain> getAllTemplateMain() {

		return templateMainRepository.findAll();
	}

	@Override
	public void saveTemplate(TemplateMain templateMain) {
		// save template to database
		this.templateMainRepository.save(templateMain);

	}

	@Override
	public TemplateMain getTemplateById(long id) {
		Optional<TemplateMain> optional = templateMainRepository.findById(id);
		TemplateMain templateMain = null;
		if (optional.isPresent()) {
			templateMain = optional.get();
		} else {
			throw new RuntimeException(" Template not found for id :: " + id);
		}
		return templateMain;
	}

	@Override
	public void deleteTemplateById(long id) {
		this.templateMainRepository.deleteById(id);
	}

	@Override
	public Page<TemplateMain> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.templateMainRepository.findAll(pageable);
	}

	// search
	@Override
	public List<TemplateMain> findByKeyword(String keyword) {
		return templateMainRepository.findByKeyword(keyword);
	}

	@Override
	public Long getTemplateCount() {
		return templateMainRepository.count();
	}

}
