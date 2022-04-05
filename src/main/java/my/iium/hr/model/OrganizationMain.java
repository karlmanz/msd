package my.iium.hr.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ORGANIZATION_MAIN")
public class OrganizationMain {

	// haven't created id in the table
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "OM_ID")
	private long id;
	@Column(name = "OM_ORG_ACRONYM")
	private String acronym;
	@Column(name = "OM_ORG_TITLE")
	private String title;
	@Column(name = "OM_ORG_STATUS")
	private String status;
	@Column(name = "OM_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "OM_ENTER_DATE")
	private Date enterDate;
	@Column(name = "OM_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "OM_UPDATE_DATE")
	private Date updateDate;

	
    //@OneToOne(mappedBy = "orgMain")
    //private FileRefNumber fileRefNumber;
	
	public OrganizationMain() {

	}

	public OrganizationMain(String orgCode, String acronym, String title, String status, Date dateStart, Date dateEnd) {
		this.acronym = acronym;
		this.title = title;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	/*public FileRefNumber getFileRefNumber() {
		return fileRefNumber;
	}

	public void setFileRefNumber(FileRefNumber fileRefNumber) {
		this.fileRefNumber = fileRefNumber;
	}*/

	@Override
	public String toString() {
		return title;
	}

}
