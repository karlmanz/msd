package my.iium.hr.security;

import java.util.List;

public interface MyUserService {

	List<MyUser> getAllSupervisors();
	
	Long getUserCount();

}
