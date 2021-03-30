package client.model.entity;

import java.util.List;

import javax.swing.AbstractListModel;

import common.model.entity.User;

/** 在线用户JList的Model */
public class MyFriendsTableModel extends AbstractListModel {
	private static final long serialVersionUID = -3903760573171074301L;
	private List<User> myFriendsTableModel;
	
	public MyFriendsTableModel(List<User> onlineUsers) {
		this.myFriendsTableModel = onlineUsers;
	}
	
	public void addElement(Object object) {
		if (myFriendsTableModel.contains(object)) {
			return;
		}
		int index = myFriendsTableModel.size();
		myFriendsTableModel.add((User)object);
		fireIntervalAdded(this, index, index);
	}
	
	public boolean removeElement(Object object) {
		int index = myFriendsTableModel.indexOf(object);
		if (index >= 0) {
			fireIntervalRemoved(this, index, index);
		}
		return myFriendsTableModel.remove(object);
	}
	 
    public int getSize() { 
    	return myFriendsTableModel.size(); 
    }
    
    public Object getElementAt(int i) {
    	return myFriendsTableModel.get(i); 
    }
    
    public List<User> getOnlineUsers() {
    	return myFriendsTableModel;
    }

	
}