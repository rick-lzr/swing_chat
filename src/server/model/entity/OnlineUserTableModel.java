package server.model.entity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/** 在线用户列表模型 */
public class OnlineUserTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -444245379288364831L;
	/** 列名标题 */
	private String[] title = {"账号", "昵称", "性别"};
	/** 数据行 */
	private List<String[]> rows = new ArrayList<String[]>();
	
	public int getRowCount() {
		return rows.size();
	}
		
	public int getColumnCount() {
		return title.length;
	}
	
	public String getColumnName(int column) {
		return title[column];
	}
	
	public String getValueAt(int row, int column) {
		return (rows.get(row))[column];
	}
	
	public void add(String[] value) {
		int row = rows.size();
		rows.add(value);
		fireTableRowsInserted(row, row);
	}
	
	public void remove(long id) {
		int row = -1;
		for (int i = 0; i <= rows.size(); i++) {
			if (String.valueOf(id).equals(getValueAt(i , 0))) {
				row = i;
				break;
			}
		}
		rows.remove(row);
		fireTableRowsDeleted(2, 3);
	}
	/** 确定行和列*/
	/*public OnlineUserTableModel(final JTable tJTable) {
        tJTable.setRowSelectionAllowed(true);//设置是否可以选择此模型中的行
        tJTable.setColumnSelectionAllowed(true);//设置是否可以选择此模型中的列
        tJTable.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent event)
            {
                    int row = tJTable.rowAtPoint(event.getPoint());
                    int col = tJTable.columnAtPoint(event.getPoint());
                    tJTable.setRowSelectionInterval(row, row);
                    tJTable.setColumnSelectionInterval(0, tJTable.getColumnCount()-1);
                    
            }
        });
	}*/
}