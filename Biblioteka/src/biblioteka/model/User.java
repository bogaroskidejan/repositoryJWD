package biblioteka.model;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1769169794415719097L;
	
	protected String username;
	protected String password;
	protected int id;
	
	public User(String username, String passsword) {
		super();
		this.username = username;
		this.password = passsword;
	}
	
	public User(int id, String username, String passsword) {
		super();
		this.username = username;
		this.password = passsword;
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsernameAndPassword(){
		return username+":"+password;
	}
	
	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		if(this.username.equals(u.getUsername())&&this.password.equals(u.getPassword())){
			return true;
		}
		else{
			return false;
		}
	}
	
}
