package my.iium.hr.security;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String> {
	

	Optional<MyUser> findUserByUsername(String username);

	Long deleteByUsername(String username);

	@Query(value = "select * from USERS U where U.USERNAME like %:keyword%", nativeQuery = true)
	List<MyUser> findByKeyword(@Param("keyword")String keyword);

	@Query(value = "select * from users u", nativeQuery = true)
	List<MyUser> getAllUsers();
}