/**
 * Student: Brian Casipit
 * Course: CS 11C - Data Structures and Algorithms
 */

package BinaryTreeProgram;

public abstract class KeyedItem<KT extends Comparable<? super KT>> {

    private KT searchKey;

    public KeyedItem(KT key) {
    //----------------------------------------
    // Constructs a search key for comparison.
    // To be used in a Binary Search Tree.
    //----------------------------------------
        searchKey = key;
    } // end constructor

    public KT getKey() {
        return searchKey;
    } // end getKey

} // end KeyedItem
