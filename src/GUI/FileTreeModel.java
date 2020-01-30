/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {
    // We specify the root directory when we create the model.

    protected File root;
    private Vector listeners = new Vector();

    public FileTreeModel(File root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {

        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        String[] children = directory.list();

        return new TreeFile(directory, children[index]);
    }

    // Tell JTree how many children a node has
    @Override
    public int getChildCount(Object parent) {
        File file = (File) parent;
        if (file.isDirectory()) {
            String[] fileList = file.list();

            if (fileList != null) {
                return file.list().length;
            }
        }
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        File file = (File) node;
        return file.isFile();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File file = (File) child;
        String[] children = directory.list();
        for (int i = 0; i < children.length; i++) {
            if (file.getName().equals(children[i])) {
                return i;
            }
        }
        return -1;

    }

    // Fetch any numbered child of a node for the JTree.
    // Our model returns File objects for all nodes in the tree.  The
    // JTree displays these by calling the File.toString() method.
    

    // Figure out a child's position in its parent node.
    

    // This method is only invoked by the JTree for editable trees.  
    // This TreeModel does not allow editing, so we do not implement 
    // this method.  The JTree editable property is false by default.
    @Override
    public void valueForPathChanged(TreePath tp, Object o) {
        File oldFile = (File) tp.getLastPathComponent();
        String fileParentPath = oldFile.getParent();
        String newFileName = (String) o;
        File targetFile = new File(fileParentPath, newFileName);
        oldFile.renameTo(targetFile);
        File parent = new File(fileParentPath);
        int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
        Object[] changedChildren = {targetFile};
        fireTreeNodesChanged(tp.getParentPath(), changedChildrenIndices, changedChildren);

    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
        Iterator iterator = listeners.iterator();
        TreeModelListener listener = null;
        while (iterator.hasNext()) {
            listener = (TreeModelListener) iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl) {
        listeners.add(tl);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl) {
        listeners.remove(tl);
    }

    public class TreeFile extends File {

        public TreeFile(File parent, String child) {
            super(parent, child);
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}
