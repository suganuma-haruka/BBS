package beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String password;
	private String name;
	private int branch_id;
	private int position_id;
	private boolean state;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return login_id;
	}
	public void setLoginId(String login_id) {
		this.login_id = login_id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setPositionId(int position_id) {
		this.position_id = position_id;
	}

	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}
