package nsfdb.gui.views;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import nsfdb.data.containers.Monkey;

public class MonkeyDataView extends View {
	private static final long serialVersionUID = 1L;
	Monkey monkey;
	String[] stats = {"Sequence ID", "Subject Code", "Gender", "Birth Year", "Death Year", "Mother ID", "Generation", "Family ID", "Sibling Number"};
	String[] categories = {"Statistic", "Value"};
	private MonkeyDataView(Monkey monkey) {
		this.monkey = monkey;
		
		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() {
				return 2;
			}

			public int getRowCount() {
				return 9;
			}

			public Object getValueAt(int row, int col) {
				if(col == 0) {
					return stats[row];
				}else {
					return monkey.getColumn(row);
				}
			}
			@Override
			public String getColumnName(int index) {
				
			    return categories[index];
			}
		};
		JTable table = new JTable(dataModel);
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);
	}

	public static MonkeyDataView generate(Monkey monkey) {
		MonkeyDataView monkeyView = new MonkeyDataView(monkey);
		return monkeyView;
	}

}
