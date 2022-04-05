package my.iium.hr.security;

import java.util.Collection;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import my.iium.hr.model.Role;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class MyUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UR_STAFF_USERNAME")
	private String username;
	@Column(name = "UR_STAFF_ID")
	private String userID;
	@Column(name = "UR_STAFF_NAME")
	private String fullname;
	@Column(name = "UR_HURIS_DEPT")
	private String hurisDept;
	@Column(name = "UR_EMAIL")
	private String useremail;
	@Column(name = "UR_PASSWORD")
	private String password;
	@Column(name = "UR_ACCOUNT_NON_LOCKED")
	private boolean accountNonLocked;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "UR_DATE_START")
	private Date dateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	@Column(name = "UR_DATE_END")
	private Date dateEnd;
	@Column(name = "UR_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "UR_ENTER_DATE")
	private Date enterDate;
	@Column(name = "UR_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "UR_UPDATE_DATE")
	private Date updateDate;

	// ROLES IMPLEMENTATION
	//original method the id was a of Long type
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		this.roles.add(role);
	}
	//end of implementation

	public MyUser() {
	}

	public MyUser(String username, String password, String fullname, String useremail, boolean accountNonLocked) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.useremail = useremail;
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null; // List.of(() -> "read");
	}
	/*
	 * static <E> List<E> of(E e1) { return new ImmutableCollections.List12<>(e1); }
	 */

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getHurisDept() {
		return hurisDept;
	}

	public void setHurisDept(String hurisDept) {
		this.hurisDept = hurisDept;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getEnterBy() {
		return enterBy;
	}

	public void setEnterBy(String enterBy) {
		this.enterBy = enterBy;
	}

	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


}
