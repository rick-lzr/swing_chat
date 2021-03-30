package server.model.entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class MyJTextField extends JTextField {
	 
		private static final long serialVersionUID = 1L;
		private JPopupMenu jPopupMenu = new JPopupMenu();
		private JMenuItem copy = new JMenuItem("复制");
		private JMenuItem paste = new JMenuItem("粘贴");
		private JMenuItem cut = new JMenuItem("剪切");
	    
	    MyJTextField myself = this;
		
		public MyJTextField(){
			this.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent arg0) {
					if(arg0.getButton()==3){
						if(myself.isEnabled()){//如果当前组件处于不可用状态，则不弹出右键菜单
						
							jPopupMenu.removeAll();
							if(!myself.isEditable()){
								jPopupMenu.add(copy);
							}else{
								jPopupMenu.add(copy);
								jPopupMenu.add(paste);
								jPopupMenu.add(cut);
							}
							jPopupMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());		
						}
						System.out.println("asdads");
					}
					System.out.println("asdads");
				}
	        });
			
			jPopupMenu.add(copy);
			jPopupMenu.add(paste);
			jPopupMenu.add(cut);
	        

			copy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					myself.copy();
				}
			});

	        paste.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					myself.paste();		
				}   	
	        });

	        cut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					myself.cut();		
				}   	
	        });    
		}	
	}
