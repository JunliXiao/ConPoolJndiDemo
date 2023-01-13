package model;

public class User {

	private int user_id;
	private String username;
	private String password;
	private String email;
	private String first_name;
	private String last_name;
	private String area;
	private String job;
	private String created_at;
	
	public User() {
		
	}

	public User(String username, String password, String email, String first_name, String last_name,
			String area, String job) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.area = area;
		this.job = job;
	}
	
	public User(int user_id, String username, String password, String email, String first_name, String last_name,
			String area, String job, String created_at) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.area = area;
		this.job = job;
		this.created_at = created_at;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getArea() {
		return area;
	}

	public String getJob() {
		return job;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
