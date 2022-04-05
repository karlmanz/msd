package my.iium.hr.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import my.iium.hr.model.OrganizationMain;

public interface OrganizationMainService {

	List<OrganizationMain> getAllOrganization();

	void saveOrganization(OrganizationMain organizationMain);

	OrganizationMain getOrganization(long id);

	void deleteOrganizationById(long id);

	Page<OrganizationMain> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	List<OrganizationMain> findByKeyword(String keyword); // search

}
