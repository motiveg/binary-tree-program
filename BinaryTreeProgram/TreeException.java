/**
 * Student: Brian Casipit
 * Course: CS 11C - Data Structures and Algorithms
 */

package BinaryTreeProgram;

public class TreeException extends RuntimeException{

    public TreeException(String s) {
    //---------------------------------------------------
    // Constructor for TreeException.
    // Throw whenever a runtime exception is encountered.
    //---------------------------------------------------
        super(s);
    } // end constructor

} // end TreeException