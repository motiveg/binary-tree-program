/**
 * Student: Brian Casipit
 * Course: CS 11C - Data Structures and Algorithms
 */

package BinaryTreeProgram;

// ADT binary search tree.
//  Assumption: A tree contains at most one item with a
//  given search key at any time.

public class BinarySearchTree<T extends KeyedItem<KT>,
                             KT extends Comparable<? super KT>>
        extends BinaryTreeBasis<T> {
    // inherits isEmpty(), makeEmpty(), getRootItem(), and
    // the use of the constructors from BinaryTreeBasis

    public BinarySearchTree() {
    //-----------------------------------------------------
    // Default constructor for BinarySearchTree.
    // BinarySearchTree is a subclass of BinaryTreeBasis,
    // so this constructs a Binary Tree with an empty root.
    //-----------------------------------------------------
    } // end defaultConstructor

    public BinarySearchTree(T rootItem) {
    //--------------------------------------------------------------
    // Constructs a BinarySearchTree, taking an item as a parameter.
    // BinarySearchTree is a subclass of BinaryTreeBasis,
    // so this constructs a Binary Tree and uses the passed item
    // for the root.
    //--------------------------------------------------------------
        super(rootItem);
    } // end constructor

    @Override
    public void setRootItem(T newItem) {
        throw new UnsupportedOperationException();
    } // end setRootItem

    public void insert(T newItem) {
    //-------------------------------------------------------
    // Inserts an item in the Binary Search Tree.
    // This method uses insertItem to store the new item
    // at the appropriate position in the Binary Search Tree.
    //-------------------------------------------------------
        root = insertItem(root, newItem);
    } // end insert

    public T retrieve(KT searchKey) {
    //-----------------------------------------
    // Returns the item from the specified key.
    // This method uses retrieveItem to search
    // for the root with the specified key.
    //-----------------------------------------
        return retrieveItem(root, searchKey);
    } // end retrieve

    public void delete(KT searchKey) {
    //-----------------------------------------
    // Deletes the root from the specified key.
    // This methods uses deleteItem to search
    // for the root with the specified key.
    //-----------------------------------------
        try {
            root = deleteItem(root, searchKey);
        } catch (TreeException e) {
            System.out.println("Error deleting item.");
            e.printStackTrace();
        }
    } // end delete

    protected TreeNode<T> insertItem(TreeNode<T> tNode, T newItem) {
    //---------------------------------------------------
    // Inserts an item into the Binary Search Tree.
    // This method makes use of recursion to make
    // a new subtree and insert the item in the new node.
    //---------------------------------------------------
        TreeNode<T> newSubtree;
        if (tNode == null) {
            // position of insertion found; insert after leaf
            // create a new node
            tNode = new TreeNode<>(newItem, null, null);
            return tNode;
        } // end if
        T nodeItem = tNode.item;

        // search for the insertion position

        if (newItem.getKey().compareTo(nodeItem.getKey()) < 0) {
            // search the left subtree
            newSubtree = insertItem(tNode.leftChild, newItem);
            tNode.leftChild = newSubtree;
            return tNode;
        }
        else { // search the right subtree
            newSubtree = insertItem(tNode.rightChild, newItem);
            tNode.rightChild = newSubtree;
            return tNode;
        } // end if
    } // end insertItem

    protected T retrieveItem(TreeNode<T> tNode, KT searchKey) {
    //---------------------------------------------------
    // Used by the retrieve method to search for the item
    // at the specified search key.
    // Uses recursion to go through appropriate branches.
    //---------------------------------------------------
        T treeItem;
        if (tNode == null) {
            treeItem = null;
        }
        else {
            T nodeItem = tNode.item;
            if (searchKey.compareTo(nodeItem.getKey()) == 0) {
                // item is in the root of some subtree
                treeItem = tNode.item;
            }
            else if (searchKey.compareTo(nodeItem.getKey()) < 0) {
                // search the left subtree
                treeItem = retrieveItem(tNode.leftChild, searchKey);
            }
            else { // search the right subtree
                treeItem = retrieveItem(tNode.rightChild, searchKey);
            } // end if
        } // end if
        return treeItem;
    } // end retrieveItem

    protected TreeNode<T> deleteItem(TreeNode<T> tNode, KT searchKey) {
    //------------------------------------------------------------
    // Deletes an item at the specified search key. Uses recursion
    // to go through appropriate branches.
    // This method calls deleteNode to delete the node with the
    // appropriately contained item.
    //------------------------------------------------------------
        // Calls: deleteNode.
        TreeNode<T> newSubtree;
        if (tNode == null) {
            throw new TreeException("TreeException: Item not found");
        }
        else {
            T nodeItem = tNode.item;
            if (searchKey.compareTo(nodeItem.getKey()) == 0) {
                // item is in the root of some subtree
                tNode = deleteNode(tNode); // delete the item
            }
            else if (searchKey.compareTo(nodeItem.getKey()) < 0) {
                // search the left subtree
                newSubtree = deleteItem(tNode.leftChild, searchKey);
                tNode.leftChild = newSubtree;
            }
            else { // search the right subtree
                newSubtree = deleteItem(tNode.rightChild, searchKey);
                tNode.rightChild = newSubtree;
            } // end if
        } // end if
        return  tNode;
    } // end deleteItem

    protected TreeNode<T> deleteNode(TreeNode<T> tNode) {
    //--------------------------------------------------
    // Deletes a node. Used by deleteItem, which is used
    // by delete. The algorithm is listed below.
    //--------------------------------------------------

        // Algorithm note: There are four cases to consider:
        //   1. The tNode is a leaf.
        //   2. The tNode has no left child.
        //   3. The tNode has no right child.
        //   4. The tNode has two children.
        // Calls: findLeftmost and deleteLeftmost
        T replacementItem;

        // test for a leaf
        if ( (tNode.leftChild == null) &&
             (tNode.rightChild == null) ) {
            return null;
        } // end if leaf

        // test for no left child
        else if (tNode.leftChild == null) {
            return tNode.rightChild;
        } // end if no left child

        // test for no right child
        else if (tNode.rightChild == null) {
            return tNode.leftChild;
        } // end if no right child

        // there are two children:
        // retrieve and delete the inorder successor
        else {
            replacementItem = findLeftmost(tNode.rightChild);
            tNode.item = replacementItem;
            tNode.rightChild = deleteLeftmost(tNode.rightChild);
            return tNode;
        } // end if
    } // end deleteNode

    protected T findLeftmost(TreeNode<T> tNode) {
    //--------------------------
    // Used by deleteNode.
    // Finds the left-most node.
    //--------------------------
        if (tNode.leftChild == null) {
            return tNode.item;
        }
        else {
            return findLeftmost(tNode.leftChild);
        } // end if
    } // end findLeftmost

    protected TreeNode<T> deleteLeftmost(TreeNode<T> tNode) {
    //----------------------------
    // Used by deleteNode.
    // Deletes the left-most node.
    //----------------------------
        if (tNode.leftChild == null) {
            return tNode.rightChild;
        }
        else {
            tNode.leftChild = deleteLeftmost(tNode.leftChild);
            return tNode;
        } // end if
    } // end deleteLeftmost

    ////////////////////////////////////////////////////////////

    public void display(TreeNode<T> root) {
    //-----------------------------------------
    // This method is used to display the items
    // organized in the Binary Search Tree.
    // This method calls the helper method,
    // printTree, to recursively print out the
    // items of the BinarySearchTree.
    //-----------------------------------------
        printTree(root, 0);

    } // end display

    protected void printTree(TreeNode<T> root, int indentation) {
    //----------------------------------------------------
    // This method makes use of recursion to print out and
    // indent items in the Binary Search Tree (to display
    // the items visually as a Binary Search Tree).
    //----------------------------------------------------
        if (!isEmpty()) {

            indentation = indentation + 3;

            // print right subtree, increasing indentation by one level
            if (root.rightChild != null)
                printTree(root.rightChild, indentation);

            // print contents of root
            System.out.printf("%" + indentation + "s%s\n", "", root.item);

            // print left subtree, increasing indentation by one level
            if (root.leftChild != null)
                printTree(root.leftChild, indentation);

        } // end if
    } // end printTree

    public void printSortedOrder(TreeNode<T> root) {
    //----------------------------------------------
    // This method prints out the items in the
    // Binary Search Tree in alphabetical order.
    // This method works the similarly as printTree.
    // The difference is that this doesn't use
    // special print formatting so that the items
    // print out in a list format.
    //----------------------------------------------
        if (!isEmpty()) {

            // print left subtree
            if (root.leftChild != null)
                printSortedOrder(root.leftChild);

            // print contents of root
            System.out.println(root.item);

            // print right subtree
            if (root.rightChild != null)
                printSortedOrder(root.rightChild);

        } // end if
    } // end printSortedOrder

} // end BinarySearchTree
