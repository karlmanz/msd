package my.iium.hr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import my.iium.hr.model.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, String>{
	
	@Query(value= "select * from Notifications n where n.notify_to = :staffId", nativeQuery= true)
	List<Notifications> getNotificationsByUser(@Param("staffId") String staffId);

}
