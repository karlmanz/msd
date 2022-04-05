package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.FileTopic;
import my.iium.hr.repository.FileTopicRepository;

@Service
public class FileTopicServiceImpl implements FileTopicService {

	@Autowired
	private FileTopicRepository fileTopicRepository;

	@Override
	public List<FileTopic> getAllFileTopic() {
		return fileTopicRepository.findAll();
	}

	@Override
	public void saveTopic(FileTopic fileTopic) {
		this.fileTopicRepository.save(fileTopic);

	}

	@Override
	public FileTopic getTopic(long id) {
		Optional<FileTopic> optional = fileTopicRepository.findById(id);
		FileTopic fileTopic = null;
		if (optional.isPresent()) {
			fileTopic = optional.get();
		} else {
			throw new RuntimeException(" Topic not found for id :: " + id);
		}
		return fileTopic;
	}

	@Override
	public void deleteTopicById(long id) {
		this.fileTopicRepository.deleteById(id);

	}

	@Override
	public Page<FileTopic> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.fileTopicRepository.findAll(pageable);
	}

	@Override
	public List<FileTopic> findByKeyword(String keyword) {
		return fileTopicRepository.findByKeyword(keyword);
	}

}
