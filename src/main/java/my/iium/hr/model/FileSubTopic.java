package my.iium.hr.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "FILE_SUBTOPIC")
public class FileSubTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FS_ID")
	private long id;
	//@Column(name = "FS_TOPIC_REF_ID") //FK
	//private int topicRefID;
	@Column(name = "FS_SUB_NO_1")
	private Integer subNo1;
	@Column(name = "FS_SUB_TOPIC_1")
	private String subTopic1;
	@Column(name = "FS_SUB_NO_2")
	private Integer subNo2;
	@Column(name = "FS_SUB_TOPIC_2")
	private String subTopic2;
	@Column(name = "FS_SUB_NO_3")
	private Integer subNo3;
	@Column(name = "FS_SUB_TOPIC_3")
	private String subTopic3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FS_DATE_START")
	private Date dateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FS_DATE_END")
	private Date dateEnd;
	@Column(name = "FS_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "FS_ENTER_DATE")
	private Date enterDate;
	@Column(name = "FS_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "FS_UPDATE_DATE")
	private Date updateDate;

	//file topic FK
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FS_TOPIC_REF_ID")
    private FileTopic fileTopic;
    
    
    //@OneToOne(mappedBy = "fileSubTopic")
    //private FileRefNumber fileRefNumber;
    
	public FileSubTopic() {

	}

	public FileSubTopic(int topicRefID, int subNo1, String subTopic1, int subNo2, String subTopic2, int subNo3,
			String subTopic3, Date dateStart, Date dateEnd) {
		this.subNo1 = subNo1;
		this.subTopic1 = subTopic2;
		this.subNo1 = subNo2;
		this.subTopic1 = subTopic3;
		this.subNo1 = subNo3;
		this.subTopic1 = subTopic1;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id= id;
	}

	public Integer getSubNo1() {
		return subNo1;
	}

	public void setSubNo1(Integer subNo1) {
		this.subNo1 = subNo1;
	}

	public String getSubTopic1() {
		return subTopic1;
	}

	public void setSubTopic1(String subTopic1) {
		this.subTopic1 = subTopic1;
	}

	public Integer getSubNo2() {
		return subNo2;
	}

	public void setSubNo2(Integer subNo2) {
		this.subNo2 = subNo2;
	}

	public String getSubTopic2() {
		return subTopic2;
	}

	public void setSubTopic2(String subTopic2) {
		this.subTopic2 = subTopic2;
	}

	public Integer getSubNo3() {
		return subNo3;
	}

	public void setSubNo3(Integer subNo3) {
		this.subNo3 = subNo3;
	}

	public String getSubTopic3() {
		return subTopic3;
	}

	public void setSubTopic3(String subTopic3) {
		this.subTopic3 = subTopic3;
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

	public FileTopic getFileTopic() {
		return fileTopic;
	}

	public void setFileTopic(FileTopic fileTopic) {
		this.fileTopic = fileTopic;
	}

	/*public FileRefNumber getFileRefNumber() {
		return fileRefNumber;
	}

	public void setFileRefNumber(FileRefNumber fileRefNumber) {
		this.fileRefNumber = fileRefNumber;
	}*/

}
