import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.*;

/**
 * This is an example code that uses some of the different JTree API.
 * We add listeners to events on the view (such as expand/collapse or select item).
 * We use the default tree model here. We add listener to changes to the model.
 * We use controller buttons to make changes to the tree model.
 * 
 * Credits: Modified from code obtained from Dr. Samik Basu
 * @author smitra
 *
 */
public class TreeFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Tree Frame");
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TreePanel());
		frame.setVisible(true);
	}
}

class TreePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTree tree;
	private DefaultTreeModel tModel;

	public TreePanel() {
		// Layout Management of the Panel
		setLayout(new BorderLayout());

		// CREATE THE TREEMODEL
		tModel = createTreeModel();
		
		// CREATE THE JTREE (the VIEW) and SET ITS PROPERTIES
		tree = new JTree(tModel);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(true);
		tree.setEditable(false);
		
		// ADD TREE NODE LISTENERS (i.e. when a node in tree is selected)
		tree.addTreeSelectionListener(createTreeSelectionListener());

		// ADD TREE EXPANSION LISTENERS (i.e. when a tree node starts expanding or collapsing).
		tree.addTreeWillExpandListener(createTreeWillExpandListener());

		// ADD THE JTREE to the main panel (in a scroll pane)
		JScrollPane scroll = new JScrollPane(tree);
		add(scroll, BorderLayout.CENTER);

		// CREATE THE CONTROLLER BUTTONS TO MODIFY THE TREE MODEL
		JPanel controllerButtonsPanel = createControllerButtonsPanel();
		
		// ADD THE CONTROLLER BUTTONS to the main panel.
		add(controllerButtonsPanel, BorderLayout.SOUTH);

	}

	/**
	 * This returns a listener to tree folder expand/collapse events
	 * @return
	 */
	private TreeWillExpandListener createTreeWillExpandListener() {
		return new TreeWillExpandListener() {
			public void treeWillCollapse(TreeExpansionEvent evt)
					throws ExpandVetoException {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt
						.getPath().getLastPathComponent();
				System.out.println("In TreeWillExpandListener: Collapsing: "
						+ node.getUserObject());
			}

			public void treeWillExpand(TreeExpansionEvent evt)
					throws ExpandVetoException {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) evt
						.getPath().getLastPathComponent();
				System.out.println("TreeWillExpandListener: Expanding: "
						+ node.getUserObject());
			}

		};
	}

	/**
	 * This returns a listener for tree item selection
	 * @return
	 */
	private TreeSelectionListener createTreeSelectionListener() {
		return new TreeSelectionListener() {
			// valueChanged - fired when the selection changes.
			public void valueChanged(TreeSelectionEvent evt) {
				TreePath selectedPath = tree.getSelectionPath();
				if (selectedPath != null)
					System.out.println("In TreeSelectionListener: "
							+ selectedPath.getLastPathComponent());
			}
		};

	}

	/**
	 * Here we create a TreeModel. 
	 * @return
	 */
	private DefaultTreeModel createTreeModel() {
		// create the root USA
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("USA");

		// First child is IOWA!
		root.add(new DefaultMutableTreeNode("Iowa"));

		// other way to create a DefaultMutableTreeNode:
		// create an empty node and then add the user object
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
		newNode.setUserObject("California");

		// add a child to newNode
		newNode.add(new DefaultMutableTreeNode("Sacramento"));

		// add a sibling to Iowa (i.e. California)
		root.add(newNode);

		// create the model using the root of the tree
		DefaultTreeModel treeModel = new DefaultTreeModel(root);

		// ADD MODEL LISTENERS (changes/insertion/deletion of nodes)
		treeModel.addTreeModelListener(createTreeModelListener());
		return treeModel;

	}

	private TreeModelListener createTreeModelListener() {
		/*
		 * Model listeners Note: any change to the tree structure or the node
		 * value will result in TreeModelEvent and a notification will be sent
		 * to the TreeModelListeners
		 */
		return new TreeModelListener() {
			public void treeNodesChanged(TreeModelEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) (evt
						.getTreePath().getLastPathComponent());
				System.out
						.println("In TreeModelListener: The User has finished editing the child node of "
								+ node.getUserObject());
			}

			public void treeNodesInserted(TreeModelEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) (evt
						.getTreePath().getLastPathComponent());
				System.out
						.println("In TreeModelListener: A new node is added to "
								+ node.getUserObject());
			}

			public void treeNodesRemoved(TreeModelEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) (evt
						.getTreePath().getLastPathComponent());
				System.out
						.println("In TreeModelListener: A node is removed from "
								+ node.getUserObject());
			}

			// need to have a drastic change or fired explicitly: remove the
			// root node
			public void treeStructureChanged(TreeModelEvent evt) {
				System.out
						.println("In TreeModelListener: Tree Structure Changed");
			}
		};
	}

	private JPanel createControllerButtonsPanel() {
		/*
		 * THESE ARE THE CONTROLLER BUTTONS FOR MODIFYING THE MODEL
		 * adding/removing nodes from the tree create a panel which contains
		 * buttons for updating the tree
		 */
		JPanel buttonPanel = new JPanel();
		JButton addSib = new JButton("Add Sibling");
		JButton addChld = new JButton("Add Child");
		JButton remNode = new JButton("Remove");
		JButton remRoot = new JButton("Remove Root");
		buttonPanel.add(addSib);
		buttonPanel.add(addChld);
		buttonPanel.add(remNode);

		// adding a sibling action!
		addSib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Identify the node that has been selected
				DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selected == null) return;

				// get the parent of the selected node
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selected
						.getParent();
				if (parent == null)
					return;

				// add a new node after the selected node as a child of the
				// selected node's parent
				// We add item to the MODEL! The view automatically updates.
				tModel.insertNodeInto(new DefaultMutableTreeNode("New Node"),
						parent, parent.getIndex(selected) + 1);

				/*
				 * Note: additions are done to the model, not the view. See
				 * below for the treeModelListener methods.
				 */
			}
		});

		// similar method for adding a child node of a selected node: note the
		// usage the getChildCount()
		addChld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Identify the node that has been selected
				DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selected == null)
					return;

				// add a new node as the last child of the selected node
				tModel.insertNodeInto(new DefaultMutableTreeNode("New Node"),
						selected, selected.getChildCount());

				// Lets also expand the tree to show the new node
				// Find the array of nodes that make up the path from the root
				// to the newly added node
				TreeNode[] nodes = tModel.getPathToRoot(selected
						.getChildAt(selected.getChildCount() - 1));

				// Create a tree path with these nodes
				TreePath path = new TreePath(nodes);
				// Make the entire path visible and make the scroller to move to
				// the last path component
				tree.scrollPathToVisible(path);
			}
		});

		// remove some selected node: you cannot remove the root node
		remNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Identify the node that has been selected
				DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (selected == null) return;
				
				// Identify the parent of the selected node; we are not allowing
				// the root node to be removed
				if (selected.getParent() == null)
					return;

				// User the models remove method to remove the selected node
				tModel.removeNodeFromParent(selected);
			}
		});

		return buttonPanel;

	} // end of method getControllerButtonPanel

} // end of class

