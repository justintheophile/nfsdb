package nsfdb.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * Class used to construct JTree forming species data
 * 
 * @author justin
 *
 */
public class FamilyTree {
	private JTree tree;
	private JPanel panel = new JPanel(new BorderLayout());
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode("Species Data");

	public FamilyTree() {
		createNodes(top);
		tree = new JTree(top);
		JScrollPane treeView = new JScrollPane(tree);
		panel.add(treeView, BorderLayout.CENTER);
	}

	private void createNodes(DefaultMutableTreeNode top) {
		addNode(addNode(top, nodeFromString("Books for Java Programmers")), nodeFromString("new node"));
	}

	private DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, DefaultMutableTreeNode child) {
		parent.add(child);
		return child;
	}

	private DefaultMutableTreeNode nodeFromString(String data) {
		return new DefaultMutableTreeNode(data);
	}

	public DefaultMutableTreeNode findNode(String nodeName) {
		TreePath path = tree.getNextMatch(nodeName, 0, Position.Bias.Forward);
		return (DefaultMutableTreeNode)path.getLastPathComponent();

	}

	public JPanel getPanel() {
		return panel;
	}
}
