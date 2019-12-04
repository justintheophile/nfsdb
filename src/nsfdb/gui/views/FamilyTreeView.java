package nsfdb.gui.views;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import nsfdb.data.Database;
import nsfdb.data.ExcelDatabase;
import nsfdb.data.Queries;
import nsfdb.data.SQLDatabase;
import nsfdb.gui.nodes.MonkeyNode;

/**
 * Class used to construct JTree forming species data
 * 
 * @author justin
 *
 */
public class FamilyTreeView extends View {
	private static final long serialVersionUID = 1L;

	private JTree tree;
	private ArrayList<MonkeyNode> monkeys = new ArrayList<MonkeyNode>();



	public void init(ArrayList<MonkeyNode> monkeys) {
		this.monkeys = monkeys;
		if (monkeys.size() > 0) {
			tree = new JTree(monkeys.get(0));

			MouseListener ml = new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					int selRow = tree.getRowForLocation(e.getX(), e.getY());
					TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
					if (selRow != -1) {
						MonkeyNode monkey = (MonkeyNode) selPath.getLastPathComponent();
						if (e.getClickCount() == 1) {
							//ViewManager.detailedView.setSelectedMonkey(monkey);
						} else if (e.getClickCount() == 2) {

						}
					}
				}
			};
			tree.addMouseListener(ml);

			JScrollPane treeView = new JScrollPane(tree);
			add(treeView, BorderLayout.CENTER);
		}
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

	public static FamilyTreeView generate() {
		Database database = null;
		if(Queries.databaseSrc == Queries.Source.FILE) {
			 database = new ExcelDatabase();
		}else if(Queries.databaseSrc == Queries.Source.SQL){
			 database = new SQLDatabase();
		}
		FamilyTreeView tree = new FamilyTreeView();
		database.getData(tree);
		tree.init(tree.monkeys);
		return tree;
	}

}
