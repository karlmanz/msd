package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.FileRefNumber;
import my.iium.hr.repository.FileRefNumberRepository;

@Service
public class FileRefNumberServiceImpl implements FileRefNumberService {

	@Autowired
	private FileRefNumberRepository fileRefNumberRepository;

	@Override
	public List<FileRefNumber> getAllFileRefNumber() {

		return fileRefNumberRepository.findAll();
	}

	@Override
	public void saveRefNumber(FileRefNumber FileRefNumber) {
		this.fileRefNumberRepository.save(FileRefNumber);

	}

	@Override
	public FileRefNumber getRefNumber(long id) {
		Optional<FileRefNumber> optional = fileRefNumberRepository.findById(id);
		FileRefNumber fileRefNumber = null;
		if (optional.isPresent()) {
			fileRefNumber = optional.get();
		} else {
			throw new RuntimeException(" Reference Number not found for id :: " + id);
		}

		return fileRefNumber;
	}

	@Override
	public void deleteRefNumberById(long id) {
		this.fileRefNumberRepository.deleteById(id);

	}

	@Override
	public Page<FileRefNumber> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.fileRefNumberRepository.findAll(pageable);
	}

	// search
	@Override
	public List<FileRefNumber> findByKeyword(String keyword) {
		return fileRefNumberRepository.findByKeyword(keyword);
	}

	@Override
	public Long getFileRefNumCount() {
		
		return fileRefNumberRepository.count();
	}

}
