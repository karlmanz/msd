package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.FileRefNumber;

public interface FileRefNumberService {

	List<FileRefNumber> getAllFileRefNumber();

	void saveRefNumber(FileRefNumber FileRefNumber);

	FileRefNumber getRefNumber(long id);
	
	Long getFileRefNumCount();

	void deleteRefNumberById(long id);

	Page<FileRefNumber> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<FileRefNumber> findByKeyword(String keyword); // search

}