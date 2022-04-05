package my.iium.hr.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import my.iium.hr.model.OrganizationMain;
import my.iium.hr.repository.OrganizationMainRepository;

@Service
public class OrganizationMainServiceImpl implements OrganizationMainService {

	@Autowired
	private OrganizationMainRepository organizationMainRepository;

	@Override
	public List<OrganizationMain> getAllOrganization() {
		return organizationMainRepository.findAll();
	}

	@Override
	public void saveOrganization(OrganizationMain organizationMain) {
		this.organizationMainRepository.save(organizationMain);

	}

	@Override
	public OrganizationMain getOrganization(long id) {
		Optional<OrganizationMain> optional = organizationMainRepository.findById(id);
		OrganizationMain organizationMain = null;
		if (optional.isPresent()) {
			organizationMain = optional.get();
		} else {
			throw new RuntimeException(" Organization not found for id :: " + id);
		}
		return organizationMain;
	}

	@Override
	public void deleteOrganizationById(long id) {
		this.organizationMainRepository.deleteById(id);

	}

	@Override
	public Page<OrganizationMain> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.organizationMainRepository.findAll(pageable);
	}

	@Override
	public List<OrganizationMain> findByKeyword(String keyword) {
		return organizationMainRepository.findByKeyword(keyword);
	}

}
