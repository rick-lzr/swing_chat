package common.model.entity;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Friend implements Serializable {
	private static final long serialVersionUID = -427838794924380991L;
	private int frdid;
	private int fromuser;
	private int touser;
	private int frdstate;
	
	
	public int getFrdid() {
		return frdid;
	}
	public void setFrdid(int frdid) {
		this.frdid = frdid;
	}
	public int getFromuser() {
		return fromuser;
	}
	public void setFromuser(int fromuser) {
		this.fromuser = fromuser;
	}
	public int getTouser() {
		return touser;
	}
	public void setTouser(int touser) {
		this.touser = touser;
	}
	public int getFrdstate() {
		return frdstate;
	}
	public void setFrdstate(int frdstate) {
		this.frdstate = frdstate;
	}

}