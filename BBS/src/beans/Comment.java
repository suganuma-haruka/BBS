package beans;

import java.io.Serializable;
import java.util.Date;

	public class Comment implements Serializable {
		private static final long serialVersionUID = 1L;

		private int comment_id;
		private int user_id;
		private int posting_id;
		private String text;
		private Date insert_date;
		private Date update_date;

		public int getCommnetsId() {
			return comment_id;
		}
		public void setCommnetsId(int comment_id) {
			this.comment_id = comment_id;
		}

		public int getUserId() {
			return user_id;
		}

		public void setUserId(int user_id) {
			this.user_id = user_id;
		}

		public int getPostingId() {
			return posting_id;
		}
		public void setPostingId(int posting_id) {
			this.posting_id = posting_id;
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

		public Date getUpdateDate() {
			return update_date;
		}
		public void setUpdateDate(Date update_date) {
			this.update_date = update_date;
		}

}
