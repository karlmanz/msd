package my.iium.hr.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ROLE_MAIN")
public class Role {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "RM_ROLE_ID")
	private long id;
	
	//required change !
	@Column(name = "RM_ROLE_NAME")
	private String name;
	
	@Column(name = "RM_ROLE_DESCRIPTION")
	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "RM_DATE_START")
	private Date dateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "RM_DATE_END")
	private Date dateEnd;
	@Column(name = "RM_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "RM_ENTER_DATE")
	private Date enterDate;
	@Column(name = "RM_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "RM_UPDATE_DATE")
	private Date updateDate;

	
	public Role() {
		
	}
	public Role(String name, String description, Date dateStart, Date dateEnd) {
		this.name = name;
		this.description = description;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
    @Override
    public String toString() {
        return this.name;
    }
}
