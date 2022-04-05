package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.FileSubTopic;
import my.iium.hr.repository.FileSubTopicRepository;

@Service
public class FileSubTopicServiceImpl implements FileSubTopicService {

	@Autowired
	private FileSubTopicRepository fileSubTopicRepository;

	@Override
	public List<FileSubTopic> getAllFileSubTopic() {
		return fileSubTopicRepository.findAll();
	}

	@Override
	public void saveSubTopic(FileSubTopic fileSubTopic) {
		this.fileSubTopicRepository.save(fileSubTopic);

	}

	@Override
	public FileSubTopic getSubTopic(long id) {
		Optional<FileSubTopic> optional = fileSubTopicRepository.findById(id);
		FileSubTopic fileSubTopic = null;
		if (optional.isPresent()) {
			fileSubTopic = optional.get();
		} else {
			throw new RuntimeException(" SubTopic not found for id :: " + id);
		}
		return fileSubTopic;
	}

	@Override
	public void deleteSubTopicById(long id) {
		this.fileSubTopicRepository.deleteById(id);

	}

	@Override
	public Page<FileSubTopic> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.fileSubTopicRepository.findAll(pageable);
	}

	@Override
	public List<FileSubTopic> findByKeyword(String keyword) {
		return fileSubTopicRepository.findByKeyword(keyword);
	}

}
