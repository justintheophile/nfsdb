package nsfdb.gui.views.interactive;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.jinteractive.main.Camera;
import com.jinteractive.main.GraphicsPanel;
import com.jinteractive.widgets.ButtonWidget;
import com.jinteractive.widgets.Widget;
import com.jinteractive.widgets.WidgetAction;

import nsfdb.data.containers.Monkey;
import nsfdb.gui.views.FamilyTreeListView;
import nsfdb.gui.views.View;

public class InteractiveFamilyTreeView extends View {
	private static final long serialVersionUID = 1L;
	ArrayList<Monkey> monkeys = new ArrayList<Monkey>();
	Thread thread;
	InteractivePanel panel;
	FamilyTreeListView familyTree;

	private InteractiveFamilyTreeView(FamilyTreeListView familyTree) {
		this.monkeys = familyTree.getMonkeys();
		this.familyTree = familyTree;
		panel = new InteractivePanel(this);
		add(panel, BorderLayout.CENTER);

		Runnable run = new Runnable() {
			public void run() {
				panel.gameloop();
			}
		};

		thread = new Thread(run);
		thread.start();
	}

	public void build() {
		panel.build();
	}

	public static InteractiveFamilyTreeView generate(FamilyTreeListView familyTree) {
		InteractiveFamilyTreeView interactiveView = new InteractiveFamilyTreeView(familyTree);
		return interactiveView;
	}

	public void cleanup() {
		super.cleanup();
		panel.stop();
	}

}

class InteractivePanel extends GraphicsPanel {
	private static final long serialVersionUID = 1L;
	InteractiveFamilyTreeView parent;
	ArrayList<ArrayList<Monkey>> generations = new ArrayList<ArrayList<Monkey>>();
	Widget root;

	public InteractivePanel(InteractiveFamilyTreeView parent) {
		super(60);

		this.parent = parent;
		root = new Widget(0, 0, 0, 0);
		root.setCamera(camera);

	}

	public void init() {

	}

	/**
	 * Construct the family tree structure.
	 * 
	 * (NOTE: child data must be sorted by generation in database)
	 */
	public void build() {
		int generations = 0;
		// finding number of generations
		for (int i = 0; i < parent.monkeys.size(); i++) {
			Monkey monkey = parent.monkeys.get(i);
			int gen = Integer.parseInt(monkey.getGeneration());

			if (gen > generations) {
				generations = gen;
			}
		}
		generations += 1;
		System.out.println(generations);

		// getting size of each generation
		int[] generationSizes = new int[generations];
		for (int i = 0; i < parent.monkeys.size(); i++) {
			Monkey monkey = parent.monkeys.get(i);
			int gen = Integer.parseInt(monkey.getGeneration());
			generationSizes[gen]++;
		}
		System.out.println(Arrays.toString(generationSizes));

		// sort list by parent and generation
		Collections.sort(parent.monkeys, new Comparator<Monkey>() {

			@Override
			public int compare(Monkey monkey1, Monkey monkey2) {
				int generation1 = Integer.parseInt(monkey1.getGeneration());
				int generation2 = Integer.parseInt(monkey2.getGeneration());
				if (monkey1.getMotherID().equals("null")) {
					return -1;
				} else if (monkey2.getMotherID().equals("null")) {
					return 1;
				}
				int parent1 = Integer.parseInt(monkey1.getMotherID());
				int parent2 = Integer.parseInt(monkey2.getMotherID());

				if (generation1 > generation2) {
					return 1;
				} else if (generation1 < generation2) {
					return -1;
				} else {
					if (parent1 > parent2) {
						return 1;
					} else if (parent1 < parent2) {
						return -1;
					}
				}
				return 0;
			}

		});

		System.out.println(parent.monkeys.toString());

		// sort list by so parents with children that are placed first
		// have higher priority
		Collections.sort(parent.monkeys, new Comparator<Monkey>() {

			@Override
			public int compare(Monkey monkey1, Monkey monkey2) {

				if (!monkey1.getGeneration().equals(monkey2.getGeneration())) {
					return 0;
				}
				int index1 = findLastChildIndex(monkey1);
				int index2 = findLastChildIndex(monkey2);
				if (index1 > index2) {
					return 1;
				} else if (index1 < index2) {
					return -1;
				}
				return 0;
			}

		});

		System.out.println(parent.monkeys.toString());

		/*-
		 * Algorithm: 
		 * 1. loop through last generation 
		 * 2. Monkeys that are siblings have
		 * a space of 2 between them, monkeys that are not siblings have a larger
		 * between them 
		 * 3. for every subsequent generation, center the parent on group
		 * of children
		 */
		float cursorX = 0;
		float cursorY = 0;
		int currentGeneration = -1;

		String lastParent = null;
		for (int i = 0; i < parent.monkeys.size(); i++) {
			int index = parent.monkeys.size() - 1 - i;
			Monkey monkey = parent.monkeys.get(index);
			ArrayList<MonkeyWidget> children = findChildren(monkey);
			int cgen = Integer.parseInt(monkey.getGeneration());
			if (currentGeneration != cgen) {
				currentGeneration = cgen;
				cursorX = 0;
			}
			cursorY = currentGeneration * 8;
			MonkeyWidget widget = null;
			if (children.size() == 0) {
				if (lastParent == null) {

				} else if (lastParent.equals(monkey.getMotherID())) {
					cursorX += 6;
				} else {
					cursorX += 7;
					lastParent = monkey.getMotherID();
				}
				widget = new MonkeyWidget(this, monkey, cursorX * Camera.GRIDSIZE, cursorY * Camera.GRIDSIZE);
			} else {
				float min = Float.MAX_VALUE;
				float max = Float.MIN_VALUE;
				float width;
				for (MonkeyWidget child : children) {
					float x = child.bounds().x / Camera.GRIDSIZE;
					if (x < min) {
						min = x;
					}
					if (x > max) {
						max = x;
					}
				}
				width = max - min;
				cursorX = min + width / 2;
				widget = new MonkeyWidget(this, monkey, cursorX * Camera.GRIDSIZE, cursorY * Camera.GRIDSIZE);
				cursorX = max;
			}

			root.addChild(widget);
			lastParent = monkey.getMotherID();
		}

	}

