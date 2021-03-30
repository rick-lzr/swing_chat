package common.model.entity;

import java.io.Serializable;
import javax.swing.ImageIcon;

import com.sun.glass.ui.CommonDialogs.Type;

public class User implements Serializable {
	
	private static final long serialVersionUID = -427838794924380991L;
	private long userid;
	
	private String username;
	private String nikename;
	private String password;
	private String Sex;
	private int head;
	private long phoneNumber;
	private String type;
	private int age;
	
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", nikename=" + nikename + ", password=" + password
				+ ", sex=" + Sex + ", head=" + head + ", phoneNumber=" + phoneNumber + ", type=" + type + ", age=" + age
				+ "]";
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return password;
	}
	public void setUserpwd(String userpwd) {
		this.password = userpwd;
	}
	
	public String getSex() {
		return Sex;
	}
	public void setSex(String usersex) {
		this.Sex = usersex;
	}
	
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	
	public ImageIcon getHeadIcon() {
		ImageIcon image = new ImageIcon("images/" + head + ".png");
		return image;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNikename() {
		return nikename;
	}
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + head;
		result = prime * result + ((nikename == null) ? 0 : nikename.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
		result = prime * result + ((Sex == null) ? 0 : Sex.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + (int) (userid ^ (userid >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (head != other.head)
			return false;
		if (nikename == null) {
			if (other.nikename != null)
				return false;
		} else if (!nikename.equals(other.nikename))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (Sex == null) {
			if (other.Sex != null)
				return false;
		} else if (!Sex.equals(other.Sex))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userid != other.userid)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}