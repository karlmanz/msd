package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.FileTopic;

public interface FileTopicService {

	List<FileTopic> getAllFileTopic();

	void saveTopic(FileTopic fileTopic);

	FileTopic getTopic(long id);

	void deleteTopicById(long id);

	Page<FileTopic> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<FileTopic> findByKeyword(String keyword); // search

}
