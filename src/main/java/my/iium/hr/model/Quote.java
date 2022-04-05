package my.iium.hr.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private String _id;
	private String[] tags;
	private String content;
	private String author;
	private String authorSlug;
	private int length;

	protected Quote() {

	}

	// Setters and getters
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorSlug() {
		return authorSlug;
	}

	public void setAuthorSlug(String authorSlug) {
		this.authorSlug = authorSlug;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Quote{" + "type='" + content + '\'' + ", value=" + author + '}';
	}

}
