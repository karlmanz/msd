package my.iium.hr.security;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import my.iium.hr.model.Role;
import my.iium.hr.repository.RoleRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserRepository userRepository;
	@Autowired 
	private RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("User Name : " + username);
		MyUser user = userRepository.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not present"));
		return new MyUserDetails(user);
	}
	
	public List<MyUser> getAllUser() {
		return userRepository.findAll();
	}
	public void createUser(UserDetails user) {
		this.userRepository.save((MyUser) user);
	}
	//roles impl
    public void registerDefaultUser(MyUser user) {
        Role roleUser = roleRepo.findByName("New");
        user.addRole(roleUser);
 
        userRepository.save(user);
    }
    
	public MyUser getUser(String username) {
		Optional<MyUser> optional = userRepository.findUserByUsername(username);
		MyUser myUser = null;
		if (optional.isPresent()) {
			myUser = optional.get();
		} else {
			throw new RuntimeException(" user not found for id :: " + username);
		}
		return myUser;
	}
	
	public void deleteUserByUsername(String username) {
		this.userRepository.deleteByUsername(username);

	}
    
    //roles impl
	public Page<MyUser> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable);
	}
    
    
	public List<MyUser> listAll() {
		return userRepository.findAll();
	}
	public MyUser get(String username) {
	    return userRepository.findUserByUsername(username).get();
	}
	 
	public List<Role> listRoles() {
	    return roleRepo.findAll();
	}

	public List<MyUser> findByKeyword(String keyword) {
		return userRepository.findByKeyword(keyword);
	}
	public void save(MyUser user) {
	    userRepository.save(user);
	}

}
