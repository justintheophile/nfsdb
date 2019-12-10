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
import nsfdb.data.LocalDatabase;
import nsfdb.data.Queries;
import nsfdb.data.SQLDatabase;
import nsfdb.data.SourceController;
import nsfdb.data.containers.Monkey;

/**
 * Class used to construct JTree forming species data
 * 
 * @author justin
 *
 */
public class FamilyTreeView extends View {
	private static final long serialVersionUID = 1L;

	private JTree tree;
	private ArrayList<Monkey> monkeys = new ArrayList<Monkey>();

	public void init(ArrayList<Monkey> monkeys) {
		this.monkeys = monkeys;
		if (monkeys.size() > 0) {
			tree = new JTree(monkeys.get(0));

			MouseListener ml = new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					int selRow = tree.getRowForLocation(e.getX(), e.getY());
					TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
					if (selRow != -1) {
						Monkey monkey = (Monkey) selPath.getLastPathComponent();
						if (e.getClickCount() == 1) {
							if (parent != null) {
								parent.setView(new ScanImageView());
								parent.setView(MonkeyDataView.generate(monkey));
								parent.setView(ScansView.generate(monkey));

							}
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

	public Monkey findMonkey(int id) {
		return monkeys.get(id);
	}

	public void addMonkey(Monkey monkey) {
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
		Database database = SourceController.getNewDataSource();

		FamilyTreeView tree = new FamilyTreeView();
		database.getFamilyData(tree, "0");
		tree.init(tree.monkeys);
		return tree;
	}

}
