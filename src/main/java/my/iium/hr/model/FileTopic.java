package my.iium.hr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "FILE_TOPIC")
public class FileTopic implements Serializable {

	/*
	 * having _ in the code will mess up the sorting function so we need to give a
	 * proper java name for the attributes
	 */
	/* with the help of @Column so we can still read from the DB */

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FT_ID")
	@Id
	private long id;
	@Column(name = "FT_TOPIC_REF_ID", nullable = false)
	private int topicRefID;
	@Column(name = "FT_TITLE")
	private String title;
	@Column(name = "FT_CLASS")
	private String topicClass;
	@Column(name = "FT_VOLUME_NO")
	private int volumeNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FT_DATE_START")
	private Date dateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FT_DATE_END")
	private Date dateEnd;
	@Column(name = "FT_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "FT_ENTER_DATE")
	private Date enterDate;
	@Column(name = "FT_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "FT_UPDATE_DATE")
	private Date updateDate;

    //@OneToOne(mappedBy = "fileTopic")
    //private FileRefNumber fileRefNumber;
    //@OneToOne(mappedBy = "fileTopic")
    //private FileSubTopic fileSubTopic;

	public FileTopic() {

	}

	public FileTopic(int topicRefID, String title, String topicClass, int volumeNo, Date dateStart, Date dateEnd) {
		this.topicRefID = topicRefID;
		this.title = title;
		this.topicClass = topicClass;
		this.volumeNo = volumeNo;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTopicRefID() {
		return topicRefID;
	}

	public void setTopicRefID(int topicRefID) {
		this.topicRefID = topicRefID;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopicClass() {
		return topicClass;
	}

	public void setTopicClass(String topicClass) {
		this.topicClass = topicClass;
	}

	public int getVolumeNo() {
		return volumeNo;
	}

	public void setVolumeNo(int volumeNo) {
		this.volumeNo = volumeNo;
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

	/*public FileRefNumber getFileRefNumber() {
		return fileRefNumber;
	}

	public void setFileRefNumber(FileRefNumber fileRefNumber) {
		this.fileRefNumber = fileRefNumber;
	}

	public FileSubTopic getFileSubTopic() {
		return fileSubTopic;
	}

	public void setFileSubTopic(FileSubTopic fileSubTopic) {
		this.fileSubTopic = fileSubTopic;
	}*/

	@Override
	public String toString() {
		return this.title;
	}

}
