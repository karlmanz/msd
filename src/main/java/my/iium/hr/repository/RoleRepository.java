package my.iium.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import my.iium.hr.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
	//role implement
	Role findByName(String name);

}
