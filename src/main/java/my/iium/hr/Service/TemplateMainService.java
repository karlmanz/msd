package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.TemplateMain;

public interface TemplateMainService {

	List<TemplateMain> getAllTemplateMain();

	void saveTemplate(TemplateMain templateMain);

	TemplateMain getTemplateById(long id);
	
	Long getTemplateCount();

	void deleteTemplateById(long id);

	Page<TemplateMain> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<TemplateMain> findByKeyword(String keyword); // search
}
