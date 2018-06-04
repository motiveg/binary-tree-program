/**
 * Student: Brian Casipit
 * Course: CS 11C - Data Structures and Algorithms
 */

package BinaryTreeProgram;

public class Month extends KeyedItem<String> {

    private String month;

    public Month(String m) {
    //------------------------------------------------
    // Constructor for Month class.
    //
    // Precondition: Parameter must be a String.
    //               Although you can pass in any
    //               String, it is assumed the user
    //               will use this class to store keys
    //               for names of months.
    //
    // Postcondition: The String is stored as private
    //                data and a key is stored for it.
    //------------------------------------------------
        super(m);
        month = m;
    } // end constructor

    @Override
    public String toString() {
        return month;
    } // end toString

} // end Month
