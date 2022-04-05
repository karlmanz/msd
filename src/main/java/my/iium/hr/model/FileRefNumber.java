package my.iium.hr.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "FILE_REFERENCE_NUMBER")
public class FileRefNumber implements Serializable {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "FRN_ID")
	private long id;
	// @Column(name = "FRN_TOPIC_REF_ID") //fk
	// private int topicRefID;
	// @Column(name = "FRN_ORG_ID") //fk
	// private String orgID;
	// @Column(name = "FRN_SUBTOPIC_ID")// foreign key
	// private String subTopicID;
	@Column(name = "FRN_REF_CODE_ID")
	private String refCodeID;
	@Column(name = "FRN_CLASS")
	private String topicClass;
	@Column(name = "FRN_HURIS_DEPT")
	private String hurisDept;
	@Column(name = "FRN_DEPARTMENT_NO", nullable = true)
	private int departmentNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FRN_DATE_START")
	private Date dateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FRN_DATE_END")
	private Date dateEnd;
	@Column(name = "FRN_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "FRN_ENTER_DATE")
	private Date enterDate;
	@Column(name = "FRN_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "FRN_UPDATE_DATE")
	private Date updateDate;

	// file topic FK
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FRN_TOPIC_REF_ID")
	private FileTopic fileTopic;
	// file subtopic FK
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FRN_SUBTOPIC_ID")
	private FileSubTopic fileSubTopic;
	// file ORG FK
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FRN_ORG_ID")
	private OrganizationMain orgMain;

	// @OneToOne(mappedBy = "fileRefNumber")
	// private Document document;

	public FileRefNumber() {

	}

	public FileRefNumber(String refCodeID, int topicRefID, String topicClass, int subNo1, int subNo2, int subNo3,
			String org, String orgCode, int departmentNo, Date dateStart, Date dateEnd) {
		this.topicClass = topicClass;
		this.departmentNo = departmentNo;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopicClass() {
		return topicClass;
	}

	public void setTopicClass(String topicClass) {
		this.topicClass = topicClass;
	}

	public int getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
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

	public String getHurisDept() {
		return hurisDept;
	}

	public void setHurisDept(String hurisDept) {
		this.hurisDept = hurisDept;
	}

	public FileTopic getFileTopic() {
		return fileTopic;
	}

	public void setFileTopic(FileTopic fileTopic) {
		this.fileTopic = fileTopic;
	}

	public FileSubTopic getFileSubTopic() {
		return fileSubTopic;
	}

	public void setFileSubTopic(FileSubTopic fileSubTopic) {
		this.fileSubTopic = fileSubTopic;
	}

	public OrganizationMain getOrgMain() {
		return orgMain;
	}

	public void setOrgMain(OrganizationMain orgMain) {
		this.orgMain = orgMain;
	}

	public String getRefCodeID() {
		return refCodeID;
	}

	public void setRefCodeID(String refCodeID) {
		this.refCodeID = refCodeID;
	}

	/*
	 * public Document getDocument() { return document; }
	 * 
	 * public void setDocument(Document document) { this.document = document; }
	 */

	@Override
	public String toString() {
		return refCodeID;
	}

}
