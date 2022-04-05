package my.iium.hr.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements MyUserService{

	@Autowired
	private MyUserRepository userRepository;
	
	
	@Override
	public List<MyUser> getAllSupervisors() {
		
		return userRepository.getAllUsers();
	}


	@Override
	public Long getUserCount() {
		return userRepository.count();
	}

}
