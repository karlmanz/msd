package my.iium.hr.model;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DOCUMENT_REVIEW")
public class DocumentReview {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "DR_ID")
	private long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "DR_SUBMISSION_DATE")
	private Date submissionDate;
	//@Column(name = "DR_DOCUMENT_ID") //fk
	//private Long documentId;
	@Column(name = "DR_REF_CODE_ID")
	private String refCodeID;
	@Column(name = "DR_SUPERVISOR_ID")
	private String supervisorID;
	@Column(name = "DR_COMMENT")
	private String comment;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "DR_COMMENT_DATE")
	private Date commentDate;
	//document FK
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DR_DOCUMENT_ID")
    private Document document;
	
	
	public DocumentReview() {
	}

	public DocumentReview(String refCodeID, String supervisorID, String comment, Date commentDate) {
		this.refCodeID = refCodeID;
		this.supervisorID = supervisorID;
		this.comment = comment;
		this.commentDate = commentDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getRefCodeID() {
		return refCodeID;
	}

	public void setRefCodeID(String refCodeID) {
		this.refCodeID = refCodeID;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	public void setDocumentStatus(String status) {
		document.setStatus(status);
	}
	public void setDocumentStaffID(String staffID) {
		document.setStaffID(staffID);
	}
	public void setDocumentRecipientName(String recipientName) {
		document.setRecipientName(recipientName);
	}
	public void setDocumentAddress(String address) {
		document.setAddress(address);
	}
	public void setDocumentTitle(String title) {
		document.setTitle(title);
	}
	public void setDocumentSign(String sign) {
		document.setContentSign(sign);
	}
	public void setDocumentCC(String cc) {
		document.setContentCC(cc);
	}
	public Long getDocumentId() {
		return document.getId();
	}
	public void setDocumentContent(String content) {
		document.setContent(content);
	}
	public void setDocumentEnterBy(String enterBy) {
		document.setEnterBy(enterBy);
	}
	public void setDocumentUpdateBy(String updateBy) {
		document.setUpdateBy(updateBy);
	}
}
