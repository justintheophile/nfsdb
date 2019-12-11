package nsfdb.gui.views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import nsfdb.data.Database;
import nsfdb.data.SourceController;
import nsfdb.data.containers.Monkey;
import nsfdb.data.containers.Scan;

public class ScansView extends View {
	private static final long serialVersionUID = 1L;
	Monkey monkey;
	private static final String[] categories = { "Scan ID", "Monkey ID", "Title", "Description", "Date" };
	ArrayList<Scan> scans;

	private ScansView(Monkey monkey, ArrayList<Scan> scans) {
		this.monkey = monkey;
		this.scans = scans;

		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() {
				return 5;
			}

			public int getRowCount() {
				return scans.size();
			}

			public Object getValueAt(int row, int col) {
				Scan scan= scans.get(row);
				return scan.getColumn(col);
			}
			@Override
			public String getColumnName(int index) {
			    return categories[index];
			}
		};
		
		
		JTable table = new JTable(dataModel);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            parent.setView(ScanImageView.generate(table.getValueAt(table.getSelectedRow(), 0).toString()));
	        }
	    });
		
		JScrollPane scrollpane = new JScrollPane(table);
		add(scrollpane, BorderLayout.CENTER);
	}
	public static ScansView generate(Monkey monkey) {
		Database database = SourceController.getNewDataSource();
		ArrayList<Scan> scans = database.getScanData(monkey);
		final int size = scans.size();

		ScansView scanView = new ScansView(monkey, scans);
		return scanView;

	}

}
