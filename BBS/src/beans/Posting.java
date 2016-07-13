package beans;

import java.io.Serializable;
import java.util.Date;

public class Posting implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int user_id;
	private String title;
	private String text;
	private String category;
	private Date insert_date;
	private Date update_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getInsertDate() {
		return insert_date;
	}

	public void setInsertDate(Date insert_date) {
		this.insert_date = insert_date;
	}

	public Date getUpdateDate() {
		return update_date;
	}

	public void setUpdateDate(Date update_date) {
		this.update_date = update_date;
	}

}
