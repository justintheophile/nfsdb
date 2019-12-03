package nsfdb.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import nsfdb.data.SQLDatabaseController;
import nsfdb.gui.details.DetailedView;
import nsfdb.gui.nodes.MonkeyNode;

/**
 * Class used to construct JTree forming species data
 * 
 * @author justin
 *
 */
public class FamilyTree {
	private JTree tree;
	private JPanel panel = new JPanel(new BorderLayout());
	private ArrayList<MonkeyNode> monkeys = new ArrayList<MonkeyNode>();

	public FamilyTree() {
		SQLDatabaseController database = new SQLDatabaseController();
		database.getData(this);
		tree = new JTree(monkeys.get(0));
		
		MouseListener ml = new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        int selRow = tree.getRowForLocation(e.getX(), e.getY());
		        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		        if(selRow != -1) {
		        	MonkeyNode monkey = (MonkeyNode) selPath.getLastPathComponent();
		            if(e.getClickCount() == 1) {
		            	 GUIController.detailedView.setSelectedMonkey(monkey);
		            }
		            else if(e.getClickCount() == 2) {
		               
		            }
		        }
		    }
		};
		tree.addMouseListener(ml);
		
		
		JScrollPane treeView = new JScrollPane(tree);
		panel.add(treeView, BorderLayout.CENTER);

	}

	public MonkeyNode findMonkey(int id) {
		return monkeys.get(id);
	}
	public void addMonkey(MonkeyNode monkey) {
		monkeys.add(monkey);
	}

	private DefaultMutableTreeNode nodeFromString(String data) {
		return new DefaultMutableTreeNode(data);
	}

	public DefaultMutableTreeNode findNode(String nodeName) {
		TreePath path = tree.getNextMatch(nodeName, 0, Position.Bias.Forward);
		return (DefaultMutableTreeNode) path.getLastPathComponent();

	}

	public JPanel getPanel() {
		return panel;
	}
}
