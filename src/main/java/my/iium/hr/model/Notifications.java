package my.iium.hr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "view_notification")
@EntityListeners(AuditingEntityListener.class)
public class Notifications {

	@Id
	@Column(name = "notify_from")
	private String notify_from;
	@Column(name = "notify_to")
	private String notify_to;
	@Column(name = "notify_doc_id")
	private String notify_doc_id;
	@Column(name = "notify_title")
	private String notify_title;
	@Column(name = "notify_status")
	private String notify_status;
	@Column(name = "notify_action")
	private String notify_action;
	@Column(name = "notify_date")
	private String notify_date;

	public Notifications() {
		super();
	}

	public Notifications(String notify_from, String notify_to, String notify_doc_id, String notify_title,
			String notify_status, String notify_action, String notify_date) {
		super();
		this.notify_from = notify_from;
		this.notify_to = notify_to;
		this.notify_doc_id = notify_doc_id;
		this.notify_title = notify_title;
		this.notify_status = notify_status;
		this.notify_action = notify_action;
		this.notify_date = notify_date;
	}

	public String getNotify_from() {
		return notify_from;
	}

	public void setNotify_from(String notify_from) {
		this.notify_from = notify_from;
	}

	public String getNotify_to() {
		return notify_to;
	}

	public void setNotify_to(String notify_to) {
		this.notify_to = notify_to;
	}

	public String getNotify_doc_id() {
		return notify_doc_id;
	}

	public void setNotify_doc_id(String notify_doc_id) {
		this.notify_doc_id = notify_doc_id;
	}

	public String getNotify_title() {
		return notify_title;
	}

	public void setNotify_title(String notify_title) {
		this.notify_title = notify_title;
	}

	public String getNotify_status() {
		return notify_status;
	}

	public void setNotify_status(String notify_status) {
		this.notify_status = notify_status;
	}

	public String getNotify_action() {
		return notify_action;
	}

	public void setNotify_action(String notify_action) {
		this.notify_action = notify_action;
	}

	public String getNotify_date() {
		return notify_date;
	}

	public void setNotify_date(String notify_date) {
		this.notify_date = notify_date;
	}

	@Override
	public String toString() {
		return "Notifications [notify_from=" + notify_from + ", notify_to=" + notify_to + ", notify_doc_id="
				+ notify_doc_id + ", notify_title=" + notify_title + ", notify_status=" + notify_status
				+ ", notify_action=" + notify_action + ", notify_date=" + notify_date + "]";
	}

}
