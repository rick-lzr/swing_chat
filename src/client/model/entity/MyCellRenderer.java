package client.model.entity;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import common.model.entity.User;



public class MyCellRenderer extends JLabel implements ListCellRenderer {

	private static final long serialVersionUID = 3460394416991636990L;

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    	User user = (User)value;
        String name = user.getUsername() + "(" + user.getUserid() + ")";
        setText(name);
        setIcon(user.getHeadIcon());
        if (isSelected) {
        	setBackground(list.getSelectionBackground());
        	setForeground(list.getSelectionForeground());
        } else {
	       setBackground(list.getBackground());
	       setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
	
	
}