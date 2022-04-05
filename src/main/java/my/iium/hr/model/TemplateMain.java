package my.iium.hr.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TEMPLATE_MAIN")
public class TemplateMain {
	/*
	 * having _ in the code will mess up the sorting function so we need to give a
	 * proper java name for the attributes
	 */
	/* with the help of @Column so we can still read from the DB */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TM_ID")
	private long id;
	@Column(name = "TM_REF_CODE_ID")
	private String refCodeID;
	@Column(name = "TM_TITLE")
	private String title;
	@Column(name = "TM_CONTENT")
	private String content;
	@Column(name = "TM_CONTENT_SIGN")
	private String contentSign;
	@Column(name = "TM_CONTENT_CC")
	private String contentCC;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "TM_DATE_START")
	private Date dateStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	@Column(name = "TM_DATE_END")
	private Date dateEnd;
	@Column(name = "TM_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "TM_ENTER_DATE")
	private Date enterDate;
	@Column(name = "TM_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "TM_UPDATE_DATE")
	private Date updateDate;

	
	
	
	public TemplateMain() {

	}

	public TemplateMain(String refID, String title, String content, String contentSign, String contentCC,
			Date dateStart, Date dateEnd) {
		this.refCodeID = refID;
		this.title = title;
		this.content = content;
		this.contentSign = contentSign;
		this.contentCC = contentCC;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRefCodeID() {
		return refCodeID;
	}

	public void setRefCodeID(String refCodeID) {
		this.refCodeID = refCodeID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentSign() {
		return contentSign;
	}

	public void setContentSign(String contentSign) {
		this.contentSign = contentSign;
	}

	public String getContentCC() {
		return contentCC;
	}

	public void setContentCC(String contentCC) {
		this.contentCC = contentCC;
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
		return  content;
	}

}
