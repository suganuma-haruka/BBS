package beans;

import java.io.Serializable;
import java.util.Date;

public class UserComment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int comment_id;
	private int posting_id;
	private int user_id;
	private int branch_id;
	private int position_id;
	private String name;
	private String text;
	private Date insert_date;


	public int getPostingId() {
		return posting_id;
	}

	public void setPostingId(int posting_id) {
		this.posting_id = posting_id;

	}

	public int getCommentId() {
		return comment_id;
	}

	public void setCommentId(int comment_id) {
		this.comment_id = comment_id;

	}
	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getBranchId() {
		return branch_id;
	}

	public void setBranchId(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getPositionId() {
		return position_id;
	}

	public void setPositionId (int position_id) {
		this.position_id = position_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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