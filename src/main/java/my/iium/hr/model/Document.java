package my.iium.hr.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DOCUMENT_MAIN")
public class Document {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "DM_ID")
	private long id;
	// @Column(name = "DM_REF_NUM_ID")//fk
	// private String refID;
	@Column(name = "DM_IS_STAFF")
	private boolean isStaff;
	@Column(name = "DM_STAFF_ID")
	private String staffID;
	@Column(name = "DM_RECIPIENT_NAME")
	private String recipientName;
	@Column(name = "DM_ADDRESS")
	private String address;
	@Column(name = "DM_TITLE")
	private String title;
	@Column(name = "DM_CONTENT")
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DM_DOC_DATE")
	private Date docDate;
	@Column(name = "DM_CONTENT_SIGN")
	private String contentSign;
	@Column(name = "DM_CONTENT_CC")
	private String contentCC;
	@Column(name = "DM_IS_CONFIDENTIAL")
	private boolean isConfidential; 
	@Column(name = "DM_STATUS")
	private String status;
	@Column(name = "DM_ENTER_BY")
	private String enterBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "DM_ENTER_DATE")
	private Date enterDate;
	@Column(name = "DM_UPDATE_BY")
	private String updateBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "DM_UPDATE_DATE")
	private Date updateDate;

	// fileRefNumber FK
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DM_REF_NUM_ID")
	private FileRefNumber fileRefNumber;

	// @OneToOne(mappedBy = "document")
	// private DocumentReview documentReview;

	public Document() {
	}

	public Document(int refID, boolean isStaff, String recipientName, String address, String title, String content,
			String contentSign, String contentCC, boolean isConfidential, String status, String enterBy) {
		// this.refID = refID;
		this.isStaff = isStaff;
		this.recipientName = recipientName;
		this.address = address;
		this.title = title;
		this.content = content;
		this.contentSign = contentSign;
		this.contentCC = contentCC;
		this.isConfidential = isConfidential;
		this.status = status;
		this.enterBy = enterBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean getIsStaff() {
		return isStaff;
	}

	public void setStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public boolean getIsConfidential() {
		return isConfidential;
	}

	public void setConfidential(boolean isConfidential) {
		this.isConfidential = isConfidential;
	}

	public FileRefNumber getFileRefNumber() {
		return fileRefNumber;
	}

	public void setFileRefNumber(FileRefNumber fileRefNumber) {
		this.fileRefNumber = fileRefNumber;
	}

	/*
	 * public DocumentReview getDocumentReview() { return documentReview; }
	 * 
	 * public void setDocumentReview(DocumentReview documentReview) {
	 * this.documentReview = documentReview; }
	 */
	
	@Override
	public String toString() {
		return content;
	}

}
