package beans;

import java.io.Serializable;
import java.util.Date;

public class UserPosting implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String title;
	private String text;
	private Date insert_date;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Date getInsertDate() {
		return insert_date;
	}
	public void setInsertDate(Date insert_date) {
		this.insert_date = insert_date;
	}
}
