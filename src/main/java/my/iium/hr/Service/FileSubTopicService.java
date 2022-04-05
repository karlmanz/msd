package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.FileSubTopic;

public interface FileSubTopicService {

	List<FileSubTopic> getAllFileSubTopic();

	void saveSubTopic(FileSubTopic fileSubTopic);

	FileSubTopic getSubTopic(long id);

	void deleteSubTopicById(long id);

	Page<FileSubTopic> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<FileSubTopic> findByKeyword(String keyword); // search

}