	public int findLastChildIndex(Monkey monkey) {
		int index = -1;
		for (int i = 0; i < parent.monkeys.size(); i++) {
			Monkey child = parent.monkeys.get(i);
			if (child.getMotherID().equals(monkey.getSequenceID())) {
				if (i > index) {
					index = i;
				}
			}
		}
		return index;
	}

	public ArrayList<MonkeyWidget> findChildren(Monkey monkey) {
		ArrayList<MonkeyWidget> children = new ArrayList<MonkeyWidget>();
		for (int i = 0; i < root.children.size(); i++) {
			if (root.children.get(i) instanceof MonkeyWidget) {
				MonkeyWidget widget = (MonkeyWidget) root.children.get(i);
				if (widget.monkey.getMotherID().equals(monkey.getSequenceID())) {
					children.add(widget);
				}
			}
		}
		return children;
	}

	public MonkeyWidget findMonkeyParent(Monkey monkey) {
		for (int i = 0; i < root.children.size(); i++) {
			if (root.children.get(i) instanceof MonkeyWidget) {
				MonkeyWidget widget = (MonkeyWidget) root.children.get(i);
				if (monkey.getMotherID().equals(widget.monkey.getSequenceID())) {
					return widget;
				}
			}
		}
		return null;
	}

	public void draw(Graphics2D g2d) {
		root.draw(g2d);
	}

	public void drawGui(Graphics2D g2d) {

	}

	public void update(float delta) {
		root.update(delta);
	}

}

class MonkeyWidget extends ButtonWidget {
	public Monkey monkey;
	InteractivePanel parent;
	final static BasicStroke stroke = new BasicStroke(2);

	public MonkeyWidget(InteractivePanel parent, Monkey monkey, float x, float y) {
		super(monkey.getSubjectCode(), x, y, 4, 4, new WidgetAction() {

			public void act() {
				parent.parent.familyTree.load(monkey);
			}
		});
		this.parent = parent;
		this.monkey = monkey;
	}

	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		MonkeyWidget parent = this.parent.findMonkeyParent(monkey);
		if (parent != null) {
			g2d.setColor(Color.black);
			g2d.setStroke(stroke);
			g2d.drawLine((int) (bounds().x + bounds().width / 2), (int) (bounds().y),
					(int) (bounds().x + bounds().width / 2), (int) (bounds().y + (parent.bounds().y - bounds().y) / 4));
			g2d.drawLine((int) (parent.bounds().x + parent.bounds().width / 2),
					(int) (parent.bounds().y + parent.bounds().height),
					(int) (parent.bounds().x + parent.bounds().width / 2),
					(int) (bounds().y + (parent.bounds().y - bounds().y) / 4));
			g2d.drawLine((int) (bounds().x + bounds().width / 2),
					(int) (bounds().y + (parent.bounds().y - bounds().y) / 4),
					(int) (parent.bounds().x + parent.bounds().width / 2),
					(int) (bounds().y + (parent.bounds().y - bounds().y) / 4));
		}

	}

}
